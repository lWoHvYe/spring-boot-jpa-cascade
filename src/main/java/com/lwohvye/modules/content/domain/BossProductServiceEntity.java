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
@Table(name = "boss_product__service")
public class BossProductServiceEntity implements Serializable {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 产品
//    使用EAGER的好处是在jpa侧，直接通过left outer join一次查出来了。
    // TODO: 2021/1/9   这种配置方式是否可行待验证
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private BossProductEntity bossProductEntity;

    // 服务
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private BossServiceEntity bossServiceEntity;

    // 状态：0-下线，1-上线
    @Column(name = "status")
    private Integer status;

    public void copy(BossProductServiceEntity source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
