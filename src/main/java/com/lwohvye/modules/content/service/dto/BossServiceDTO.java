package com.lwohvye.modules.content.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;


/**
* @author why
* @date 2020-06-23
*/
@Data
public class BossServiceDTO implements Serializable {

    // ID
    private Long id;

    // CODE
    private String code;

    // 名称
    private String name;

    // 状态：0-下线，1-上线
    private Integer status;

    // 描述
    private String desc;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

    private Set<BossProductServiceDTO> bossProductServiceEntities;
}
