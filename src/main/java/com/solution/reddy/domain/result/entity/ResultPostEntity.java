package com.solution.reddy.domain.result.entity;

import com.solution.reddy.domain.result.dto.GetUserPostDetailResponseDto;
import com.solution.reddy.domain.result.dto.GetUserPostResponseDto;
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
@Entity @Builder
@Table(name = "tb_result_post")
@AllArgsConstructor
public class ResultPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_post_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_result_group_id")
    ResultGroupEntity group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_result_id")
    ResultEntity result;
    String imageUrl;

    public GetUserPostResponseDto toGetUserPostResponseDto() {
        return GetUserPostResponseDto.builder()
                .groupId(this.group.getId())
                .resultTitle(this.result.getTitle())
                .imageUrl(this.imageUrl)
                .build();
    }

    public GetUserPostDetailResponseDto getUserPostDetailResponseDto() {
        return GetUserPostDetailResponseDto.builder()
                .imageUrl(this.imageUrl)
                .resultTitle(this.result.getTitle())
                .description(this.result.getDescription())
                .build();
    }
}
