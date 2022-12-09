package com.lwohvye.modules.content.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "boss_service")
public class BossServiceEntity implements Serializable {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 名称
    @Column(name = "name")
    private String name;

    // TODO: 2020/12/2 针对ManyToMany做调整亦可解决循环依赖问题。但该注解只适合单向维护多对多关系的情况。维护方使用@JoinTable，被维护方使用mappedBy
    @JsonIgnore
//    若两方都配置@JoinTable，就会出问题。当更新product时，会先移除所有关联关系。然后错误触发移除关联service相关的关联关系
    @ManyToMany(mappedBy = "bossServices")
//    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JoinTable(name = "boss_product__service",
//            joinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private Set<BossProductEntity> bossProducts;

    public void copy(BossServiceEntity source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }


    // 在这一侧剔除关联的属性，比较适合@ManyToMany这种维护方式
    @Override
    public String toString() {
        return "BossServiceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
