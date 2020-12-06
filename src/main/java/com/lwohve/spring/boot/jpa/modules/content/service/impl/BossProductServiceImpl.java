package com.lwohve.spring.boot.jpa.modules.content.service.impl;

import com.lwohve.spring.boot.jpa.modules.content.domain.BossProductEntity;
import com.lwohve.spring.boot.jpa.modules.content.repository.BossProductRepository;
import com.lwohve.spring.boot.jpa.modules.content.repository.BossProductServiceRepository;
import com.lwohve.spring.boot.jpa.modules.content.repository.BossServiceRepository;
import com.lwohve.spring.boot.jpa.modules.content.service.dto.BossProductDTO;
import com.lwohve.spring.boot.jpa.modules.content.service.mapper.BossProductMapper;
import com.lwohve.spring.boot.jpa.modules.content.service.BossProductService;
import com.lwohve.spring.boot.jpa.modules.content.service.dto.BossProductQueryCriteria;
import com.lwohvye.utils.FileUtil;
import com.lwohvye.utils.PageUtil;
import com.lwohvye.utils.QueryHelp;
import com.lwohvye.utils.ValidationUtil;
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
public class BossProductServiceImpl implements BossProductService {

    @Autowired
    private BossProductRepository bossProductRepository;

    @Autowired
    private BossProductMapper bossProductMapper;

    @Autowired
    private BossServiceRepository bossServiceRepository;

    @Autowired
    private BossProductServiceRepository bossProductServiceRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> queryAll(BossProductQueryCriteria criteria, Pageable pageable) {
        Page<BossProductEntity> page = bossProductRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria,
                criteriaBuilder), pageable);
        //由于关联表中不止id属性，需要手动维护关联关系
//        List<BossProductEntity> content = page.getContent();
//                .stream().peek(bossProduct -> {
//            List<Long> serviceIds =
//                    bossProductServiceRepository.findByProductId(bossProduct.getId()).orElse(new ArrayList<>()).stream()
//                            .filter(bossProductServiceEntity -> bossProductServiceEntity.getStatus() == 1).map(BossProductServiceEntity::getServiceId).collect(Collectors.toList());
//            List<BossServiceEntity> bossServices = bossServiceRepository.findByIdIn(serviceIds).orElse(new ArrayList<>());
//            bossProduct.setBossServices(bossServices);
//        }).collect(Collectors.toList());
//        Map<String, Object> map = Maps.newLinkedHashMapWithExpectedSize(2);
//        map.put("content", content);
//        map.put("totalElements", page.getTotalElements());
        return PageUtil.toPage(page.map(bossProductMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BossProductDTO> queryAll(BossProductQueryCriteria criteria) {
        return bossProductMapper.toDto(bossProductRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria,
                criteriaBuilder)));
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
        BossProductEntity bossProduct = bossProductRepository.save(resources);
//        resources.setId(bossProduct.getId());
        // 保存关联关系
//        saveLinks(resources);
        return bossProductMapper.toDto(bossProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BossProductEntity resources) {
        BossProductEntity bossProduct = bossProductRepository.findById(resources.getId()).orElseGet(BossProductEntity::new);
        ValidationUtil.isNull(bossProduct.getId(), "BossProduct", "id", resources.getId());
        bossProduct.copy(resources);
        // 保存关联关系
//        saveLinks(bossProduct);
        bossProductRepository.save(bossProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        bossProductRepository.deleteById(id);
    }


    /**
     * @return void
     * @description 更新关联关系，因为关联表存在状态、时间等字段，所以需要手动维护
     * @params [bossProduct]
     * @author Hongyan Wang
     * @date 2020/6/24 20:18
     */
    private void saveLinks(BossProductEntity bossProduct) {
        // 先设置与该产品有关的关联记录全部失效
//        bossProductServiceRepository.updateByProductId(bossProduct.getId());
//        bossProduct.getBossServices().forEach(
//                bossService -> {
//                    if (bossService == null)
//                        return;
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
//        bossProductRepository.save(bossProduct);
    }

    @Override
    public void download(List<BossProductDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (BossProductDTO bossProduct : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("CODE", bossProduct.getCode());
            map.put("名称", bossProduct.getName());
            map.put("类型，1-基础包产品 2-增值包产品 3-套餐产品", bossProduct.getType());
            map.put("价格，单位：分", bossProduct.getPrice());
            map.put("原始价格", bossProduct.getOriginalPrice());
            map.put("积分", bossProduct.getPoints());
            map.put("原始积分", bossProduct.getOriginalPoints());
            map.put("计费类型：0-FREE（免费），1-PPV（一次性收费），2-SVOD（周期性收费）", bossProduct.getFeeType());
            map.put("开始时间", bossProduct.getStartTime());
            map.put("过期时间", bossProduct.getExpireTime());
            map.put("状态：0-下线，1-上线", bossProduct.getStatus());
            map.put("图片", bossProduct.getImg());
            map.put("描述", bossProduct.getDesc());
            map.put("创建时间", bossProduct.getCreateTime());
            map.put("更新时间", bossProduct.getUpdateTime());
            map.put("outProductId", bossProduct.getOutProductId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
