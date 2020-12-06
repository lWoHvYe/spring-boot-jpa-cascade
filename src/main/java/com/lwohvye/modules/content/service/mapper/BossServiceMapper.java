package com.lwohvye.modules.content.service.mapper;

import com.lwohvye.modules.content.domain.BossServiceEntity;
import com.lwohvye.modules.content.service.dto.BossServiceDTO;
import com.lwohvye.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author why
* @date 2020-06-23
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BossServiceMapper extends BaseMapper<BossServiceDTO, BossServiceEntity> {

}
