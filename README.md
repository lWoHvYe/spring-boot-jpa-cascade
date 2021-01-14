# spring-boot-jpa-cascade

Jpa 实际关系注解测试

@ManyToOne @OneToMany注解版本。前端传数据时，关联关系可不包含当前实体的属性
使用

```java
import org.hibernate.annotations.Where;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;

@OneToMany
@JoinColumn
// 排序 加到sql上
@OrderBy(value = "sequence DESC, id ASC")
// 前端传的实体会按照此顺序排列
@OrderColumn("sequence DESC")
// 筛选 加到sql上
@Where(clause = " status = 1 ")
private Set<Role> roles;
```
新增时，可携带关联关系。关联关系中不包含产品相关属性。 保存时，会先add产品id为null的关联记录。再执行update 新增商品示例

```json5

{
  "name": "包月",
  "type": 2,
  "bossProductServiceEntities": [
    {
      "bossServiceEntity": {
        "id": 3
      },
      "status": 0,
      "sequence": 2 
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

新增关联，只需传包含id为空的即可。 若移除关联关系，只需要在传到后端的数据中删除对应记录即可。 后端会根据与原数据的比对结果进行添加或删除

```json5
{
  "id": 1,
  "name": "包月",
  "type": 2,
  "bossProductServiceEntities": [
    {
      "id": 9,
      "bossServiceEntity": {
        "id": 4
      },
      "status": 0,
      "sequence": 1
    },
    {
      "bossServiceEntity": {
        "id": 2
      },
      "status": 1,
      "sequence": 2
    }
  ]
}
```
