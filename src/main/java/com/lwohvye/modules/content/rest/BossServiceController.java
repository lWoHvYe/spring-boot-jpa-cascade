package com.lwohvye.modules.content.rest;

import com.lwohvye.modules.content.domain.BossServiceEntity;
import com.lwohvye.modules.content.service.BossServiceService;
import com.lwohvye.modules.content.service.dto.BossServiceQueryCriteria;
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
@Api(tags = "BossService管理")
@RestController
@RequestMapping("/api/bossService")
public class BossServiceController {

    private final BossServiceService bossServiceService;

    public BossServiceController(BossServiceService bossServiceService) {
        this.bossServiceService = bossServiceService;
    }

    @GetMapping
    @ApiOperation("查询BossService")
    public ResponseEntity getBossServices(BossServiceQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(bossServiceService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("新增BossService")
    public ResponseEntity create(@Validated @RequestBody BossServiceEntity resources) {
        return new ResponseEntity<>(bossServiceService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("修改BossService")
    public ResponseEntity update(@Validated @RequestBody BossServiceEntity resources) {
        bossServiceService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("删除BossService")
    public ResponseEntity delete(@PathVariable Long id) {
        bossServiceService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
