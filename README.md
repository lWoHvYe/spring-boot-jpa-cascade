# spring-boot-jpa-cascade
Jpa 实际关系注解测试

@ManyToMany注解版。使用不恰当会误删其他关联数据。且会自动更改另一方的数据。比如在product的set中放的service对象，会在更新product时，同时更新service。这点需注意

该注解适合单方维护多对多关系的情况。在维护方使用@JoinTable注解。
在被维护方使用@ManyToMany(mappedBy = "多方关联属性名")
若两方都配置@JoinTable，就会出问题。当更新product时，会先移除所有关联关系。然后错误触发移除关联service相关的所有关联关系，之后只添加该product与这些service的关联关系，其他product与这些service的关系却被错误移除掉了。一定不要这么配置。

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
