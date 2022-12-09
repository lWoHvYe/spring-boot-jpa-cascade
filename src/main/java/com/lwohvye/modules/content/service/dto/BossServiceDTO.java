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
public class BossServiceDTO implements Serializable {

    // ID
    @NotNull(groups = BaseEntity.Update.class)
    private Long id;

    // 名称
    private String name;

    private Set<BossProductServiceDTO> bossProductServiceEntities;
}
