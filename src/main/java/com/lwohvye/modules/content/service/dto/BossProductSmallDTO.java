package com.lwohvye.modules.content.service.dto;

import lombok.Data;

import java.io.Serializable;


/**
* @author why
* @date 2020-06-23
*/
@Data
public class BossProductSmallDTO implements Serializable {

    // ID
    private Long id;

    // 名称
    private String name;

    // 类型，1-基础包产品 2-增值包产品 3-套餐产品
    private Integer type;
//        Small的目的是不包含这部分关系。避免循环依赖
//    private Set<BossServiceSmallDTO> bossServices;
}
