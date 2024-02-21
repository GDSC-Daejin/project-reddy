package com.solution.reddy.domain.result.resository;

import com.solution.reddy.domain.result.entity.ResultPostEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResultPostRepository extends JpaRepository<ResultPostEntity, Long> {

    @Query("select rp from ResultPostEntity rp where rp.group.id = :id")
    ResultPostEntity findByGroupId(Long id);
}
