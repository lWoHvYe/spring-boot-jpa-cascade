package com.lwohvye.modules.content.repository;

import com.lwohvye.modules.content.domain.BossProductServiceEntity;
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
    Optional<List<BossProductServiceEntity>> findByProductId(Long productId);

    Optional<List<BossProductServiceEntity>> findByServiceId(Long serviceId);

    Optional<BossProductServiceEntity> findFirstByProductIdAndServiceId(Long productId, Long serviceId);

    @Modifying
    @Query(value = "update BossProductServiceEntity set status = 0 where productId = ?1")
    void updateByProductId(Long productId);

    @Modifying
    @Query(value = "update BossProductServiceEntity set status = 0 where serviceId = ?1")
    void updateByServiceId(Long serviceId);
}
