package com.lwohvye.modules.content.service.dto;

import com.lwohvye.core.annotation.Query;
import lombok.Data;

/**
* @author why
* @date 2020-06-23
*/
@Data
public class BossServiceQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String name;
}
