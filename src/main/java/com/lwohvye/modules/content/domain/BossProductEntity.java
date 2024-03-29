package com.lwohvye.modules.content.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Set;

/**
 * @author why
 * @date 2020-06-23
 */
@Entity
//@Accessors(chain = true) toEntity 中set系列方法对方法返回值都有要求，要为void
// TODO: 2020/12/2 造成循环依赖的罪魁祸首竟然是hashCode方法，只使用getter和setter就没问题了
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
//@NamedEntityGraph(name = "product-details", attributeNodes = {@NamedAttributeNode("bossProductServiceEntities")})
@Table(name = "boss_product")
public class BossProductEntity implements Serializable {

    // ID
    @Id
    @Column(name = "id")
    private Long id;

    // 名称
    @Column(name = "name")
    private String name;

    // 类型，1-基础包产品 2-增值包产品 3-套餐产品
    @Column(name = "type")
    private Integer type;

    // TODO: 2020/12/2 针对ManyToMany做调整亦可解决循环依赖问题。但只适用单方维护关系的场景。且不能都用@JoinTable，被维护方使用@ManyToMany(mappedBy = "多方关联属性名")
    // TODO: 2020/12/2 针对ManyToMany做调整亦可解决循环依赖问题，在一侧重写toString排除掉关联属性
    // cascade = CascadeType.ALL 在新增时不能包含关联关系，更新时可包含关联关系且可对其进行更新，删除时会自动删除另一方（所以不能使用CascadeType.REMOVE）
    // 推荐用下面的MERGE，可考虑REFRESH
    @JsonManagedReference
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "boss_product__service",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "id")})
    private Set<BossServiceEntity> bossServices;

    // TODO: 2020/12/2 需要级联查询与更新。使用下面这种方式
//    @Lazy注解注解的作用主要是减少springIOC容器启动的加载时间
//    当出现注入的循环依赖时，也可以添加@Lazy。但这里出现但并不是注入，所以加上无效。问题出现在序列化（toString()）时
//    @Lazy
    @JoinColumn(name = "product_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    sql排序
    @OrderBy(value = "sequence ASC , id desc ")
//    前端实体排序。一般用于后续处理时的顺序
    @OrderColumn(name = "sequence")
//  筛选 加到sql上，慎用这个，因为这个对所有的select生效，导致及联更新时忽略掉为0的，从而导致为0的出现重复，除非业务不关注为0的
//    @Where(clause = " status = 1 ")
    // 注意类型用Set会重新排序。导入OrderBy无效。但级联更新正常
//    使用Set存取顺序不一致是因为使用的实现是HashSet，
    // 类型用List时，OrderBy正常。但返回的数据中有null。建议用Set
    private Set<BossProductServiceEntity> bossProductServiceEntities;

//    @JoinColumn(name = "product_code")
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private Set<BossProductServiceEntity> bossProductServiceEntities;

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

    public void copy(BossProductEntity source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }

    @Override
    public String toString() {
        return "BossProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
