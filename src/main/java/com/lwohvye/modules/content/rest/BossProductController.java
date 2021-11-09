package com.lwohvye.modules.content.rest;

import com.lwohvye.modules.content.domain.BossProductEntity;
import com.lwohvye.modules.content.service.BossProductService;
import com.lwohvye.modules.content.service.dto.BossProductQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @ApiOperation("查询BossProduct")
    public ResponseEntity getBossProducts(BossProductQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(bossProductService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("新增BossProduct")
    public ResponseEntity create(@Validated @RequestBody BossProductEntity resources) {
        return new ResponseEntity<>(bossProductService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("修改BossProduct")
    public ResponseEntity update(@Validated @RequestBody BossProductEntity resources) {
        bossProductService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("删除BossProduct")
    public ResponseEntity delete(@PathVariable Long id) {
        bossProductService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
