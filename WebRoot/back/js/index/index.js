Ext.ns('SX');
SX.ViewPort = function(){
	var bottombar = [{
		text:"退出系统",
		iconCls:"btn-logout",
		handler:function () {
			App.Logout();
		}
	},"-",{
		text:"在线用户",
		iconCls:"btn-onlineUser",
		handler:function () {
			OnlineUserSelector.getView().show();
		}
	},"-",{
		id:"messageTip",
		xtype:"button",
		hidden:true,
		width:50,
		height:20,
		handler:function () {
			var a=Ext.getCmp("messageTip");
			var b=Ext.getCmp("win");
			if(b==null) {
				new MessageWin().show();
			}
			a.hide();
		}
	},"->",{
		xtype:"tbfill"
	},{
		xtype:"tbtext",
		text:__companyName+"办公协同管理系统",
		id:"toolbarCompanyName"
	},{
		xtype:"tbseparator"
	},new Ext.Toolbar.TextItem('技术支持 <a href="#" target="_blank">'+__companyName+'</a>'),{
		xtype:"tbseparator"
	},{
		pressed:false,
		text:"便签",
		iconCls:"tipsTile",
		handler:function () {
			App.clickTopTab("PersonalTipsView");
		}
	},{
		xtype:"tbseparator"
	},{
		pressed:false,
		text:"与我们联系",
		handler:function () {
			Ext.ux.Toast.msg("联系我们","电话：020-62652355<br/>网址：http://www.jee-soft.cn");
		}
	},"-",{
		text:"收展",
		iconCls:"btn-expand",
		handler:function () {
			var a=Ext.getCmp("__nortPanel");
			if(a.collapsed) {
				a.expand(true);
			}else {
				a.collapse(true);
			}
		}
	},"-",{
		xtype:"combo",
		mode:"local",
		editable:false,
		value:"切换皮肤",
		width:100,
		triggerAction:"all",
		store:[["ext-all","缺省浅蓝"],["ext-all-css04","灰白主题"],["ext-all-css05","绿色主题"],["ext-all-css03","粉红主题"],["xtheme-tp","灰色主题"],["xtheme-default2","灰蓝主题"],["xtheme-default16","绿色主题"],["xtheme-access","Access风格"]],
		listeners:{
			scope:this,
			"select":function (d,b,c) {
				if(d.value!="") {
					var a=new Date();
					a.setDate(a.getDate()+300);
					setCookie("theme",d.value,a,__ctxPath);
					Ext.util.CSS.swapStyleSheet("theme",__ctxPath+"/assets/frame/ext/resources/css/"+d.value+".css");
				}
			}
		}
	}];
	var top = new Ext.Panel({
		region:"north",
		id:"__nortPanel",
		contentEl:"app-header",
		height:60
	});
	var center = new Ext.TabPanel({
		id:"centerTabPanel",
		region:"center",
		deferredRender:true,
		enableTabScroll:true,
		activeTab:0,
		defaults:{
			autoScroll:true,
			closable:true
		},
		items:[]
	});
	var west = new Ext.Panel({
		region:"west",
		id:"west-panel",
		title:"导航",
		iconCls:"menu-navigation",
		split:true,
		width:180,
		autoScroll:true,
		layout:"accordion",
		collapsible:true,
		items:[]
	});
	var south = new Ext.Panel({
		region:"south",
		height:28,
		bbar:bottombar,
		border:false
	});
	var config = {
		layout:'border',
		renderTo:Ext.getBody(),	
		items:[top,west,center,south]
//		items:[top,center]
	};
	/*
	 	备份菜单数据信息原来的菜单功能备份
	 	拷贝回来即可使用
	 	var arr = [
						{
							text:'我的电脑1',
							id:'MyCompany',
							iconCls:'menu-desktop',
							items:[{}]
						},
						{
							text:'我的文档2',
							id:'Documents',
							iconCls:'menu-signInOff',
							items:[{}]
						},
						{
							text:'信息管理3',
							id:'MyInfomation',
							iconCls:'menu-subuser',
							items:[{}]
						}
					];
					for (var i = 0; i < arr.length; i++) {
						var tree = new Ext.tree.TreePanel(
							{
								id:arr[i].id,
								title:arr[i].text,
								iconCls:arr[i].iconCls,
								layout:"fit",
								animate:true,
								border:false,
								autoScroll:true,
								loader:new Ext.tree.TreeLoader({
									url:'menu'+(i+1)+'.json',
									autoLoad:true
								}),
								root:new Ext.tree.AsyncTreeNode({
									text:arr[i].text
								}),
								listeners:{
									"click":App.clickNode
								},
								rootVisible:false
							}
						);
						westmenu.add(tree);
						westmenu.doLayout();
					} 
	 */
	var loadWest = function(){
		var westmenu = west;
		var roleids = "";
		if(userinfo.rolesids.length==1){
			roleids = userinfo.rolesids[0];
		}else{
			roleids = userinfo.rolesids.join(',');
		}
		Ext.Ajax.request(
			{
				url:__ctxPath+'/back/role/getfirstmenuinfo.jspx',
				method:'POST',
				params:{type:'first',ids:roleids},
				success:function(resp,option){
					var respobj = Ext.decode(resp.responseText);
					if(respobj.success){
						var arr = respobj.data;
						for (var i = 0; i < arr.length; i++) {
							var tree = new Ext.tree.TreePanel(
								{
									id:arr[i].id,
									title:arr[i].mname,
									iconCls:arr[i].iconCls,
									layout:"fit",
									animate:true,
									border:false,
									autoScroll:true,
									loader:new Ext.tree.TreeLoader({
										listeners : {"beforeload":function(treeLoader, node) {
									        this.baseParams.pid = node.attributes.id;
									    }},
										baseParams:{type:'tree',pid:arr[i].id,ids:roleids},
										url:__ctxPath+'/back/role/gettreemenuinfo.jspx',
										autoLoad:true
									}),
									root:new Ext.tree.AsyncTreeNode({
										text:arr[i].text,
										id:arr[i].id
									}),
									listeners:{
										"click":App.clickNode
									},
									rootVisible:false
								}
							);
							tree.expandAll();
							westmenu.add(tree);
							westmenu.doLayout();
						}
					}
				},
				failure:function(resp,option){
					
				}
			}
		);
		
		
	};
	var addHomePanel = function(){
		var home = new HomePanel();
		var tabpanel = Ext.getCmp('centerTabPanel');
		tabpanel.add(home);
		tabpanel.activate(home);
	};
	var afterPropertySet = function(){
		addHomePanel();
	};
	SX.ViewPort.superclass.constructor.call(this,config);
	loadWest();
//	afterPropertySet();
}
Ext.extend(SX.ViewPort,Ext.Viewport);

var init = function(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	Ext.BLANK_IMAGE_URL = '../assets/frame/ext/resources/images/default/s.gif';
	var view = new SX.ViewPort();
}
Ext.onReady(init);