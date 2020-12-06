package com.lwohve.spring.boot.jpa.modules.content.service.dto;

import com.lwohvye.annotation.Query;
import lombok.Data;

/**
* @author why
* @date 2020-06-23
*/
@Data
public class BossProductServiceQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private Long productId;

    // 精确
    @Query
    private Long serviceId;

    // 精确
    @Query
    private Integer status;
}
