package com.lwohvye.modules.content.service.impl;

import com.lwohvye.context.CycleAvoidingMappingContext;
import com.lwohvye.modules.content.domain.BossProductServiceEntity;
import com.lwohvye.modules.content.repository.BossProductServiceRepository;
import com.lwohvye.modules.content.service.dto.BossProductServiceDTO;
import com.lwohvye.modules.content.service.mapper.BossProductServiceMapper;
import com.lwohvye.modules.content.service.BossProductServiceService;
import com.lwohvye.modules.content.service.dto.BossProductServiceQueryCriteria;
import com.lwohvye.utils.PageUtil;
import com.lwohvye.utils.QueryHelp;
import com.lwohvye.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author why
 * @date 2020-06-23
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BossProductServiceServiceImpl implements BossProductServiceService {

    private final BossProductServiceRepository bossProductServiceRepository;

    private final BossProductServiceMapper bossProductServiceMapper;

    public BossProductServiceServiceImpl(BossProductServiceRepository bossProductServiceRepository, BossProductServiceMapper bossProductServiceMapper) {
        this.bossProductServiceRepository = bossProductServiceRepository;
        this.bossProductServiceMapper = bossProductServiceMapper;
    }

    @Override
    public Map<String, Object> queryAll(BossProductServiceQueryCriteria criteria, Pageable pageable) {
        Page<BossProductServiceEntity> page = bossProductServiceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(bossProductServiceEntity -> bossProductServiceMapper.toDto(bossProductServiceEntity, new CycleAvoidingMappingContext())));
    }

    @Override
    public List<BossProductServiceDTO> queryAll(BossProductServiceQueryCriteria criteria) {
        return bossProductServiceMapper.toDto(bossProductServiceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)),
                new CycleAvoidingMappingContext());
    }

    @Override
    public BossProductServiceDTO findById(Long id) {
        BossProductServiceEntity bossProductService = bossProductServiceRepository.findById(id).orElseGet(BossProductServiceEntity::new);
        ValidationUtil.isNull(bossProductService.getId(), "BossProductService", "id", id);
        return bossProductServiceMapper.toDto(bossProductService, new CycleAvoidingMappingContext());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BossProductServiceDTO create(BossProductServiceEntity resources) {
        return bossProductServiceMapper.toDto(bossProductServiceRepository.save(resources), new CycleAvoidingMappingContext());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BossProductServiceEntity resources) {
        BossProductServiceEntity bossProductService = bossProductServiceRepository.findById(resources.getId()).orElseGet(BossProductServiceEntity::new);
        ValidationUtil.isNull(bossProductService.getId(), "BossProductService", "id", resources.getId());
        bossProductService.copy(resources);
        bossProductServiceRepository.save(bossProductService);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        bossProductServiceRepository.deleteById(id);
    }


    @Override
    public void download(List<BossProductServiceDTO> all, HttpServletResponse response) throws IOException {
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (BossProductServiceDTO bossProductService : all) {
//            Map<String,Object> map = new LinkedHashMap<>();
//            map.put("产品ID", bossProductService.getBossProductEntity().getName());
//            map.put("服务ID", bossProductService.getBossServiceEntity().getName());
//            map.put("状态：0-下线，1-上线", bossProductService.getStatus());
//            map.put("描述", bossProductService.getDesc());
//            map.put("创建时间", bossProductService.getCreateTime());
//            map.put("更新时间", bossProductService.getUpdateTime());
//            list.add(map);
//        }
//        FileUtil.downloadExcel(list, response);
    }
}
