# spring-boot-jpa-cascade
Jpa 实际关系注解测试


@ManyToOne  @OneToMany注解版本

保存json示例
直接修改bossProductServiceEntities中值，
执行update后生效
```json5
{
	"id": 1,
	"code": "Fee_Month_TEST_AS",
	"name": "包月",
	"type": 2,
	"price": null,
	"originalPrice": null,
	"points": null,
	"originalPoints": null,
	"feeType": 1,
	"feeUnit": 3,
	"feeDuration": null,
	"feeEffective": null,
	"feeInvalid": null,
	"startTime": null,
	"expireTime": null,
	"status": 1,
	"img": "[]",
	"desc": "测试包月",
	"createTime": 1591930079000,
	"updateTime": 1592998706000,
	"outProductId": null,
	"bossProductServiceEntities": [
		{
			"id": 10,
			"bossProductEntity": {
				"id": 1,
				"code": "Fee_Month_TEST_AS",
				"name": "包月",
				"type": 2,
				"price": null,
				"originalPrice": null,
				"points": null,
				"originalPoints": null,
				"feeType": 1,
				"feeUnit": 3,
				"feeDuration": null,
				"feeEffective": null,
				"feeInvalid": null,
				"startTime": null,
				"expireTime": null,
				"status": 1,
				"img": "[]",
				"desc": "测试包月",
				"createTime": 1591930079000,
				"updateTime": 1592998706000,
				"outProductId": null
			},
			"bossServiceEntity": {
				"id": 3,
				"code": "666100001",
				"name": "VIP影视增值包",
				"status": 1,
				"desc": null,
				"createTime": 1592560854000,
				"updateTime": null
			},
			"status": 0,
			"desc": null,
			"createTime": null,
			"updateTime": null
		},
		{
			"id": 9,
			"bossProductEntity": {
				"id": 1,
				"code": "Fee_Month_TEST_AS",
				"name": "包月",
				"type": 2,
				"price": null,
				"originalPrice": null,
				"points": null,
				"originalPoints": null,
				"feeType": 1,
				"feeUnit": 3,
				"feeDuration": null,
				"feeEffective": null,
				"feeInvalid": null,
				"startTime": null,
				"expireTime": null,
				"status": 1,
				"img": "[]",
				"desc": "测试包月",
				"createTime": 1591930079000,
				"updateTime": 1592998706000,
				"outProductId": null
			},
			"bossServiceEntity": {
				"id": 4,
				"code": "666100002",
				"name": "XX乐园增值包",
				"status": 1,
				"desc": null,
				"createTime": 1592560850000,
				"updateTime": null
			},
			"status": 0,
			"desc": null,
			"createTime": null,
			"updateTime": 1593018577000
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
	"code": "Fee_Month_TEST_AS",
	"name": "包月",
	"type": 2,
	"price": null,
	"originalPrice": null,
	"points": null,
	"originalPoints": null,
	"feeType": 1,
	"feeUnit": 3,
	"feeDuration": null,
	"feeEffective": null,
	"feeInvalid": null,
	"startTime": null,
	"expireTime": null,
	"status": 1,
	"img": "[]",
	"desc": "测试包月",
	"createTime": 1591930079000,
	"updateTime": 1592998706000,
	"outProductId": null,
	"bossProductServiceEntities": [
		{
			"id": 9,
			"bossProductEntity": {
				"id": 1,
				"code": "Fee_Month_TEST_AS",
				"name": "包月",
				"type": 2,
				"price": null,
				"originalPrice": null,
				"points": null,
				"originalPoints": null,
				"feeType": 1,
				"feeUnit": 3,
				"feeDuration": null,
				"feeEffective": null,
				"feeInvalid": null,
				"startTime": null,
				"expireTime": null,
				"status": 1,
				"img": "[]",
				"desc": "测试包月",
				"createTime": 1591930079000,
				"updateTime": 1592998706000,
				"outProductId": null
			},
			"bossServiceEntity": {
				"id": 4,
				"code": "666000002",
				"name": "XX乐园增值包",
				"status": 1,
				"desc": null,
				"createTime": 1592560850000,
				"updateTime": null
			},
			"status": 0,
			"desc": null,
			"createTime": null,
			"updateTime": 1593018577000
		},
		{
			"bossProductEntity": {
				"id": 1,
				"code": "Fee_Month_TEST_AS",
				"name": "包月",
				"type": 2,
				"price": null,
				"originalPrice": null,
				"points": null,
				"originalPoints": null,
				"feeType": 1,
				"feeUnit": 3,
				"feeDuration": null,
				"feeEffective": null,
				"feeInvalid": null,
				"startTime": null,
				"expireTime": null,
				"status": 1,
				"img": "[]",
				"desc": "测试包月",
				"createTime": 1591930079000,
				"updateTime": 1592998706000,
				"outProductId": null
			},
			"bossServiceEntity": {
				"id": 2,
				"code": "666100003",
				"name": "XX增值包",
				"status": 1,
				"desc": null,
				"createTime": 1592560857000,
				"updateTime": null
			},
			"status": 1,
			"desc": null,
			"createTime": null,
			"updateTime": 1593002632000
		}
	]
}
```
