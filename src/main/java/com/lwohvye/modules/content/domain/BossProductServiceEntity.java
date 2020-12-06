package com.lwohvye.modules.content.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;

import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.sql.Timestamp;

import java.io.Serializable;

/**
* @author why
* @date 2020-06-23
*/
@Accessors(chain = true)
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="boss_product__service")
public class BossProductServiceEntity implements Serializable {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 产品ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private BossProductEntity bossProductEntity;

    // 服务ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private BossServiceEntity bossServiceEntity;

    // 状态：0-下线，1-上线
    @Column(name = "status")
    private Integer status;

    // 描述
    @Column(name = "`desc`")
    private String desc;

    // 创建时间
    @CreatedDate
    @Column(name = "create_time")
    private Timestamp createTime;

    // 更新时间
    @LastModifiedDate
    @Column(name = "update_time")
    private Timestamp updateTime;

    public void copy(BossProductServiceEntity source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
