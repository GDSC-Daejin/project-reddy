package com.solution.reddy.domain.result.entity;

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
@Entity
@Builder
@Table(name = "tb_result_group")
@AllArgsConstructor
public class ResultGroupEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_group_id")
    Long id;

}
