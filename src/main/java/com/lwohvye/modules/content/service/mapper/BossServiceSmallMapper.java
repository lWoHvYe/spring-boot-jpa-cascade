package com.lwohvye.modules.content.service.mapper;

import com.lwohvye.base.BaseMapper;
import com.lwohvye.modules.content.domain.BossServiceEntity;
import com.lwohvye.modules.content.service.dto.BossServiceSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author why
* @date 2020-06-23
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BossServiceSmallMapper extends BaseMapper<BossServiceSmallDTO, BossServiceEntity> {

}
