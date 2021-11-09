package com.lwohvye.modules.content.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.lwohvye.modules.content.base.CycleAvoidingMappingContext;
import com.lwohvye.modules.content.domain.BossProductEntity;
import com.lwohvye.modules.content.repository.BossProductRepository;
import com.lwohvye.modules.content.service.BossProductService;
import com.lwohvye.modules.content.service.dto.BossProductDTO;
import com.lwohvye.modules.content.service.dto.BossProductQueryCriteria;
import com.lwohvye.modules.content.service.mapper.BossProductCAMapper;
import com.lwohvye.modules.content.service.mapper.BossProductMapper;
import com.lwohvye.utils.PageUtil;
import com.lwohvye.utils.QueryHelp;
import com.lwohvye.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private BossProductMapper bossProductMapper;

    @Autowired
    private BossProductCAMapper bossProductCAMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> queryAll(BossProductQueryCriteria criteria, Pageable pageable) {
        Page<BossProductEntity> page = bossProductRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(bossProductEntity -> bossProductCAMapper.toDto(bossProductEntity, new CycleAvoidingMappingContext())));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BossProductDTO> queryAll(BossProductQueryCriteria criteria) {
        return bossProductMapper.toDto(bossProductRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public BossProductDTO findById(Long id) {
        BossProductEntity bossProduct = bossProductRepository.findById(id).orElseGet(BossProductEntity::new);
        ValidationUtil.isNull(bossProduct.getId(), "BossProduct", "id", id);
        return bossProductMapper.toDto(bossProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BossProductDTO create(BossProductEntity resources) {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        resources.setId(snowflake.nextId());
        BossProductEntity bossProduct = bossProductRepository.save(resources);
        return bossProductMapper.toDto(bossProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BossProductEntity resources) {
        BossProductEntity bossProduct = bossProductRepository.findById(resources.getId()).orElseGet(BossProductEntity::new);
        ValidationUtil.isNull(bossProduct.getId(), "BossProduct", "id", resources.getId());
        bossProduct.copy(resources);
        bossProductRepository.save(bossProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        bossProductRepository.deleteById(id);
    }

}
