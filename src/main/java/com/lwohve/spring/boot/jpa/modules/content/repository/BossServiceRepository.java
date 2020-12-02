package com.lwohve.spring.boot.jpa.modules.content.repository;

import com.lwohve.spring.boot.jpa.modules.content.domain.BossServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
* @author why
* @date 2020-06-23
*/
public interface BossServiceRepository extends JpaRepository<BossServiceEntity, Long>, JpaSpecificationExecutor<BossServiceEntity> {
    Optional<List<BossServiceEntity>> findByIdIn(List<Long> ids);
}
