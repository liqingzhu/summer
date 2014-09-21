LtcEarnPanel = function() {
	var userds = new Ext.data.JsonStore({
//		data : userdata,
		url      :__ctxPath+'/back/btc/getbtcbalance.jspx',
		fields   : ["marketid", "balance", "bytecoinqty", "frozenbal","frozenqty", "leftbal","leftqty","marketname"],
		autoLoad : true
	});
	window.disableMarket = function(value){
		var config = {
			url:__ctxPath+'/back/btc/disablemarket.jspx',
			params:{marketid:value},
			method:'POST',
			success:function(resp,option){
//				var panel = Ext.getCmp('BtcEarnPanel');
//				console.log(Ext.getDom('dis'+value));
				Ext.fly('dis'+value).setStyle({color: 'red',background: 'yellow'});    
				Ext.fly('en'+value).setStyle({color: '',background: ''});
			},
			failure:function(resp,option){
				
			}
		};
		Ext.Ajax.request(config);
	};
	
	window.enableMarket = function(value){
		var config = {
			url:__ctxPath+'/back/btc/enablemarket.jspx',
			params:{marketid:value},
			method:'POST',
			success:function(resp,option){
				var panel = Ext.getCmp('BtcEarnPanel');
				Ext.fly('en'+value).setStyle({color: 'red',background: 'yellow'});   
				Ext.fly('dis'+value).setStyle({color: '',background: ''});   
			},
			failure:function(resp,option){}
		};
		Ext.Ajax.request(config);
	};
	var userbalance = new Ext.grid.GridPanel({
		loadMask : {msg:'正在配置各自账户数据,请稍后!'},
		title : '账户信息设置',
		tbar : [/*{
			text : '设置',
			iconCls:'cog_edit',
			handler : function() {
			}
		}, '-',*/ {
			text : '重新加载',
			iconCls:'table_refresh',
			handler : function() {
				var grid = this.ownerCt.ownerCt;
				grid.getStore().reload();
			}
		}/*, '-', {
			text : '设置重新',
			iconCls:'cog_edit',
			handler : function() {
			}
		}*/],
		flex : 1,
		autoHeight : true,
		store : userds,
		colModel : new Ext.grid.ColumnModel({
			defaults : {
				width : 120,
				sortable : true
			},
			columns : [{
				id : 'company',
				header : '市场名称',
				width : 200,
				sortable : true,
				dataIndex : 'marketname'
			}, {
				header : '账户余额',
				dataIndex : 'balance'
			}, {
				header : '比特币余额',
				dataIndex : 'bytecoinqty'
			}, {
				header : '冻结余额',
				dataIndex : 'frozenbal'
			}, {
				header : '冻结数量',
				dataIndex : 'frozenqty'
			},{
				header:'禁用',
				width:80,
				dataIndex:'marketid',
				renderer:function(value,metadata,record,rowIndex,colIndex,store){
					
					return '<a id="dis'+value+'" href="#" onclick="disableMarket('+value+')">禁用</a>'
				}
			},{
				header:'启用',width:80,
				dataIndex:'marketid',
				renderer:function(value,metadata,record,rowIndex,colIndex,store){
					
					return '<a id="en'+value+'" href="#" onclick="enableMarket('+value+')">启用</a>'
				}
			}]
		})
	});
	var propsGrid = new Ext.grid.PropertyGrid({
		tbar : ['->', {
			text : '交易信息初始化',
			iconCls:'cog_edit',
			handler : function() {
				var config = {
					method:'POST',
					url    :__ctxPath+'/back/btc/settradeparam.jspx',
					params:propsGrid.getSource(),
					success:function(response,option){
						console.log(response.responseText);
					},
					failure:function(response,option){
					
					}
				}
				Ext.Ajax.request(config);
			},
			scope:this
		}],
		title : '交易信息设置',
		height : 400,
		flex : 1,
		autoScoll : true,
		autoHeight : true,
		propertyNames : {
			maxqty:'最大交易币数量',
			minqty:'最小交易币数量',
			chargefee:'手续费',
			rate:'汇率',
			diff:'交易差额',
			cursor:'第i个记录',
			depthtime:'交易深度刷新频率(秒)',
			balancetime:'余额刷新频率(分钟)'
		},
		source : {
			maxqty:'1',
			minqty:'0.01',
			chargefee:'0.01',
			rate:'6.08',
			diff:'10',
			cursor:'3',
			depthtime:'10',
			balancetime:'30'
		}
	});
	
	window.clickInfo = function(tradeid){
		// 窗体Windows 用于显示
		var winDepthDetail = new BtcDepthWindow(tradeid);
		    winDepthDetail.show();
	}
	var makeTwins = function(){
		var option = {
			url    :__ctxPath+'/back/btc/makedepth.jspx',
			success: function(response, opts) {
				var obj = Ext.decode(response.responseText);
				var str = '<a href="#" onClick="clickInfo(\''+obj.tradeid+'\')">'+obj.result+'</a>'
		     	Ext.DomHelper.append('btcdepth','<h1>'+str+'</h1>');
		     	Ext.fly('btcdepth').highlight();
//		     	console.log(makeDepth);
			},
			failure: function(response, opts) {
		     	console.log("failure");
			},
			method :'POST'
		};
		Ext.Ajax.request(option);
	}
	var panel1 = new Ext.Panel({
		layout : 'hbox',height:250,border:false,
		flex : 1,
		layoutConfig : {padding : '5',align : 'top'},
		defaults : {margins : '0 5 0 0'},
		items : [userbalance, propsGrid]
	});
	var task = {
	    run: makeTwins,
	    interval: 1000 //1 second
	}
	//depthrunner 深度定时器
	var depthrunner = new Ext.util.TaskRunner();
	var makeDepth = new Ext.Panel(
		{
			flex : 1,autoScroll :true,height:270,
			title:'匹配交易对列',
			html:'<div id="btcdepth">默认为每3秒钟匹配一次交易队列11!</div>',
			tbar:[
				{
					text:'匹配交易对',
					iconCls:'clock_play',
					handler:function(){
						depthrunner.start(task);
					}
				},{
					text:'停止匹配交易对',
					iconCls:'clock_stop',
					handler:function(){
						depthrunner.stop(task);
					}
				}
			]
		}
	);
	var tradeStore = new Ext.data.JsonStore(
		{
			autoLoad:true,
			data:[
				{id:'1',tradeid:'1',status:'1',buymarketid:'1',sellmarketid:'3',buyqty:1,sellqty:2,buyprice:'1.1'},
				{id:'2',tradeid:'1',status:'1',buymarketid:'1',sellmarketid:'3',buyqty:1,sellqty:2,buyprice:'1.1'},
				{id:'3',tradeid:'1',status:'1',buymarketid:'1',sellmarketid:'3',buyqty:1,sellqty:2,buyprice:'1.1'},
				{id:'4',tradeid:'1',status:'1',buymarketid:'1',sellmarketid:'3',buyqty:1,sellqty:2,buyprice:'1.1'},
				{id:'5',tradeid:'1',status:'1',buymarketid:'1',sellmarketid:'3',buyqty:1,sellqty:2,buyprice:'1.1'},
				{id:'47',tradeid:'1',status:'3',buymarketid:'1',sellmarketid:'3',buyqty:2,sellqty:3,buyprice:'2.1'}
			],
			fields:['id','tradeid','status','buymarketid','sellmarketid','buyqty','sellqty']
		}
	);
	var startTrade = function(){
		var config = {
			url    :__ctxPath+'/back/btc/tradebtc.jspx',
			method:'POST',
			success:function(reponse,option){
				var defaultData = {
				    id:'6',tradeid:'6',status:'6',
				    buymarketid:'6',sellmarketid:'6',buyqty:6,
				    sellqty:7,buyprice:'6.1'
				};
				var recId = 0; // 为记录提供一个唯一的id
				var r = new tradeStore.recordType(defaultData, ++recId); // 创建 新记录
				tradeStore.insert(0, r); // 向store中插入一条新记录
			},
			failure:function(reponse,option){
				console.log('没有得到结果，通讯异常!');
			}
		};
		Ext.Ajax.request(config);
	}
	var tradeDepth = new Ext.grid.GridPanel(
		{
			title:'开始进行交易',
			store:tradeStore,
			loadMask : {msg:'正在配置各自账户数据,请稍后!'},
			height:270,
			autoScroll :true,
			tbar:[
				{
					text : '开始进行交易',
					iconCls : 'lightning_go',
					scope:this,
					handler : startTrade
				}, '-',{
					text : '停止进行交易',
					iconCls : 'lightning_delete',
					handler : function() {
						
					}
				},'->',{
					text : '查看交易明细',
					iconCls : 'zoom',
					handler : function() {
						
					}
				}
			],
			colModel : new Ext.grid.ColumnModel({
				defaults : {width : 50},
				columns : [{
						header : '买入',
						dataIndex : 'buymarketid'
					}, {
						header : '数量',
						dataIndex : 'buyqty'
					}, {
						header : '价格',
						dataIndex : 'buyprice'
					}, {
						header : '数量',
						dataIndex : 'sellqty'
					}, {
						header : '卖出',
						dataIndex : 'sellmarketid'
					}, {
						header : '状态',
						dataIndex : 'status'
					},{
						header:'禁用',
						width:80,
						dataIndex:'tradeid',
						renderer:function(value,metadata,record,rowIndex,colIndex,store){
							
							return '<a id="dis'+value+'" href="#" onclick="disableMarket('+value+')">禁用</a>'
						}
					},{
						header:'启用',width:80,
						dataIndex:'tradeid',
						renderer:function(value,metadata,record,rowIndex,colIndex,store){
							
							return '<a id="en'+value+'" href="#" onclick="enableMarket('+value+')">启用</a>'
						}
					}
				]
			})
		}
	);
	var panel2 = new Ext.Panel({
		
		layout : 'hbox',
		flex : 2,
		border:false,
		layoutConfig : {
			padding : '5',
			align : 'top'
		},
		defaults : {
			margins : '0 5 0 0'
		},
		items : [makeDepth, tradeDepth]
	});
	var left = new Ext.Panel(
		{
			border:false,
			flex : 3,
			layout : {
				type : 'vbox',
				padding : '5',
				align : 'stretch'
			},
			defaults : {
				margins : '0 0 5 0'
			},
			items : [userbalance, panel2]
		}
	);
	
	
	
	var config = {
		layout : {
			type : 'hbox',
			padding : '5',
			align : 'stretch'
		},
		defaults : {
			margins : '0 0 5 0'
		},
		items : [left, propsGrid],
		title : 'LTC搬砖',
		iconCls : 'money_pound',
		id : 'BtcEarnPanel'
	};
	/*var config = {
		layout : {
			type : 'vbox',
			padding : '5',
			align : 'stretch'
		},
		defaults : {
			margins : '0 0 5 0'
		},
		items : [panel1, panel2],
		title : 'BTC搬砖1',
		iconCls : 'money_dollar',
		id : 'BtcEarnPanel'
	};*/
	LtcEarnPanel.superclass.constructor.call(this, config);
};
Ext.extend(LtcEarnPanel, Ext.Panel);