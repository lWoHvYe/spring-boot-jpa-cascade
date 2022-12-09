# spring-boot-jpa-cascade
Jpa 实际关系注解测试

@ManyToMany注解版。使用不恰当会误删其他关联数据。且会自动更改另一方的数据。比如在product的set中放的service对象，会在更新product时，同时更新service。这点需注意

该注解适合单方维护多对多关系的情况。在维护方使用@JoinTable注解。

在被维护方使用@ManyToMany(mappedBy = "多方关联属性名")

若两方都配置@JoinTable，就会出问题。当更新product时，会先移除所有关联关系。然后错误触发移除关联service相关的所有关联关系，之后只添加该product与这些service的关联关系，其他product与这些service的关系却被错误移除掉了。一定不要这么配置。

```java

public enum CascadeType {
    ALL,
    PERSIST, // 新增支配方时，若带有关系关系，会报detached entity passed to persist；更新支配方时，若关联的被支配方不存在，会insert，但insert的结果好像有问题。
    MERGE, // 新增支配方时，可带着关联关系(被支配方需存在)；更新支配方时，除了关联关系，还可以对被支配方update (未传的被支配方字段 会被置空)，且若被支配方不存在，会新增；
    REMOVE, // 删除支配方时，除了关联关系，还会将被支配方delete，这个慎用
    REFRESH, // 不清楚用途
    DETACH; // 删除支配方时，若其有与其相关的外键，会撤销所以相关的外键关联

    private CascadeType() {
    }
}
```

内容调整很简单，只需要传要关联那些内容即可
```json5
{
	"id": 1,
	"name": "包月",
	"type": 2,
	"bossServices": [
		{
			"id": 2,
			"name": "XX增值包"
		},
		{
			"id": 4,
			"name": "XX乐园增值包"
		},
		{
			"id": 3,
			"name": "VIP影视增值包"
		}
	]
}
```

新增时，容Cascade包含Present且携带关联关系，否则将收获异常: detached entity passed to persist
```json5
{
  "name": "包纪-至尊黄金VIP",
  "type": 1
}

```
而修改时是可以同步修改另一侧属性的
```json5
{
  "id": "6",
  "name": "包纪-至尊黄金VIP",
  "type": 2,
  "bossServices": [
    {
      "id": 2,
      "name": "YouTube"
    },
    {
      "id": 4,
      "name": "Apple TV"
    }
  ]
}
```
