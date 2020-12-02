package com.lwohve.spring.boot.jpa.modules.content.service;

import com.lwohve.spring.boot.jpa.modules.content.domain.BossProductEntity;
import com.lwohve.spring.boot.jpa.modules.content.service.dto.BossProductDTO;
import com.lwohve.spring.boot.jpa.modules.content.service.dto.BossProductQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author why
* @date 2020-06-23
*/
public interface BossProductService {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(BossProductQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<BossProductDTO>
    */
    List<BossProductDTO> queryAll(BossProductQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return BossProductDTO
     */
    BossProductDTO findById(Long id);

    BossProductDTO create(BossProductEntity resources);

    void update(BossProductEntity resources);

    void delete(Long id);

    void download(List<BossProductDTO> all, HttpServletResponse response) throws IOException;
}
