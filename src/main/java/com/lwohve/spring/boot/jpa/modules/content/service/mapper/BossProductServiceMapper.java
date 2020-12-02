package com.lwohve.spring.boot.jpa.modules.content.service.mapper;

import com.lwohve.spring.boot.jpa.modules.content.domain.BossProductServiceEntity;
import com.lwohve.spring.boot.jpa.modules.content.service.dto.BossProductServiceDTO;
import me.zhengjie.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author why
* @date 2020-06-23
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BossProductServiceMapper extends BaseMapper<BossProductServiceDTO, BossProductServiceEntity> {

}
