package com.lwohvye.modules.content.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;
import org.springframework.context.annotation.Lazy;
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
//@NamedEntityGraph(name = "service-details", attributeNodes = {@NamedAttributeNode("bossProductServiceEntities")})
@Table(name = "boss_service")
public class BossServiceEntity implements Serializable {

    // ID
    @Id
    @Column(name = "id")
    private Long id;

    // 名称
    @Column(name = "name")
    private String name;

    // TODO: 2020/12/2 针对ManyToMany做调整亦可解决循环依赖问题。但只适用于单方维护关系的场景。
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "boss_product__service",
//            joinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
//    private List<BossProductEntity> bossProducts;

    // TODO: 2020/12/2 需要级联查询与更新。使用下面这种方式
    @Lazy
    @JoinColumn(name = "service_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    sql排序
    @OrderBy(value = "sequence ASC , id desc ")
//    前端实体排序。一般用于后续处理时的顺序
    @OrderColumn(name = "sequence")
//    sql筛选
    @Where(clause = " status = 1 ")
    // 注意类型用Set会重新排序。导入OrderBy无效。但级联更新正常
    // 类型用List时，OrderBy正常。但级联更新有问题。对于只读的业务可以用List，否则用Set，因为排序可交由前端
    private Set<BossProductServiceEntity> bossProductServiceEntities;

    //    需要重写set方法
//    针对的报错 A collection with cascade="all-delete-orphan" was no longer referenced by the owning entity instance
    public void setBossProductServiceEntities(Set<BossProductServiceEntity> bossProductServiceEntities) {
        if (ObjectUtil.isNull(this.bossProductServiceEntities)) {
            this.bossProductServiceEntities = bossProductServiceEntities;
        } else if (ObjectUtil.notEqual(this.bossProductServiceEntities, bossProductServiceEntities)) {// not the same instance, in other case we can get ConcurrentModificationException from hibernate AbstractPersistentCollection
            this.bossProductServiceEntities.clear();
            if (ObjectUtil.isNotNull(bossProductServiceEntities)) {
                this.bossProductServiceEntities.addAll(bossProductServiceEntities);
            }
        }
    }

    public void copy(BossServiceEntity source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
