package com.lwohve.spring.boot.jpa.modules.content.rest;

import com.lwohve.spring.boot.jpa.modules.content.domain.BossServiceEntity;
import com.lwohve.spring.boot.jpa.modules.content.service.BossServiceService;
import com.lwohve.spring.boot.jpa.modules.content.service.dto.BossServiceQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhengjie.annotation.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author why
* @date 2020-06-23
*/
@Api(tags = "BossService管理")
@RestController
@RequestMapping("/api/bossService")
public class BossServiceController {

    private final BossServiceService bossServiceService;

    public BossServiceController(BossServiceService bossServiceService) {
        this.bossServiceService = bossServiceService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('bossService:list')")
    public void download(HttpServletResponse response, BossServiceQueryCriteria criteria) throws IOException {
        bossServiceService.download(bossServiceService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询BossService")
    @ApiOperation("查询BossService")
    @PreAuthorize("@el.check('bossService:list')")
    public ResponseEntity getBossServices(BossServiceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(bossServiceService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增BossService")
    @ApiOperation("新增BossService")
    @PreAuthorize("@el.check('bossService:add')")
    public ResponseEntity create(@Validated @RequestBody BossServiceEntity resources){
        return new ResponseEntity<>(bossServiceService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改BossService")
    @ApiOperation("修改BossService")
    @PreAuthorize("@el.check('bossService:edit')")
    public ResponseEntity update(@Validated @RequestBody BossServiceEntity resources){
        bossServiceService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @Log("删除BossService")
    @ApiOperation("删除BossService")
    @PreAuthorize("@el.check('bossService:del')")
    public ResponseEntity delete(@PathVariable Long id){
        bossServiceService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
