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
public class BossServiceDTO implements Serializable {

    // ID
    private Long id;

    // 名称
    private String name;

    @Lazy
    private Set<BossProductServiceDTO> bossProductServiceEntities;
}
