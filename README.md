# spring-boot-jpa-cascade

Jpa 实际关系注解测试

@ManyToOne @OneToMany注解版本。前端传数据时，关联关系可不包含当前实体的属性
使用

```java
import org.hibernate.annotations.Where;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.OrderColumn;

@OneToMany
@JoinColumn
// 排序 加到sql上
@OrderBy(value = "sequence DESC, id ASC")
// 前端传的实体会按照此顺序排列，影响jpa级联更新的顺序
@OrderColumn("sequence DESC")
// 筛选 加到sql上，慎用这个，因为这个对所有的select生效，导致及联更新时忽略掉为0的，从而出现问题(!=this.condition的会重复)，但如果业务中只关注 = condition 的，也能用这个
@Where(clause = " status = 1 ")
// 注意类型用Set会重新排序。导入OrderBy无效。但级联更新正常
private Set<Role> roles;
// 类型用List时，OrderBy正常。但级联更新有问题(忘了啥问题了)。对于只读的业务可以用List，否则用Set，因为排序可交由前端
//private List<Role> roles;
```

新增时/即Create，可携带关联关系。关联关系中不包含产品相关属性。 保存时，会先add产品id为null的关联记录。再执行update 新增商品示例

```json5

{
  "name": "包月-银卡VIP",
  "type": 2,
  "bossProductServiceEntities": [
    {
      "bossServiceEntity": {
        "id": 3
      },
      "status": 1,
      "sequence": 3
    },
    {
      "bossServiceEntity": {
        "id": 4
      },
      "status": 0,
      "sequence": 1
    }
  ]
}
```

保存json示例（与下面调整类似） 直接修改bossProductServiceEntities中值， 执行update后生效

```json5
{
  "id": 1,
  "name": "包月",
  "type": 2,
  "bossProductServiceEntities": [
    {
      "id": 10,
      "bossServiceEntity": {
        "id": 3
      },
      "status": 0,
      "sequence": 2
    },
    {
      "id": 9,
      "bossServiceEntity": {
        "id": 4
      },
      "status": 0,
      "sequence": 1
    }
  ]
}
```

新增关联时/即Update，有两种方式
- 将关联表记录的id置空，这样会先清空原关联记录，然后新增，这种最简单，且针对需要update或不变的关联记录，不需要传bossProductEntity.id（毕竟是全清空重建）
```json5
{
  "id": 1,
  "name": "包月-银卡VIP",
  "type": 2,
  "bossProductServiceEntities": [
    {
      "bossServiceEntity": {
        "id": 3
      },
      "status": 1,
      "sequence": 3
    },
    {
      "bossServiceEntity": {
        "id": 5
      },
      "status": 1,
      "sequence": 5
    }
  ]
}
```
- 有关联表记录id的传其id，则该记录需包含所有必须的项，包括bossProductEntity.id，这个会比对并根据需要update/delete，针对无关联记录id的create

```json5
{
  "id": 1,
  "name": "包月-银卡VIP",
  "type": 2,
  "bossProductServiceEntities": [
    {
      "id": 10,
      "bossProductEntity": {
        "id": 1,
      },
      "bossServiceEntity": {
        "id": 3
      },
      "status": 1,
      "sequence": 3
    },
    {
      "bossServiceEntity": {
        "id": 4
      },
      "status": 0,
      "sequence": 1
    }
  ]
}
```

####分表后

###### 产品列表（分库分表） 多个库，多次查询

````json5
{
  "content": [
    {
      "id": 2,
      "name": "VIP影视增值包月0.01元",
      "type": 2,
      "bossProductServiceEntities": [
        {
          "id": 3,
          "bossProductEntity": {
            "id": 2,
            "name": "VIP影视增值包月0.01元",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 3,
            "name": "VIP影视增值包"
          },
          "status": 1,
          "sequence": 20
        },
        {
          "id": 2,
          "bossProductEntity": {
            "id": 2,
            "name": "VIP影视增值包月0.01元",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 4,
            "name": "优宝乐园增值包"
          },
          "status": 1,
          "sequence": 1
        },
        {
          "id": 24,
          "bossProductEntity": {
            "id": 2,
            "name": "VIP影视增值包月0.01元",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 1,
            "name": "服务包爱上测试"
          },
          "status": 1,
          "sequence": 8
        }
      ]
    },
    {
      "id": 4,
      "name": "爱看包月0.01元",
      "type": 2,
      "bossProductServiceEntities": [
        {
          "id": 13,
          "bossProductEntity": {
            "id": 4,
            "name": "爱看包月0.01元",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 1,
            "name": "服务包爱上测试"
          },
          "status": 1,
          "sequence": 21
        }
      ]
    },
    {
      "id": 8,
      "name": "包月",
      "type": 3,
      "bossProductServiceEntities": []
    },
    {
      "id": 10,
      "name": "包月-1222",
      "type": 1,
      "bossProductServiceEntities": []
    },
    {
      "id": 5,
      "name": "测试包",
      "type": 2,
      "bossProductServiceEntities": [
        {
          "id": 15,
          "bossProductEntity": {
            "id": 5,
            "name": "测试包",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 2,
            "name": "爱看增值包"
          },
          "status": 1,
          "sequence": 16
        },
        {
          "id": 16,
          "bossProductEntity": {
            "id": 5,
            "name": "测试包",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 4,
            "name": "优宝乐园增值包"
          },
          "status": 1,
          "sequence": 24
        },
        {
          "id": 17,
          "bossProductEntity": {
            "id": 5,
            "name": "测试包",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 3,
            "name": "VIP影视增值包"
          },
          "status": 1,
          "sequence": 6
        }
      ]
    },
    {
      "id": 1,
      "name": "包月-1224",
      "type": 1,
      "bossProductServiceEntities": []
    },
    {
      "id": 3,
      "name": "优宝乐园包月100.01元",
      "type": 3,
      "bossProductServiceEntities": [
        {
          "id": 43,
          "bossProductEntity": {
            "id": 3,
            "name": "优宝乐园包月100.01元",
            "type": 3
          },
          "bossServiceEntity": {
            "id": 5,
            "name": "服务包0625"
          },
          "status": 1,
          "sequence": 28
        },
        {
          "id": 18,
          "bossProductEntity": {
            "id": 3,
            "name": "优宝乐园包月100.01元",
            "type": 3
          },
          "bossServiceEntity": {
            "id": 2,
            "name": "爱看增值包"
          },
          "status": 1,
          "sequence": 15
        },
        {
          "id": 19,
          "bossProductEntity": {
            "id": 3,
            "name": "优宝乐园包月100.01元",
            "type": 3
          },
          "bossServiceEntity": {
            "id": 1,
            "name": "服务包爱上测试"
          },
          "status": 1,
          "sequence": 25
        },
        {
          "id": 42,
          "bossProductEntity": {
            "id": 3,
            "name": "优宝乐园包月100.01元",
            "type": 3
          },
          "bossServiceEntity": {
            "id": 3,
            "name": "VIP影视增值包"
          },
          "status": 1,
          "sequence": 16
        }
      ]
    }
  ],
  "totalElements": 7
}
````

###### 服务包列表（分库）

````json5
{
  "content": [
    {
      "id": 2,
      "name": "爱看增值包",
      "bossProductServiceEntities": [
        {
          "id": 15,
          "bossProductEntity": {
            "id": 5,
            "name": "测试包",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 2,
            "name": "爱看增值包"
          },
          "status": 1,
          "sequence": 16
        },
        {
          "id": 18,
          "bossProductEntity": {
            "id": 3,
            "name": "优宝乐园包月100.01元",
            "type": 3
          },
          "bossServiceEntity": {
            "id": 2,
            "name": "爱看增值包"
          },
          "status": 1,
          "sequence": 15
        }
      ]
    },
    {
      "id": 4,
      "name": "优宝乐园增值包",
      "bossProductServiceEntities": [
        {
          "id": 16,
          "bossProductEntity": {
            "id": 5,
            "name": "测试包",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 4,
            "name": "优宝乐园增值包"
          },
          "status": 1,
          "sequence": 24
        },
        {
          "id": 2,
          "bossProductEntity": {
            "id": 2,
            "name": "VIP影视增值包月0.01元",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 4,
            "name": "优宝乐园增值包"
          },
          "status": 1,
          "sequence": 1
        }
      ]
    },
    {
      "id": 1,
      "name": "服务包爱上测试",
      "bossProductServiceEntities": [
        {
          "id": 19,
          "bossProductEntity": {
            "id": 3,
            "name": "优宝乐园包月100.01元",
            "type": 3
          },
          "bossServiceEntity": {
            "id": 1,
            "name": "服务包爱上测试"
          },
          "status": 1,
          "sequence": 25
        },
        {
          "id": 13,
          "bossProductEntity": {
            "id": 4,
            "name": "爱看包月0.01元",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 1,
            "name": "服务包爱上测试"
          },
          "status": 1,
          "sequence": 21
        },
        {
          "id": 24,
          "bossProductEntity": {
            "id": 2,
            "name": "VIP影视增值包月0.01元",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 1,
            "name": "服务包爱上测试"
          },
          "status": 1,
          "sequence": 8
        }
      ]
    },
    {
      "id": 3,
      "name": "VIP影视增值包",
      "bossProductServiceEntities": [
        {
          "id": 3,
          "bossProductEntity": {
            "id": 2,
            "name": "VIP影视增值包月0.01元",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 3,
            "name": "VIP影视增值包"
          },
          "status": 1,
          "sequence": 20
        },
        {
          "id": 42,
          "bossProductEntity": {
            "id": 3,
            "name": "优宝乐园包月100.01元",
            "type": 3
          },
          "bossServiceEntity": {
            "id": 3,
            "name": "VIP影视增值包"
          },
          "status": 1,
          "sequence": 16
        },
        {
          "id": 17,
          "bossProductEntity": {
            "id": 5,
            "name": "测试包",
            "type": 2
          },
          "bossServiceEntity": {
            "id": 3,
            "name": "VIP影视增值包"
          },
          "status": 1,
          "sequence": 6
        }
      ]
    },
    {
      "id": 5,
      "name": "服务包0625",
      "bossProductServiceEntities": [
        {
          "id": 43,
          "bossProductEntity": {
            "id": 3,
            "name": "优宝乐园包月100.01元",
            "type": 3
          },
          "bossServiceEntity": {
            "id": 5,
            "name": "服务包0625"
          },
          "status": 1,
          "sequence": 28
        }
      ]
    }
  ],
  "totalElements": 5
}
````
