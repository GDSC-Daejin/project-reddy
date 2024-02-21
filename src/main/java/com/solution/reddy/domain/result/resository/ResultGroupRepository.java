package com.solution.reddy.domain.result.resository;

import com.solution.reddy.domain.result.entity.ResultGroupEntity;
import com.solution.reddy.domain.user.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResultGroupRepository extends JpaRepository<ResultGroupEntity, Long> {

    @Query("select rg from ResultGroupEntity rg where rg.user = :user")
    List<ResultGroupEntity> findAllByUser(UserEntity user);
}
