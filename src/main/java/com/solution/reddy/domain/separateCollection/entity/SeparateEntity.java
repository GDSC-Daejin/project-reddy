package com.solution.reddy.domain.separateCollection.entity;

import com.solution.reddy.domain.category.entity.CategoryEntity;
import com.solution.reddy.domain.separateCollection.dto.response.SeparatePostDetailDto;
import com.solution.reddy.domain.separateCollection.dto.response.SeparatePostResponseItem;
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
@Table(name = "tb_separate")
@AllArgsConstructor
public class SeparateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_category_id")
    private CategoryEntity category;

    private String imageUrl;
    private String title;
    private String description;

    public SeparatePostResponseItem toSeparatePostResponseItem() {
        return SeparatePostResponseItem.builder()
                .category(this.category.toCategoryResponseDto())
                .id(this.id)
                .imageUrl(this.imageUrl)
                .title(this.title)
                .build();
    }

    public SeparatePostDetailDto toSeparatePostDetailDto() {
        return SeparatePostDetailDto.builder()
                .id(this.id)
                .imageUrl(this.imageUrl)
                .title(this.title)
                .description(this.description)
                .build();
    }
}
