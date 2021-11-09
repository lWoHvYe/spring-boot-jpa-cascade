package com.lwohvye.modules.content.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author why
 * @date 2020-06-23
 */
@Getter
@Setter
@ToString
//	    hashCode导致了栈溢出。所以不要用@Data
//		at com.lwohvye.modules.content.service.dto.BossProductServiceDTO.hashCode(BossProductServiceDTO.java:12)
//	    at java.base/java.util.AbstractSet.hashCode(AbstractSet.java:124)
//	    at com.lwohvye.modules.content.service.dto.BossServiceDTO.hashCode(BossServiceDTO.java:14)
public class BossProductServiceDTO implements Serializable {

    // ID
    private Long id;

    // 产品ID
    private BossProductDTO bossProductEntity;

    // 服务ID
    private BossServiceDTO bossServiceEntity;

    // 状态：0-下线，1-上线
    private Integer status;

    private Integer sequence;
}
