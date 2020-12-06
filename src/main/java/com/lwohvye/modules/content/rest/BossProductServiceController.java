package com.lwohvye.modules.content.rest;

import com.lwohvye.annotation.Log;
import com.lwohvye.modules.content.domain.BossProductServiceEntity;
import com.lwohvye.modules.content.service.BossProductServiceService;
import com.lwohvye.modules.content.service.dto.BossProductServiceQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author why
* @date 2020-06-23
*/
@Api(tags = "BossProductService管理")
@RestController
@RequestMapping("/api/bossProductService")
public class BossProductServiceController {

    private final BossProductServiceService bossProductServiceService;

    public BossProductServiceController(BossProductServiceService bossProductServiceService) {
        this.bossProductServiceService = bossProductServiceService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('bossProductService:list')")
    public void download(HttpServletResponse response, BossProductServiceQueryCriteria criteria) throws IOException {
        bossProductServiceService.download(bossProductServiceService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询BossProductService")
    @ApiOperation("查询BossProductService")
    @PreAuthorize("@el.check('bossProductService:list')")
    public ResponseEntity getBossProductServices(BossProductServiceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(bossProductServiceService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增BossProductService")
    @ApiOperation("新增BossProductService")
    @PreAuthorize("@el.check('bossProductService:add')")
    public ResponseEntity create(@Validated @RequestBody BossProductServiceEntity resources){
        return new ResponseEntity<>(bossProductServiceService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改BossProductService")
    @ApiOperation("修改BossProductService")
    @PreAuthorize("@el.check('bossProductService:edit')")
    public ResponseEntity update(@Validated @RequestBody BossProductServiceEntity resources){
        bossProductServiceService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @Log("删除BossProductService")
    @ApiOperation("删除BossProductService")
    @PreAuthorize("@el.check('bossProductService:del')")
    public ResponseEntity delete(@PathVariable Long id){
        bossProductServiceService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
