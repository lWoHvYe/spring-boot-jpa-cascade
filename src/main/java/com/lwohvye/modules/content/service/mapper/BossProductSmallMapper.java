package com.lwohvye.modules.content.service.mapper;

import com.lwohvye.modules.content.domain.BossProductEntity;
import com.lwohvye.modules.content.service.dto.BossProductSmallDTO;
import com.lwohvye.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author why
* @date 2020-06-23
*/
// TODO: 2021/1/9 这些smallMapper应该可以使用mapstruct的一些配置，用原mapper实现，
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BossProductSmallMapper extends BaseMapper<BossProductSmallDTO, BossProductEntity> {

}
