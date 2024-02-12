package com.solution.reddy.domain.user.info.impl;

import com.solution.reddy.domain.user.entity.SocialType;
import com.solution.reddy.domain.user.info.OAuth2UserInfo;
import lombok.Getter;

@Getter
public class TestOAuth2UserInfo extends OAuth2UserInfo {
    private String id;
    private String name;
    private final String email;
    private final SocialType socialType = SocialType.TEST;

    public TestOAuth2UserInfo() {
        this.id = "test";
        this.email = "test";
        this.name = "test";
    }

    public String getSocialTypeName() {
        return socialType.toString();
    }
}
