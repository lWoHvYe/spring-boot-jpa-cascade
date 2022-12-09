package com.lwohvye.modules.content.rest;

import com.lwohvye.core.base.BaseEntity;
import com.lwohvye.core.utils.result.ResultInfo;
import com.lwohvye.modules.content.service.BossServiceService;
import com.lwohvye.modules.content.service.dto.BossServiceDTO;
import com.lwohvye.modules.content.service.dto.BossServiceQueryCriteria;
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
@Tag(name = "BossService", description = "BossService管理")
@RestController
@RequestMapping("/api/bossService")
public class BossServiceController {

    private final BossServiceService bossServiceService;

    public BossServiceController(BossServiceService bossServiceService) {
        this.bossServiceService = bossServiceService;
    }

    @GetMapping
    @Operation(summary = "查询BossService")
    public Map<String, Object> getBossServices(BossServiceQueryCriteria criteria, Pageable pageable) {
        return bossServiceService.queryAll(criteria, pageable);
    }

    @PostMapping
    @Operation(summary = "新增BossService")
    public ResponseEntity<ResultInfo<String>> create(@Validated @RequestBody BossServiceDTO resources) {
        bossServiceService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "修改BossService")
    public ResponseEntity<ResultInfo<String>> update(@Validated(BaseEntity.Update.class) @RequestBody BossServiceDTO resources) {
        bossServiceService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 会直接忽略body，因为是204 No_Content
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "删除BossService")
    public ResultInfo<String> delete(@PathVariable Long id) {
        bossServiceService.delete(id);
        return ResultInfo.success();
    }
}
