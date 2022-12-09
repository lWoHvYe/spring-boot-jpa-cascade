package com.lwohvye.modules.content.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Set;

/**
 * @author why
 * @date 2020-06-23
 */
@Entity
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

    // TODO: 2020/12/2 针对ManyToMany做调整亦可解决循环依赖问题，在一侧重写toString排除掉关联属性
    // cascade = CascadeType.ALL 在新增时不能包含关联关系，更新时可包含关联关系且可对其进行更新，删除时会自动删除另一方（所以不能使用CascadeType.REMOVE）
    // 推荐用下面的MERGE，可考虑REFRESH
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "boss_product__service",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "id")})
    private Set<BossServiceEntity> bossServices;

    public void copy(BossProductEntity source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
