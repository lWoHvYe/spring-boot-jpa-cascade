package com.lwohvye.modules.content.service.mapstruct;

import com.lwohvye.core.base.BaseMapper;
import com.lwohvye.modules.content.domain.BossProductEntity;
import com.lwohvye.modules.content.service.dto.BossProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author why
 * @date 2020-06-23
 */
@Mapper(componentModel = "spring", uses = {BossProductServiceMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BossProductMapper extends BaseMapper<BossProductDTO, BossProductEntity> {

}
