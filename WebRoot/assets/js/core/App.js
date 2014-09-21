Ext.ns("App");
var PortalItem=function (a,b,c) {
	this.panelId=a;
	this.column=b;
	this.row=c;
};
var UserInfo=function (d,a,c,f,e,b) {
	this.userId=d;
	this.fullname=a;
	this.depId=c;
	this.depName=f;
	this.rights=e;
	this.portalConfig=b;
};
var curUserInfo=null;
function isGranted(a) {
	if(curUserInfo.rights.indexOf("__ALL")!=-1) {
		return true;
	}if(curUserInfo.rights.indexOf(a)!=-1) {
		return true;
	}return false;
}
/*
App.init=function () {
	Ext.QuickTips.init();
	Ext.BLANK_IMAGE_URL=__ctxPath+"/ext3/resources/images/default/s.gif";
	setTimeout(function () {
		Ext.get("loading").remove();
		Ext.get("loading-mask").fadeOut({
			remove:true
		});
	},1000);
	Ext.util.Observable.observeClass(Ext.data.Connection);
	Ext.data.Connection.on("requestcomplete",function (c,d,b) {
		if(d&&d.getResponseHeader) {
			if(d.getResponseHeader("__timeout")) {
				Ext.ux.Toast.msg("操作提示：","操作已经超时，请重新登录!");
				window.location.href=__ctxPath+"/index.jsp?randId="+parseInt(1000*Math.random());
			}if(d.getResponseHeader("__forbidden")) {
				Ext.ux.Toast.msg("系统访问权限提示：","你目前没有权限访问：{0}",b.url);
			}
		}
	});
	Ext.Ajax.request({
		url:__ctxPath+"/system/getCurrentAppUser.do?random="+Math.random(),method:"Get",success:function (d,g) {
			var f=Ext.util.JSON.decode(d.responseText);
			var c=f.user;
			var e=c.items;
			curUserInfo=new UserInfo(c.userId,c.fullname,c.depId,c.depName,c.rights,e);
			var b=Ext.getCmp("centerTabPanel");
			var h=b.add(new AppHome());
			b.activate(h);
		}
	});
	var a=new IndexPage();
};

*/

/**
 *f=id
 *c=params
 *a=precall
 *e=callback 
 **/
App.clickTopTab=function (id,params,precall,callback) {
	if(precall!=null) {
		precall.call(this);
	}
	var centertab=Ext.getCmp("centerTabPanel");
	var _panel=centertab.getItem(id);
	if(_panel==null) {
		//f=id
		//c=params
		//b=tabpanel
		//d=item
		$ImportJs(id,function(g){_panel=centertab.add(g);centertab.activate(_panel);},params);
	}else {
		if(callback!=null) {
		   callback.call(this);
		}
		centertab.activate(_panel);
	}
};
App.MyDesktopClickTopTab=function (id,params,precall,callback) {
	if(precall!=null) {
		precall.call(this);
	}
	var tabs=Ext.getCmp("centerTabPanel");
	var tabItem=tabs.getItem(id);
	if(tabItem==null) {
		$ImportJs(id,function (view) {
			tabItem=tabs.add(view);
			tabs.activate(tabItem);
		},params);
	}else {
		tabs.remove(tabItem);
		var str="new "+id;
		if(params!=null) {
			str+="(params);";
		}else {
			str+="();";
		}var view=eval(str);
		tabItem=tabs.add(view);
		tabs.activate(tabItem);
	}
};
App.clickNode=function (node) {
	var code = node.attributes.mcode;
	if(code==null||code==""||code.indexOf("xnode")!=-1) {
		return ;
	}else if(node.attributes.params){
		App.clickTopTab(code,node.attributes.params);
	}else{
		App.clickTopTab(code);
	}
//	App.clickTopTab(node.id,20); 传递参数的写法
//	App.clickTopTab(code);  //木有传递参数的写法
};
App.MyDesktopClick=function () {
	var a=Ext.getCmp("MyDesktop");
	a.expand(true);
	App.clickTopTab("AppHome");
};
App.Logout=function () {
	Ext.Ajax.request({
		url:__ctxPath+"/j_logout.do",success:function () {
			window.location.href=__ctxPath+"/login.jsp";
		}
	});
};
/*Ext.onReady(App.init);*/