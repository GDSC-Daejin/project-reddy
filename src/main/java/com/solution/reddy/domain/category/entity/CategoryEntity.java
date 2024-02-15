package com.solution.reddy.domain.category.entity;

import com.solution.reddy.domain.category.dto.CategoryResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Getter
@Builder
@Entity @Table(name = "tb_category")
@NoArgsConstructor
@AllArgsConstructor
@BatchSize(size = 100)
public class CategoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String categoryName;

    public CategoryResponseDto toCategoryResponseDto() {
        return CategoryResponseDto.builder()
                .id(this.id)
                .categoryName(this.categoryName)
                .build();
    }

    public CategoryEntity makeCategory(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}
