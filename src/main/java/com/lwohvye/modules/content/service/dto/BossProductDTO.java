package com.lwohvye.modules.content.service.dto;

import com.lwohvye.core.base.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;


/**
 * @author why
 * @date 2020-06-23
 */
@Getter
@Setter
@ToString
public class BossProductDTO implements Serializable {

    // ID
    @NotNull(groups = BaseEntity.Update.class)
    private Long id;

    // 名称
    private String name;

    // 类型，1-基础包产品 2-增值包产品 3-套餐产品
    private Integer type;

//    关联关系
    private Set<BossServiceSmallDTO> bossServices;
}
