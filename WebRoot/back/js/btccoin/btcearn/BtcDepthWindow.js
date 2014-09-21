BtcDepthWindow = function(tradeid){
	var sellurl = __ctxPath+'/back/btc/gettwindepthdata.jspx';
	var selldata = [
		{
			marketname:'BTCChina',price:1000.1,qty:10.11
		},{
			marketname:'OKCoin',price:901.12,qty:10.12
		},{
		    marketname:'FireCoin',price:787.1,qty:10.13
		}];
	var sellstore = new Ext.data.JsonStore(
		{
//			data:selldata,
			url:sellurl,
			baseParams:{pkid:tradeid,tradetype:1},
			autoLoad:true,
			fields:['marketname','price','qty']
		}
	);
	var buystore = new Ext.data.JsonStore(
		{
//			data:selldata,
			url:sellurl,
			baseParams:{pkid:tradeid,tradetype:0},
			autoLoad:true,
			fields:['marketname','price','qty']
		}
	);
	var num = new  Ext.grid.RowNumberer();
	var cm = [
		num,{
			header:"市场",dataIndex:"marketname",width:80
		},{
			header:"价格",dataIndex:"price",width:50
		},{
			header:"数量",dataIndex:"qty",width:50
		}
	];
	var sellgrid = new Ext.grid.GridPanel(
		{
			title:'委卖交易深度',
			flex : 1,
			store:sellstore,
			width:210,
			height:140,
			columns:cm
		}
	);
	var buygrid = new Ext.grid.GridPanel(
		{
			title:'委买交易深度',
			flex : 1,
			store:buystore,
			width:210,
			height:140,
			columns:cm
		}
	);
	
	var config = {
		constrainHeader:true,
		constrain:true,
		modal : true,
		layout : 'hbox',
		layoutConfig : {
			padding : '5',
			align : 'top'
		},
		defaults : {
			margins : '0 5 0 0'
		},
		items : [sellgrid,buygrid],
		closeAction:'hide',
		title:'交易深度获取列表',
		width:450,
		height:160
	}
	BtcDepthWindow.superclass.constructor.call(this, config);
}
Ext.extend(BtcDepthWindow, Ext.Window);