package com.lwohvye.modules.content.service.mapstruct;

import com.lwohvye.core.base.BaseMapper;
import com.lwohvye.modules.content.domain.BossProductServiceEntity;
import com.lwohvye.modules.content.service.dto.BossProductServiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BossProductServiceMapper extends BaseMapper<BossProductServiceDTO, BossProductServiceEntity> {
}
