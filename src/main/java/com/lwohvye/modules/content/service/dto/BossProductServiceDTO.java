package com.lwohvye.modules.content.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author why
* @date 2020-06-23
*/
@Data
public class BossProductServiceDTO implements Serializable {

    // ID
    private Long id;

    // 产品ID
    private BossProductSmallDTO bossProductEntity;

    // 服务ID
    private BossServiceSmallDTO bossServiceEntity;

    // 状态：0-下线，1-上线
    private Integer status;

    private Integer sequence;
}
