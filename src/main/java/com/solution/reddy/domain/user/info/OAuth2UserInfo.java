package com.solution.reddy.domain.user.info;


import com.solution.reddy.domain.user.entity.RoleType;
import com.solution.reddy.domain.user.entity.SocialType;
import com.solution.reddy.domain.user.entity.Status;
import com.solution.reddy.domain.user.entity.UserEntity;

public abstract class OAuth2UserInfo {
    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract SocialType getSocialType();


    public UserEntity createUserEntity() {
        return UserEntity.builder()
                .email(getEmail())
                .name(getName())
                .socialType(getSocialType())
                .roleType(RoleType.MEMBER)
                .status(Status.ACTIVE)
                .build();
    }

}
