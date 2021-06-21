package com.lwohvye.modules.content.service.dto;

import lombok.Data;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;
import java.util.Set;


/**
* @author why
* @date 2020-06-23
*/
@Data
public class BossProductDTO implements Serializable {

    // ID
    private Long id;

    // 名称
    private String name;

    // 类型，1-基础包产品 2-增值包产品 3-套餐产品
    private Integer type;

    @Lazy
    private Set<BossProductServiceDTO> bossProductServiceEntities;
}
