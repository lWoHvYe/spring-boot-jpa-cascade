package com.lwohve.spring.boot.jpa.modules.content.domain;

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
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author why
 * @date 2020-06-23
 */
@Entity
// TODO: 2020/12/2 造成循环依赖的罪魁祸首竟然是toString方法，只使用getter和setter就没问题了
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "boss_service")
public class BossServiceEntity implements Serializable {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // CODE
    @Column(name = "code")
    private String code = "bossService_" + UUID.randomUUID();

    // 名称
    @Column(name = "name")
    private String name;

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

    // TODO: 2020/12/2 针对ManyToMany做调整亦可解决循环依赖问题。但无法级联保存。存疑。
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "boss_product__service",
            joinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<BossProductEntity> bossProducts;

    // TODO: 2020/12/2 需要级联查询与更新。使用下面这种方式
//    @JoinColumn(name = "service_id")
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private Set<BossProductServiceEntity> bossProductServiceEntities = new HashSet<>();

    public void copy(BossServiceEntity source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
