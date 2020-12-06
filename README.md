# spring-boot-jpa-cascade
Jpa 实际关系注解测试

@ManyToMany注解版。当前还未对更新及保存做测试。后续进行
该注解适合单方维护多对多关系的情况。在维护方使用@JoinTable注解。
在被维护方使用@ManyToMany(mappedBy = "多方关联属性名")

内容调整很简单，只需要传要关联那些内容即可
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
	"bossServices": [
		{
			"id": 2,
			"code": "666100003",
			"name": "XX增值包",
			"status": 1,
			"desc": null,
			"createTime": 1592560857000,
			"updateTime": null
		},
		{
			"id": 4,
			"code": "666100002",
			"name": "XX乐园增值包",
			"status": 1,
			"desc": null,
			"createTime": 1592560850000,
			"updateTime": null
		},
		{
			"id": 3,
			"code": "666100001",
			"name": "VIP影视增值包",
			"status": 1,
			"desc": null,
			"createTime": 1592560854000,
			"updateTime": null
		}
	]
}
```
