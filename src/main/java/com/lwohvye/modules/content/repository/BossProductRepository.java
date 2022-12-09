package com.lwohvye.modules.content.repository;

import com.lwohvye.modules.content.domain.BossProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @author why
 * @date 2020-06-23
 */
public interface BossProductRepository extends JpaRepository<BossProductEntity, Long>, JpaSpecificationExecutor<BossProductEntity> {

    @Override
//    @EntityGraph(value = "product-details")
    /*
        配置EntityGraph后。分页查询查了所有记录。然后在内存中分页
        #查product及product__service
        select bossproduc0_.id as id1_0_0_, bossproduc1_.id as id1_1_1_, bossproduc0_.name as name2_0_0_,
        bossproduc0_.type as type3_0_0_, bossproduc1_.product_id as product_4_1_1_, bossproduc1_.service_id
        as service_5_1_1_, bossproduc1_.sequence as sequence2_1_1_, bossproduc1_.status as status3_1_1_,
        bossproduc1_.product_id as product_4_1_0__, bossproduc1_.id as id1_1_0__ from boss_product_0
        bossproduc0_ left outer join boss_product__service bossproduc1_ on bossproduc0_.id=bossproduc1_.product_id
        and ( bossproduc1_.status = 1 ) where 1=1 order by bossproduc1_.sequence asc, bossproduc1_.id
        desc
        #查总记录数
        select count(bossproduc0_.id) as col_0_0_ from boss_product_0 bossproduc0_ where 1=1
        查关联的service的详细信息
        select bossservic0_.id as id1_2_0_, bossservic0_.name as name2_2_0_ from boss_service bossservic0_
        where bossservic0_.id=3
    */
    Page<BossProductEntity> findAll(Specification<BossProductEntity> spec, Pageable pageable);

    Optional<List<BossProductEntity>> findByIdIn(List<Long> ids);
}
