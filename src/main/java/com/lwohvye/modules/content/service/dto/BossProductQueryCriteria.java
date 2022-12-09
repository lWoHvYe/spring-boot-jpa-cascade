package com.lwohvye.modules.content.service.dto;

import com.lwohvye.core.annotation.Query;
import lombok.Data;

/**
* @author why
* @date 2020-06-23
*/
@Data
public class BossProductQueryCriteria{

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
    private Integer type;

    // 精确
    @Query
    private Integer feeType;

    // 计费单位：1-小时 2-天 3-周 4-月 5-季 6-年
    @Query
    private Integer feeUnit;

    //    计费时长（>0）：-1-永久 其他-具体值
    @Query
    private Integer feeDuration;

    //    计费生效规则：1-订购立即生效 2-下一自然周期生效
    @Query
    private Integer feeEffective;

    // 计费失效规则：1-退订立即失效 2-服务期满自动失效 3-退订自然周期满失效
    @Query
    private Integer feeInvalid;

    // 精确
    @Query
    private Integer status;
}
