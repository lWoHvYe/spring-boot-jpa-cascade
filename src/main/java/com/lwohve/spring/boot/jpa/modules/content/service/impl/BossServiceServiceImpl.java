package com.lwohve.spring.boot.jpa.modules.content.service.impl;

import com.lwohve.spring.boot.jpa.modules.content.domain.BossServiceEntity;
import com.lwohve.spring.boot.jpa.modules.content.repository.BossProductRepository;
import com.lwohve.spring.boot.jpa.modules.content.repository.BossProductServiceRepository;
import com.lwohve.spring.boot.jpa.modules.content.repository.BossServiceRepository;
import com.lwohve.spring.boot.jpa.modules.content.service.dto.BossServiceDTO;
import com.lwohve.spring.boot.jpa.modules.content.service.BossServiceService;
import com.lwohve.spring.boot.jpa.modules.content.service.dto.BossServiceQueryCriteria;
import com.lwohve.spring.boot.jpa.modules.content.service.mapper.BossServiceMapper;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

    @Autowired
    private BossProductServiceRepository bossProductServiceRepository;

    @Autowired
    private BossProductRepository bossProductRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> queryAll(BossServiceQueryCriteria criteria, Pageable pageable) {
        Page<BossServiceEntity> page = bossServiceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria,
                criteriaBuilder), pageable);
        //由于关联表中不止id属性，需要手动维护关联关系
//        List<BossServiceEntity> content = page.getContent();
//                .stream().peek(bossService -> {
//            List<Long> productIds =
//                    bossProductServiceRepository.findByServiceId(bossService.getId()).orElse(new ArrayList<>()).stream()
//                            .filter(bossProductServiceEntity -> bossProductServiceEntity.getStatus() == 1).map(BossProductServiceEntity::getProductId).collect(Collectors.toList());
//            List<BossProductEntity> bossProducts = bossProductRepository.findByIdIn(productIds).orElse(new ArrayList<>());
//            bossService.setBossProducts(bossProducts);
//        }).collect(Collectors.toList());
//        Map<String, Object> map = Maps.newLinkedHashMapWithExpectedSize(2);
//        map.put("content", content);
//        map.put("totalElements", page.getTotalElements());
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
//        resources.setId(bossService.getId());
//        saveLinks(resources);
        return bossServiceMapper.toDto(bossService);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BossServiceEntity resources) {
        BossServiceEntity bossService = bossServiceRepository.findById(resources.getId()).orElseGet(BossServiceEntity::new);
        ValidationUtil.isNull(bossService.getId(), "BossService", "id", resources.getId());
        bossService.copy(resources);
//        saveLinks(bossService);
        bossServiceRepository.save(bossService);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        bossServiceRepository.deleteById(id);
    }

    /**
     * @return void
     * @description 更新关联关系，因为关联表存在状态、时间等字段，所以需要手动维护
     * @params [bossService]
     * @author Hongyan Wang
     * @date 2020/6/24 20:18
     */
    private void saveLinks(BossServiceEntity bossService) {
        // 先设置与该产品有关的关联记录全部失效
//        bossProductServiceRepository.updateByServiceId(bossService.getId());
//        bossService.getBossProducts().forEach(
//                bossProduct -> {
//                    BossProductServiceEntity bossProductService =
//                            // 根据productId和serviceId查询，查到的记录状态改为1，未查到的执行添加操作
//                            bossProductServiceRepository.findFirstByProductIdAndServiceId(bossProduct.getId(), bossService.getId())
//                                    .orElse(new BossProductServiceEntity()
//                                            .setProductId(bossProduct.getId())
//                                            .setServiceId(bossService.getId()));
//                    bossProductService.setStatus(1);
//                    bossProductServiceRepository.save(bossProductService);
//                }
//        );
    }

    @Override
    public void download(List<BossServiceDTO> all, HttpServletResponse response) throws IOException {
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (BossServiceDTO bossService : all) {
//            Map<String, Object> map = new LinkedHashMap<>();
//            map.put("CODE", bossService.getCode());
//            map.put("名称", bossService.getName());
//            map.put("状态：0-下线，1-上线", bossService.getStatus());
//            map.put("描述", bossService.getDesc());
//            map.put("创建时间", bossService.getCreateTime());
//            map.put("更新时间", bossService.getUpdateTime());
//            list.add(map);
//        }
//        FileUtil.downloadExcel(list, response);
    }
}
