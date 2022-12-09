package com.lwohvye.modules.content.rest;

import com.lwohvye.core.utils.result.ResultInfo;
import com.lwohvye.modules.content.domain.BossProductEntity;
import com.lwohvye.modules.content.service.BossProductService;
import com.lwohvye.modules.content.service.dto.BossProductQueryCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author why
 * @date 2020-06-23
 */
@Tag(name = "BossProduct", description = "BossProduct管理")
@RestController
@RequestMapping("/api/bossProduct")
public class BossProductController {

    private final BossProductService bossProductService;

    public BossProductController(BossProductService bossProductService) {
        this.bossProductService = bossProductService;
    }

    @GetMapping
    @Operation(summary = "查询BossProduct")
    public Map<String, Object> getBossProducts(BossProductQueryCriteria criteria, Pageable pageable) {
        return bossProductService.queryAll(criteria, pageable);
    }

    @PostMapping
    @Operation(summary = "新增BossProduct")
    public ResponseEntity<ResultInfo<String>> create(@Validated @RequestBody BossProductEntity resources) {
        bossProductService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "修改BossProduct")
    public ResponseEntity<ResultInfo<String>> update(@Validated @RequestBody BossProductEntity resources) {
        bossProductService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 会直接忽略body，因为是204 No_Content
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "删除BossProduct")
    public ResultInfo<String> delete(@PathVariable Long id) {
        bossProductService.delete(id);
        return ResultInfo.success();
    }
}
