package com.lwohvye.modules.content.service.impl;

import com.lwohvye.modules.content.service.BossServiceService;
import com.lwohvye.modules.content.service.dto.BossServiceDTO;
import com.lwohvye.modules.content.service.dto.BossServiceQueryCriteria;
import com.lwohvye.modules.content.service.mapper.BossServiceMapper;
import com.lwohvye.modules.content.domain.BossServiceEntity;
import com.lwohvye.modules.content.repository.BossServiceRepository;
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
public class BossServiceServiceImpl implements BossServiceService {

    @Autowired
    private BossServiceRepository bossServiceRepository;

    @Autowired
    private BossServiceMapper bossServiceMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> queryAll(BossServiceQueryCriteria criteria, Pageable pageable) {
        Page<BossServiceEntity> page = bossServiceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria,
                criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(bossServiceMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BossServiceDTO> queryAll(BossServiceQueryCriteria criteria) {
        return bossServiceMapper.toDto(bossServiceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria,
                criteriaBuilder)));
    }

    @Override
    public BossServiceDTO findById(Long id) {
        BossServiceEntity bossService = bossServiceRepository.findById(id).orElseGet(BossServiceEntity::new);
        ValidationUtil.isNull(bossService.getId(), "BossService", "id", id);
        return bossServiceMapper.toDto(bossService);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BossServiceDTO create(BossServiceEntity resources) {
        BossServiceEntity bossService = bossServiceRepository.save(resources);
        return bossServiceMapper.toDto(bossService);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BossServiceEntity resources) {
        BossServiceEntity bossService = bossServiceRepository.findById(resources.getId()).orElseGet(BossServiceEntity::new);
        ValidationUtil.isNull(bossService.getId(), "BossService", "id", resources.getId());
        bossService.copy(resources);
        bossServiceRepository.save(bossService);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        bossServiceRepository.deleteById(id);
    }

}
