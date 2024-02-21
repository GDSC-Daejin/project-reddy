package com.solution.reddy.domain.result.entity;

import com.solution.reddy.domain.result.dto.AIResultResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity @Builder
@Table(name = "tb_result")
@AllArgsConstructor
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;
    private String title;
    @Column(length = 5000)
    private String description;

    public AIResultResponse toAIResultResponse() {
        return AIResultResponse.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .build();
    }
}
