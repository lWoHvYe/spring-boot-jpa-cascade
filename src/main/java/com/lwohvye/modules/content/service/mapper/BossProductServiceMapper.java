package com.lwohvye.modules.content.service.mapper;

import com.lwohvye.base.BaseMapper;
import com.lwohvye.modules.content.domain.BossProductServiceEntity;
import com.lwohvye.modules.content.service.dto.BossProductServiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author why
* @date 2020-06-23
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BossProductServiceMapper extends BaseMapper<BossProductServiceDTO, BossProductServiceEntity> {

}
