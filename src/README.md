两个部分：后台管理admin和前端页面user

都有DAO类

admin:
	dao.impl
	service
	servlet
	domain
	
user:
	dao.impl
	servlet
	service
	damain
	
后台实现：
	登录
	管理分类
		添加
		删除
		修改
	管理商品
		添加
		删除
		修改
	
	
****************************************

2020-05-02
需求变更：
	更多功能->添加商品、高级搜索  变为分类旁下拉列表
	添加分类  变为分类旁按钮功能
	编辑、删除  横行变为竖行
	备注框，单击显示站点提示alert

bug备注:
	因为选择条件的错误，add.jsp不能显示导航栏与下方版权说明
	<由于需求更改，此BUG可以回滚，在新需求下不会造成影响>
	
****************************************
	
2020-05-03
需求变更：
	添加-> 增加“元件参数”
	显示  元件参数，方式与  备注相同
	
新增需求：
	新增“入库”“出库”需求
	并添加日志效果，每次操作，记录日志:
		xxxx-xx-xx xx:xx:xx ‘goods.gname’入库xx个，出库xx个，库存xx个

****************************************
	
	
	
	