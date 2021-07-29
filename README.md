# weixingOrder
一个微信点餐系统(部分功能需要权限，后续继续完善)

![Screenshot 2021 07 29 225519](https://z3.ax1x.com/2021/07/29/Wqgwzn.jpg)

微信公众号 ： 鸣人哈说Java

![WfAWgf.png](https://z3.ax1x.com/2021/07/26/WfAWgf.md.png)

**买家（手机端）**

下单--查询订单--取消订单--支付订单

**卖家（PC端）**

类目增删--商品上下架--接单--查询订单--取消订单

![WfATEj.png](https://z3.ax1x.com/2021/07/26/WfATEj.md.png)

![WfA7Us.png](https://z3.ax1x.com/2021/07/26/WfA7Us.md.png)



项目展示：（学生党个人无法申请微信支付账号以及服务号，展示只能本地展示）

![WfeQi9.png](https://z3.ax1x.com/2021/07/26/WfeQi9.png)



## API 调试（postman）

### \###商品列表

```
GET /sell/buyer/product/list
```

参数

```
无
```

返回

![WfA5Dg.png](https://z3.ax1x.com/2021/07/26/WfA5Dg.png)

\###创建订单

```
POST /sell/buyer/order/create
```

参数

```
name: "张三"
phone: "18868822111"
address: "mengzhiayuan"
openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
items: [{
    productId: "1423113435324",
    productQuantity: 2 //购买数量
}]
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": {
      "orderId": "147283992738221" 
  }
}
```

\###订单列表

```
GET /sell/buyer/order/list
```

参数

```
openid: 18eu2jwk2kse3r42e2e
page: 0 //从第0页开始
size: 10
```

![WfAfv8.png](https://z3.ax1x.com/2021/07/26/WfAfv8.md.png)

\###查询订单详情

```
GET /sell/buyer/order/detail
```

参数

```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

\###取消订单

```
POST /sell/buyer/order/cancel
```

参数

```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```

\###获取openid

```
重定向到 /sell/wechat/authorize
```

参数

```
returnUrl: http://xxx.com/abc  //【必填】
```

返回

```
http://xxx.com/abc?openid=oZxSYw5ldcxv6H0EU67GgSXOUrVg
```

\###支付订单(这需要微信支付权限和服务号)

![WfA4KS.png](https://z3.ax1x.com/2021/07/26/WfA4KS.png)

# 微信点餐数据库

![WfAIbQ.png](https://z3.ax1x.com/2021/07/26/WfAIbQ.md.png)

```sql
-- 类目
create table `product_category` (
    `category_id` int not null auto_increment,
    `category_name` varchar(64) not null comment '类目名字',
    `category_type` int not null comment '类目编号',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`category_id`),
    UNIQUE KEY `uqe_category_type` (`category_type`)
);

-- 商品
create table `product_info` (
    `product_id` varchar(32) not null,
    `product_name` varchar(64) not null comment '商品名称',
    `product_price` decimal(8,2) not null comment '单价',
    `product_stock` int not null comment '库存',
    `product_description` varchar(64) comment '描述',
    `product_icon` varchar(512) comment '小图',
    `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
    `category_type` int not null comment '类目编号',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`product_id`)
);

-- 订单
create table `order_master` (
    `order_id` varchar(32) not null,
    `buyer_name` varchar(32) not null comment '买家名字',
    `buyer_phone` varchar(32) not null comment '买家电话',
    `buyer_address` varchar(128) not null comment '买家地址',
    `buyer_openid` varchar(64) not null comment '买家微信openid',
    `order_amount` decimal(8,2) not null comment '订单总金额',
    `order_status` tinyint(3) not null default '0' comment '订单状态, 默认为新下单',
    `pay_status` tinyint(3) not null default '0' comment '支付状态, 默认未支付',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`order_id`),
    key `idx_buyer_openid` (`buyer_openid`)
);

-- 订单商品
create table `order_detail` (
    `detail_id` varchar(32) not null,
    `order_id` varchar(32) not null,
    `product_id` varchar(32) not null,
    `product_name` varchar(64) not null comment '商品名称',
    `product_price` decimal(8,2) not null comment '当前价格,单位分',
    `product_quantity` int not null comment '数量',
    `product_icon` varchar(512) comment '小图',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`detail_id`),
    key `idx_order_id` (`order_id`)
);

-- 卖家(登录后台使用, 卖家登录之后可能直接采用微信扫码登录，不使用账号密码)
create table `seller_info` (
    `seller_id` varchar(32) not null,
    `username` varchar(32) not null,
    `password` varchar(32) not null,
    `openid` varchar(64) not null comment '微信openid',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`seller_id`)
) comment '卖家信息表';
```

