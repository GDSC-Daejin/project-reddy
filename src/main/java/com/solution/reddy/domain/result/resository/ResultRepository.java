package com.solution.reddy.domain.result.resository;

import com.solution.reddy.domain.result.entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<ResultEntity, Long> {

}
