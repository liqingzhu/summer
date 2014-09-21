DiaryPanelView=Ext.extend(Ext.ux.Portlet,{
	tools:null,
	constructor :function (a){
		Ext.applyIf(this,a);
		this.initTool();
		DiaryPanelView.superclass.constructor.call(this,{
			id:"DiaryPanelView",
			title:"我的日志",
			iconCls:"menu-diary",
			tools:this.tools,
			autoLoad:false/*,
			autoLoad:{
				autoLoad:false,
				url:__ctxPath+"/system/displayDiary.do?start=0&limit=8"
			}*/
		});
	},
	initTool:function (){
		this.tools=[{
			id:"refresh",
			handler:function (){
				Ext.getCmp("DiaryPanelView").getUpdater().update(__ctxPath+"/system/displayDiary.do?start=0&limit=8");
			}
		},{
			id:"close",
			handler:function (c,b,a){
				Ext.Msg.confirm("提示信息","确认删除此模块吧？",function (d){
					if(d=="yes"){
						a.ownerCt.remove(a,true);
					}
				});
			}
		}];
	}
});
NewsPanelView=Ext.extend(Ext.ux.Portlet,{
	tools:null,
	constructor :function (a){
		Ext.applyIf(this,a);
		this.initTool();
		NewsPanelView.superclass.constructor .call(this,{
			id:"NewsPanelView",
			title:"新闻中心",
			iconCls:"menu-news",
			tools:this.tools,
			autoLoad:false/*,
			autoLoad:{
				url:__ctxPath+"/info/displayNews.do"
			}*/
		});
	},
	initTool:function (){
		this.tools=[{
			id:"refresh",
			handler:function (){
				Ext.getCmp("NewsPanelView").getUpdater().update(__ctxPath+"/info/displayNews.do");
			}
		}];
	}
});
MessagePanelView=Ext.extend(Ext.ux.Portlet,{
	tools:null,
	constructor :function (a){
		Ext.applyIf(this,a);
		this.initTool();
		MessagePanelView.superclass.constructor .call(this,{
			id:"MessagePanelView",
			title:"个人消息",
			iconCls:"menu-message",
			tools:this.tools,
			autoLoad:false
			/*autoLoad:{
				url:__ctxPath+"/info/displayInMessage.do"
			}*/
		});
	},
	initTool:function (){
		this.tools=[{
			id:"refresh",
			handler:function (){
				Ext.getCmp("MessagePanelView").getUpdater().update(__ctxPath+"/info/displayInMessage.do");
			}
		}];
	}
});

HomePanel = function(){
	var portalPanel = {};
	var initUIComponents = function (){
		var confs = new Array();
		//if(confs==null||confs==undefined||confs.length<1){
			confs=new Array();
			var p1={
				panelId:"DiaryPanelView",
				column:1,
				row:0
			};
			var p2={
				panelId:"MessagePanelView",
				column:0,
				row:1
			};
			var p3={
				panelId:"NewsPanelView",
				column:0,
				row:0
			};
			confs.push(p3);
			confs.push(p2);
			confs.push(p1);
		//}
		var column0=[];
		var column1=[];
		for(var v=0;v<confs.length;v++){
			if(confs[v].column==0){
				column0.push(eval("new "+confs[v].panelId+"()"));
			}else{
				column1.push(eval("new "+confs[v].panelId+"()"));
			}
		}
		portalPanel={
			id:"Portal",
			xtype:"portal",
			region:"center",
			margins:"35 5 5 0",
			items:[{
				columnWidth:0.65,
				style:"padding:10px 0 10px 10px",
				id:"PortalItem",
				items:column0
			},{
				columnWidth:0.35,
				style:"padding:10px 10px 10px 10px",
				items:column1
			}]
		};
	};
	
	var tbar = ["->",
		{
			xtype:"button",
			text:"添加模块",
			iconCls:"btn-add",
			handler:function (){}
		},{
			xtype:"button",
			text:"保存",
			iconCls:"btn-save",
			handler:function (){}
		}
	];
	initUIComponents();
	var config = {
		title:"首       页",
		closable:false,
		id:"AppHome",
		iconCls:"menu-company",
		layout:"fit",
		tbar:tbar,
		items:portalPanel,
		defaults:{padding:"0 5 0 0"}
	};
	
	HomePanel.superclass.constructor.call(this,config);
}
Ext.extend(HomePanel,Ext.Panel);

AppHome=Ext.extend(Ext.Panel,{
	//portalPanel:null,
	//toolbar:null,
	constructor :function (a){
		//Ext.applyIf(this,a);
		this.initUIComponents();
		AppHome.superclass.constructor .call(this,{
			title:"首       页",
			closable:false,
			id:"AppHome",
			iconCls:"menu-company",
			layout:"fit",
			defaults:{
				padding:"0 5 0 0"
			}/*,
			tbar:this.toolbar,
			items:this.portalPanel*/
		});
	},
	initUIComponents:function (){
		this.toolbar=new Ext.Toolbar({
			height:30,
			items:[
				"->",
				{
					xtype:"button",
					text:"添加模块",
					iconCls:"btn-add",
					handler:function (){
						new PanelSelectorWin().show();
					}
				},{
					xtype:"button",
					text:"保存",
					iconCls:"btn-save",
					handler:function (){
						var portal=Ext.getCmp("Portal");
						curUserInfo.portalConfig=[];
						var items=portal.items;
						for(var i=0;i<items.length;i++){
							var v=items.itemAt(i);
							for(var j=0;j<v.items.getCount();j++){
								var m=v.items.itemAt(j);
								var portalItem=new PortalItem(m.id,i,j);
								curUserInfo.portalConfig.push(portalItem);
							}
						}
						Ext.Ajax.request({
							method:"post",
							url:__ctxPath+"/system/saveIndexDisplay.do",
							success:function (request){
								Ext.ux.Toast.msg("操作信息","保存成功");
							},
							failure:function (request){
								Ext.MessageBox.show({
									title:"操作信息",
									msg:"信息保存出错，请联系管理员！",
									buttons:Ext.MessageBox.OK,
									icon:"ext-mb-error"
								});
							},
							params:{
								items:Ext.encode(curUserInfo.portalConfig)
							}
					});
				}
			}]
		});
		var tools=[{
			id:"gear",
			handler:function (){
				Ext.Msg.alert("Message","The Settings tool was clicked.");
			}
		},{
			id:"close",
			handler:function (e,target,panel){
				panel.ownerCt.remove(panel,true);
			}
		}];
		var confs=curUserInfo.portalConfig;
		if(confs==null||confs==undefined||confs.length<1){
			confs=new Array();
			var p1={
				panelId:"MessagePanelView",
				column:1,
				row:0
			};
			var p2={
				panelId:"NoticePanelView",
				column:0,
				row:1
			};
			var p3={
				panelId:"NewsPanelView",
				column:0,
				row:0
			};
			confs.push(p3);
			confs.push(p2);
			confs.push(p1);
		}
		var column0=[];
		var column1=[];
		for(var v=0;v<confs.length;v++){
			if(confs[v].column==0){
				column0.push(eval("new "+confs[v].panelId+"()"));
			}else{
				column1.push(eval("new "+confs[v].panelId+"()"));
			}
		}
		this.portalPanel={
			id:"Portal",
			xtype:"portal",
			region:"center",
			margins:"35 5 5 0",
			items:[{
				columnWidth:0.65,
				style:"padding:10px 0 10px 10px",
				id:"PortalItem",
				items:column0
			},{
				columnWidth:0.35,
				style:"padding:10px 10px 10px 10px",
				items:column1
			}]
		};
	}
});


