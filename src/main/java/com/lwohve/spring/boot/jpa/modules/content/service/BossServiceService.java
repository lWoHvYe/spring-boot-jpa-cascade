package com.lwohve.spring.boot.jpa.modules.content.service;

import com.lwohve.spring.boot.jpa.modules.content.domain.BossServiceEntity;
import com.lwohve.spring.boot.jpa.modules.content.service.dto.BossServiceDTO;
import com.lwohve.spring.boot.jpa.modules.content.service.dto.BossServiceQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author why
* @date 2020-06-23
*/
public interface BossServiceService {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(BossServiceQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<BossServiceDTO>
    */
    List<BossServiceDTO> queryAll(BossServiceQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return BossServiceDTO
     */
    BossServiceDTO findById(Long id);

    BossServiceDTO create(BossServiceEntity resources);

    void update(BossServiceEntity resources);

    void delete(Long id);

    void download(List<BossServiceDTO> all, HttpServletResponse response) throws IOException;
}
