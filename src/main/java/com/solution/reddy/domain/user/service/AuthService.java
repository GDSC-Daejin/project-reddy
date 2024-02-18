package com.solution.reddy.domain.user.service;

import static com.solution.reddy.global.message.AuthMessage.INVALID_ID_TOKEN;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.solution.reddy.domain.user.entity.RoleType;
import com.solution.reddy.domain.user.entity.SocialType;
import com.solution.reddy.domain.user.info.OAuth2UserInfo;
import com.solution.reddy.domain.user.info.impl.GoogleOAuth2UserInfo;
import com.solution.reddy.domain.user.info.impl.TestOAuth2UserInfo;
import com.solution.reddy.domain.user.repository.UserRepository;
import com.solution.reddy.global.exception.ApiException;
import com.solution.reddy.global.jwt.JwtTokenProvider;
import com.solution.reddy.global.jwt.dto.TokenResponse;
import com.solution.reddy.global.service.RedisService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${google.client.id}")
    private String googleClientId;
    private final JwtTokenProvider tokenProvider;
    private final RedisService redisService;
    private final UserRepository userRepository;

    @Transactional
    public TokenResponse googleLogin(String idToken) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(List.of(googleClientId))
                .build();
        try {
            GoogleIdToken googleIdToken = verifier.verify(idToken);

            if (googleIdToken == null) {
                throw new ApiException(INVALID_ID_TOKEN);
            }
            else {
                GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo(googleIdToken.getPayload());
                saveUser(userInfo);
                return generateJwtToken(userInfo.getSocialTypeName(), userInfo.getEmail());
            }
        } catch (IllegalArgumentException | HttpClientErrorException | GeneralSecurityException | IOException e) {
            throw new ApiException(INVALID_ID_TOKEN);
        }
    }

    private void saveUser(OAuth2UserInfo userInfo) {
        userRepository.save(userInfo.createUserEntity());
    }

    @Transactional
    public TokenResponse testLogin(String email) {
        saveUser(new TestOAuth2UserInfo());
        return generateJwtToken(String.valueOf(SocialType.TEST), email);
    }

    @Transactional
    public void logout(String socialType, String email) {
//        String requestAccessToken = resolveToken(requestAccessTokenInHeader);

        // Redis에 저장되어 있는 RT 삭제
        String refreshTokenInRedis = redisService.getValues("RT(" + socialType + "):" + email);
        if (refreshTokenInRedis != null) {
            redisService.deleteValues("RT(" + socialType + "):" + email);
        }

//        // Redis에 로그아웃 처리한 AT 저장
//        long expiration = tokenProvider.getTokenExpirationTime(requestAccessToken) - new Date().getTime();
//        redisService.setValuesWithTimeout(requestAccessToken,
//                "logout",
//                expiration);
    }

    // 토큰 재발급: validate 메서드가 true 반환할 때만 사용 -> AT, RT 재발급
    @Transactional
    public TokenResponse reissue(String provider, String email, String requestRefreshToken) {

        String refreshTokenInRedis = redisService.getValues("RT(" + provider + "):" + email);
        if (refreshTokenInRedis == null) { // Redis에 저장되어 있는 RT가 없을 경우
            return null; // -> 재로그인 요청
        }

        // 요청된 RT의 유효성 검사 & Redis에 저장되어 있는 RT와 같은지 비교
        if(!tokenProvider.validateToken(requestRefreshToken) || !refreshTokenInRedis.equals(requestRefreshToken)) {
            redisService.deleteValues("RT(" + provider + "):" + email); // 탈취 가능성 -> 삭제
            return null; // -> 재로그인 요청
        }
        // 토큰 재발급 및 Redis 업데이트
        redisService.deleteValues("RT(" + provider + "):" + email); // 기존 RT 삭제
        return generateJwtToken(provider, email);
    }


    private TokenResponse generateJwtToken(String provider, String email) {
        // RT가 이미 있을 경우
        if(redisService.getValues("RT(" + provider + "):" + email) != null) {
            redisService.deleteValues("RT(" + provider + "):" + email); // 삭제
        }
        // AT, RT 생성 및 Redis에 RT 저장
        TokenResponse tokenDto = createJwtToken(provider, email);
        saveRefreshToken(provider, email, tokenDto.refreshToken());
        return tokenDto;
    }

    @Transactional
    public void saveRefreshToken(String provider, String email, String refreshToken) {
        redisService.setValuesWithTimeout("RT(" + provider + "):" + email, // key
                refreshToken, // value
                tokenProvider.getTokenExpirationTime(refreshToken)); // timeout(milliseconds)
    }

    private TokenResponse createJwtToken(String provider, String email) {
        return tokenProvider.generateJwtToken(provider, email, RoleType.MEMBER);
    }

    // "Bearer {AT}"에서 {AT} 추출
    public String resolveToken(String requestAccessTokenInHeader) {
        if (requestAccessTokenInHeader != null && requestAccessTokenInHeader.startsWith("Bearer ")) {
            return requestAccessTokenInHeader.substring(7);
        }
        return null;
    }
}
