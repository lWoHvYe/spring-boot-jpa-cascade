package com.lwohve.spring.boot.jpa.modules.content.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


/**
* @author why
* @date 2020-06-23
*/
@Data
public class BossServiceSmallDTO implements Serializable {

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

//    private List<BossProductSmallDTO> bossProducts;
}
