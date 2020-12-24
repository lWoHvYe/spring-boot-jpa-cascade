package com.lwohvye.modules.content.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    // 名称
    @Column(name = "name")
    private String name;

    // 类型，1-基础包产品 2-增值包产品 3-套餐产品
    @Column(name = "type")
    private Integer type;

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
