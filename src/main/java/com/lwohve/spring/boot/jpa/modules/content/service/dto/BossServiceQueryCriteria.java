package com.lwohve.spring.boot.jpa.modules.content.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
* @author why
* @date 2020-06-23
*/
@Data
public class BossServiceQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private String code;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    // 精确
    @Query
    private Integer status;
}
