package com.lwohvye.modules.content.service.impl;

import com.lwohvye.core.context.CycleAvoidingMappingContext;
import com.lwohvye.core.utils.PageUtils;
import com.lwohvye.core.utils.QueryHelp;
import com.lwohvye.core.utils.ValidationUtils;
import com.lwohvye.modules.content.domain.BossProductEntity;
import com.lwohvye.modules.content.repository.BossProductRepository;
import com.lwohvye.modules.content.service.BossProductService;
import com.lwohvye.modules.content.service.dto.BossProductDTO;
import com.lwohvye.modules.content.service.dto.BossProductQueryCriteria;
import com.lwohvye.modules.content.service.mapstruct.BossProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author why
 * @date 2020-06-23
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BossProductServiceImpl implements BossProductService {

    @Autowired
    private BossProductRepository bossProductRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private BossProductMapper bossProductMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Map<String, Object> queryAll(BossProductQueryCriteria criteria, Pageable pageable) {
        var page = bossProductRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtils.toPage(page.map(bossProductEntity -> conversionService.convert(bossProductEntity, BossProductDTO.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<BossProductDTO> queryAll(BossProductQueryCriteria criteria) {
        return bossProductRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder))
                .stream().map(bossProductEntity -> conversionService.convert(bossProductEntity, BossProductDTO.class)).toList();
    }

    @Override
    public BossProductDTO findById(Long id) {
        var bossProduct = bossProductRepository.findById(id).orElseGet(BossProductEntity::new);
        ValidationUtils.isNull(bossProduct.getId(), "BossProduct", "id", id);
        return conversionService.convert(bossProduct, BossProductDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(BossProductDTO resources) {
        var bossProductEntity = bossProductMapper.toEntity(resources, new CycleAvoidingMappingContext());
        bossProductRepository.save(bossProductEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BossProductDTO resources) {
        var bossProduct = bossProductRepository.findById(resources.getId()).orElseGet(BossProductEntity::new);
        ValidationUtils.isNull(bossProduct.getId(), "BossProduct", "id", resources.getId());
        var bossProductEntity = bossProductMapper.toEntity(resources, new CycleAvoidingMappingContext());
        bossProduct.copy(bossProductEntity);
        bossProductRepository.save(bossProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        bossProductRepository.deleteById(id);
    }

}
