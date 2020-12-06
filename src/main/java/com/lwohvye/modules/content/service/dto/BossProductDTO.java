package com.lwohvye.modules.content.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
* @author why
* @date 2020-06-23
*/
@Data
public class BossProductDTO implements Serializable {

    // ID
    private Long id;

    // CODE
    private String code;

    // 名称
    private String name;

    // 类型，1-基础包产品 2-增值包产品 3-套餐产品
    private Integer type;

    // 价格，单位：分
    private Integer price;

    // 原始价格
    private Integer originalPrice;

    // 积分
    private Integer points;

    // 原始积分
    private Integer originalPoints;

    // 计费类型：0-FREE（免费），1-PPV（一次性收费），2-SVOD（周期性收费）
    private Integer feeType;

    // 计费单位：1-小时 2-天 3-周 4-月 5-季 6-年
    private Integer feeUnit;

    //    计费时长（>0）：-1-永久 其他-具体值
    private Integer feeDuration;

    //    计费生效规则：1-订购立即生效 2-下一自然周期生效
    private Integer feeEffective;

    // 计费失效规则：1-退订立即失效 2-服务期满自动失效 3-退订自然周期满失效
    private Integer feeInvalid;

    // 开始时间
    private Timestamp startTime;

    // 过期时间
    private Timestamp expireTime;

    // 状态：0-下线，1-上线
    private Integer status;

    // 图片
    private String img;

    // 描述
    private String desc;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

    private String outProductId;
//    关联关系
    private List<BossServiceSmallDTO> bossServices;
}
