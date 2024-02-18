package com.solution.reddy.domain.result.entity;

import com.solution.reddy.domain.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_result_group")
@AllArgsConstructor
public class ResultGroupEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_group_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_user_id")
    UserEntity user;

    @Builder
    public ResultGroupEntity(UserEntity user) {
        this.user = user;
    }
}
