package com.lwohvye.modules.content.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author why
 * @date 2020-06-23
 */
@Entity
// TODO: 2020/12/2 造成循环依赖的罪魁祸首竟然是hashCode方法，只使用getter和setter就没问题了
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "boss_product")
public class BossProductEntity implements Serializable {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // CODE
    @Column(name = "code")
    private String code = "bossProduct_" + UUID.randomUUID();

    // 名称
    @Column(name = "name")
    private String name;

    // 类型，1-基础包产品 2-增值包产品 3-套餐产品
    @Column(name = "type")
    private Integer type;

    // 价格，单位：分
    @Column(name = "price")
    private Integer price;

    // 原始价格
    @Column(name = "original_price")
    private Integer originalPrice;

    // 积分
    @Column(name = "points")
    private Integer points;

    // 原始积分
    @Column(name = "original_points")
    private Integer originalPoints;

    // 计费类型：0-FREE（免费），1-PPV（一次性收费），2-SVOD（周期性收费）
    @Column(name = "fee_type")
    private Integer feeType;

    // 计费单位：1-小时 2-天 3-周 4-月 5-季 6-年
    @Column(name = "fee_unit")
    private Integer feeUnit;

    //    计费时长（>0）：-1-永久 其他-具体值
    @Column(name = "fee_duration")
    private Integer feeDuration;

    //    计费生效规则：1-订购立即生效 2-下一自然周期生效
    @Column(name = "fee_effective")
    private Integer feeEffective;

    // 计费失效规则：1-退订立即失效 2-服务期满自动失效 3-退订自然周期满失效
    @Column(name = "fee_invalid")
    private Integer feeInvalid;

    // 开始时间
    @Column(name = "start_time")
    private Timestamp startTime;

    // 过期时间
    @Column(name = "expire_time")
    private Timestamp expireTime;

    // 状态：0-下线，1-上线
    @Column(name = "status")
    private Integer status;

    // 图片
    @Column(name = "img")
    private String img;

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

    @Column(name = "out_product_id")
    private String outProductId;

    // TODO: 2020/12/2 针对ManyToMany做调整亦可解决循环依赖问题。但只适用单方维护关系的场景。且不能都用@JoinTable，被维护方使用@ManyToMany(mappedBy = "多方关联属性名")
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "boss_product__service",
//            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "id")})
//    private List<BossServiceEntity> bossServices;

    // TODO: 2020/12/2 需要级联查询与更新。使用下面这种方式
    @JoinColumn(name = "product_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<BossProductServiceEntity> bossProductServiceEntities;

    //    需要重写set方法
//    针对的报错 A collection with cascade="all-delete-orphan" was no longer referenced by the owning entity instance
    public void setBossProductServiceEntities(Set<BossProductServiceEntity> bossProductServiceEntities) {
        if (this.bossProductServiceEntities == null) {
            this.bossProductServiceEntities = bossProductServiceEntities;
        } else if (this.bossProductServiceEntities != bossProductServiceEntities) {// not the same instance, in other case we can get ConcurrentModificationException from hibernate AbstractPersistentCollection
            this.bossProductServiceEntities.clear();
            if (bossProductServiceEntities != null) {
                this.bossProductServiceEntities.addAll(bossProductServiceEntities);
            }
        }
    }

    public void copy(BossProductEntity source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
