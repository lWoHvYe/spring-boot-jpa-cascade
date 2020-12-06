package com.lwohvye.modules.content.repository;

import com.lwohvye.modules.content.domain.BossProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @author why
 * @date 2020-06-23
 */
public interface BossProductRepository extends JpaRepository<BossProductEntity, Long>, JpaSpecificationExecutor<BossProductEntity> {
    Optional<List<BossProductEntity>> findByIdIn(List<Long> ids);
}
