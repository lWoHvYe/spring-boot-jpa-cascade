package com.lwohvye.modules.content.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.lwohvye.core.utils.PageUtils;
import com.lwohvye.core.utils.QueryHelp;
import com.lwohvye.core.utils.ValidationUtils;
import com.lwohvye.modules.content.domain.BossServiceEntity;
import com.lwohvye.modules.content.repository.BossServiceRepository;
import com.lwohvye.modules.content.service.BossServiceService;
import com.lwohvye.modules.content.service.dto.BossServiceDTO;
import com.lwohvye.modules.content.service.dto.BossServiceQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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
public class BossServiceServiceImpl implements BossServiceService {

    @Autowired
    private BossServiceRepository bossServiceRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Map<String, Object> queryAll(BossServiceQueryCriteria criteria, Pageable pageable) {
        Page<BossServiceEntity> page = bossServiceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtils.toPage(page.map(bossProductEntity -> conversionService.convert(bossProductEntity, BossServiceDTO.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<BossServiceDTO> queryAll(BossServiceQueryCriteria criteria) {
        return bossServiceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder))
                .stream().map(bossServiceEntity -> conversionService.convert(bossServiceEntity, BossServiceDTO.class)).toList();
    }

    @Override
    public BossServiceDTO findById(Long id) {
        BossServiceEntity bossService = bossServiceRepository.findById(id).orElseGet(BossServiceEntity::new);
        ValidationUtils.isNull(bossService.getId(), "BossService", "id", id);
        return conversionService.convert(bossService, BossServiceDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(BossServiceEntity resources) {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        resources.setId(snowflake.nextId());
        bossServiceRepository.save(resources);
        /* 4.1.1版本可以这样强制使用主库，5.x改了配置方式，有时间再看一下
        通过使用 Sharding-JDBC 的 HintManager 分片键值管理器，可以强制使用主库。
        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();
        // 继续JDBC操作*/
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BossServiceEntity resources) {
        BossServiceEntity bossService = bossServiceRepository.findById(resources.getId()).orElseGet(BossServiceEntity::new);
        ValidationUtils.isNull(bossService.getId(), "BossService", "id", resources.getId());
        bossService.copy(resources);
        bossServiceRepository.save(bossService);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        bossServiceRepository.deleteById(id);
    }

}
