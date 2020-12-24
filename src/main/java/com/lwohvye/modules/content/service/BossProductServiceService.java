package com.lwohvye.modules.content.service;

import com.lwohvye.modules.content.domain.BossProductServiceEntity;
import com.lwohvye.modules.content.service.dto.BossProductServiceDTO;
import com.lwohvye.modules.content.service.dto.BossProductServiceQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author why
* @date 2020-06-23
*/
public interface BossProductServiceService {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(BossProductServiceQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<BossProductServiceDTO>
    */
    List<BossProductServiceDTO> queryAll(BossProductServiceQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return BossProductServiceDTO
     */
    BossProductServiceDTO findById(Long id);

    BossProductServiceDTO create(BossProductServiceEntity resources);

    void update(BossProductServiceEntity resources);

    void delete(Long id);

}
