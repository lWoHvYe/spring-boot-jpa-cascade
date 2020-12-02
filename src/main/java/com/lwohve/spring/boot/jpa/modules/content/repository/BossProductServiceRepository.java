package com.lwohve.spring.boot.jpa.modules.content.repository;

import com.lwohve.spring.boot.jpa.modules.content.domain.BossProductServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author why
 * @date 2020-06-23
 */
public interface BossProductServiceRepository extends JpaRepository<BossProductServiceEntity, Long>, JpaSpecificationExecutor<BossProductServiceEntity> {
    Optional<List<BossProductServiceEntity>> findByBossProductEntity_Id(Long productId);

    Optional<List<BossProductServiceEntity>> findByBossServiceEntity_Id(Long serviceId);

    Optional<BossProductServiceEntity> findFirstByBossProductEntity_IdAndBossServiceEntity_Id(Long productId, Long serviceId);

    @Modifying
    @Query(value = "update BossProductServiceEntity set status = 0 where bossProductEntity.id = ?1")
    void updateByProductId(Long productId);

    @Modifying
    @Query(value = "update BossProductServiceEntity set status = 0 where bossServiceEntity.id = ?1")
    void updateByServiceId(Long serviceId);
}
