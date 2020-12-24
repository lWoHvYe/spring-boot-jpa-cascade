# spring-boot-jpa-cascade
Jpa 实际关系注解测试


@ManyToOne  @OneToMany注解版本

新增时，可携带关联关系。关联关系中不包含产品相关属性。
保存时，会先add产品id为null的关联记录。再执行update
新增商品示例
```json5

{
  "name": "包月",
  "type": 2,
  "bossProductServiceEntities": [
    {
      "bossServiceEntity": {
        "id": 3
      },
      "status": 0
    },
    {
      "bossServiceEntity": {
        "id": 4
      },
      "status": 0
    }
  ]
}
```

保存json示例（与下面调整类似）
直接修改bossProductServiceEntities中值，
执行update后生效
```json5
{
	"id": 1,
	"name": "包月",
	"type": 2,
	"bossProductServiceEntities": [
		{
			"id": 10,
			"bossProductEntity": {
				"id": 1
			},
			"bossServiceEntity": {
				"id": 3
			},
			"status": 0
		},
		{
			"id": 9,
			"bossProductEntity": {
				"id": 1
			},
			"bossServiceEntity": {
				"id": 4
			},
			"status": 0
		}
	]
}
```
新增关联，只需传包含id为空的即可。
若移除关联关系，只需要在传到后端的数据中删除对应记录即可。
后端会根据与原数据的比对结果进行添加或删除
```json5
{
	"id": 1,
	"name": "包月",
	"type": 2,
	"bossProductServiceEntities": [
		{
			"id": 9,
			"bossProductEntity": {
				"id": 1
			},
			"bossServiceEntity": {
				"id": 4
			},
			"status": 0
		},
		{
			"bossProductEntity": {
				"id": 1
			},
			"bossServiceEntity": {
				"id": 2
			},
			"status": 1
		}
	]
}
```
