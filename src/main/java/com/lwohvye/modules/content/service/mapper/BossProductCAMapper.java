/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package com.lwohvye.modules.content.service.mapper;

import com.lwohvye.modules.content.base.CycleAvoidingMappingContext;
import com.lwohvye.modules.content.domain.BossProductEntity;
import com.lwohvye.modules.content.service.dto.BossProductDTO;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author Filip Hrisafov
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BossProductCAMapper {

    @InheritInverseConfiguration
    BossProductDTO toDto(BossProductEntity dtoList, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    List<BossProductDTO> toDto(List<BossProductEntity> entityList, @Context CycleAvoidingMappingContext context);

    BossProductEntity toEntity(BossProductDTO dto, @Context CycleAvoidingMappingContext context);

    List<BossProductEntity> toEntity(List<BossProductDTO> dto, @Context CycleAvoidingMappingContext context);

}
