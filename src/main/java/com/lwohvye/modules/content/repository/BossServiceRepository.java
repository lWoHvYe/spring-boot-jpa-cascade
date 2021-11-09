package com.lwohvye.modules.content.repository;

import com.lwohvye.modules.content.domain.BossServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @author why
 * @date 2020-06-23
 */
public interface BossServiceRepository extends JpaRepository<BossServiceEntity, Long>, JpaSpecificationExecutor<BossServiceEntity> {

    @Override
//    @EntityGraph(attributePaths = {"bossProductServiceEntities"})
    /*
        不配置@EntityGraph时的情况
        #根据页码，确定需要查询的记录总数 page * size (入参为第3页，每页1条)。分库分表必然导致分页需要一定的解决方案，这种查需要的最大量也是一种方案
        select bossservic0_.id as id1_2_, bossservic0_.name as name2_2_ from boss_service bossservic0_
        where 1=1 limit 0, 3
        #查总记录数
        select count(bossservic0_.id) as col_0_0_ from boss_service bossservic0_ where 1=1
        #查关联表。关联查询product
        select bossproduc0_.service_id as service_5_1_0_, bossproduc0_.id as id1_1_0_, bossproduc0_.id
        as id1_1_1_, bossproduc0_.product_id as product_4_1_1_, bossproduc0_.service_id as service_5_1_1_,
        bossproduc0_.sequence as sequence2_1_1_, bossproduc0_.status as status3_1_1_, bossproduc1_.id
        as id1_0_2_, bossproduc1_.name as name2_0_2_, bossproduc1_.type as type3_0_2_ from boss_product__service
        bossproduc0_ left outer join boss_product_0 bossproduc1_ on bossproduc0_.product_id=bossproduc1_.id
        where ( bossproduc0_.status = 1 ) and bossproduc0_.service_id=1 order by bossproduc0_.sequence
        asc, bossproduc0_.id desc
     */
    Page<BossServiceEntity> findAll(Specification<BossServiceEntity> spec, Pageable pageable);

    // 指向比较明确的，可以试试用@EntityGraph，因为的确缓解了 N+1 的问题
    @EntityGraph(attributePaths = {"bossProductServiceEntities"})
    Optional<List<BossServiceEntity>> findByIdIn(List<Long> ids);
}
