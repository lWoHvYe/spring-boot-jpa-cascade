package com.lwohve.spring.boot.jpa.modules.content.rest;

import com.lwohve.spring.boot.jpa.modules.content.domain.BossProductEntity;
import com.lwohve.spring.boot.jpa.modules.content.service.BossProductService;
import com.lwohve.spring.boot.jpa.modules.content.service.dto.BossProductQueryCriteria;
import me.zhengjie.annotation.Log;
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
@Api(tags = "BossProduct管理")
@RestController
@RequestMapping("/api/bossProduct")
public class BossProductController {

    private final BossProductService bossProductService;

    public BossProductController(BossProductService bossProductService) {
        this.bossProductService = bossProductService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('bossProduct:list')")
    public void download(HttpServletResponse response, BossProductQueryCriteria criteria) throws IOException {
        bossProductService.download(bossProductService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询BossProduct")
    @ApiOperation("查询BossProduct")
    @PreAuthorize("@el.check('bossProduct:list')")
    public ResponseEntity getBossProducts(BossProductQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(bossProductService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增BossProduct")
    @ApiOperation("新增BossProduct")
    @PreAuthorize("@el.check('bossProduct:add')")
    public ResponseEntity create(@Validated @RequestBody BossProductEntity resources){
        return new ResponseEntity<>(bossProductService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改BossProduct")
    @ApiOperation("修改BossProduct")
    @PreAuthorize("@el.check('bossProduct:edit')")
    public ResponseEntity update(@Validated @RequestBody BossProductEntity resources){
        bossProductService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @Log("删除BossProduct")
    @ApiOperation("删除BossProduct")
    @PreAuthorize("@el.check('bossProduct:del')")
    public ResponseEntity delete(@PathVariable Long id){
        bossProductService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
