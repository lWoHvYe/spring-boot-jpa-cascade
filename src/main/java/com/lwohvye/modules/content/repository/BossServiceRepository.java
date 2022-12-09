package com.lwohvye.modules.content.repository;

import com.lwohvye.modules.content.domain.BossServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author why
 * @date 2020-06-23
 */
public interface BossServiceRepository extends JpaRepository<BossServiceEntity, Long>, JpaSpecificationExecutor<BossServiceEntity> {
}
