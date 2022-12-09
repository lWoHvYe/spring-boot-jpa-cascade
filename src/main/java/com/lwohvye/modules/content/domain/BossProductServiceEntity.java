package com.lwohvye.modules.content.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    @JoinColumn(name = "product_id")
    private BossProductEntity bossProductEntity;

//    referencedColumnName 指定另一实体关联的字段，默认是主键，如果不是需要进行指定，比如下面这个，指定通过非主键的code进行关联
//    name 指定本实体中关联用字段，referencedColumnName 指定另一实体进行关联的字段
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "product_code",referencedColumnName = "code")
//    private BossProductEntity bossProductEntity;


    // insertable = false, updatable = false 不要随便使用不了解的配置。这两个设置为该字段不设置值、不更新值。类似交由数据库默认值维护
    // 服务
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private BossServiceEntity bossServiceEntity;

    // 状态：0-下线，1-上线
    @Column(name = "status")
    private Integer status;

    //    顺序
    @Column(name = "sequence")
    private Integer sequence;

    public void copy(BossProductServiceEntity source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
