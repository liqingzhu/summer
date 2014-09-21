Ext.namespace('SX');
var __companyName = '秦皇岛首信电子科技';
var __ctxPath = '/summer';
/**
 *根据用户的rolesandgroups信息抽象
 *出用户有的用户组和权限信息然后返回一个用户信息
 **/
SX.transRoleAndUserGroupInfo = function(userinfo){
	var rolesandgroups = [];
	if(userinfo=='undefined'||!userinfo){
		location.href = __ctxPath+'/login.jsp';
		return;
	}
	if(userinfo.rolesandgroups){
		rolesandgroups = userinfo.rolesandgroups;
		var roles = [];
		var rolesids = [];
		var usergroup = [];
		var usergroupids = [];
		for (var i = 0; i < rolesandgroups.length; i++) {
			if(rolesandgroups[i].userrolename){
				roles.push(rolesandgroups[i].userrolename);
			}
			if(rolesandgroups[i].usergrouprolename){
				roles.push(rolesandgroups[i].usergrouprolename);
			}
			if(rolesandgroups[i].userroleid){
				rolesids.push(rolesandgroups[i].userroleid);
			}
			if(rolesandgroups[i].usergrouproleid){
				rolesids.push(rolesandgroups[i].usergrouproleid);
			}
			if(rolesandgroups[i].usergroupname){
				usergroup.push(rolesandgroups[i].usergroupname);
			}
			if(rolesandgroups[i].usergroupid){
				usergroupids.push(rolesandgroups[i].usergroupid);
			}
		}
		userinfo.roles = roles;
		userinfo.rolesids = rolesids;
		userinfo.usergroup = usergroup;
		userinfo.usergroupids = usergroupids;
	}
	return userinfo
}

var msg = function(title,msg,icon) {
	Ext.Msg.show({
		title    : title,
		msg 	 : msg,
		minWidth : 200,
		modal    : false,
		icon     : icon,
		buttons  : Ext.Msg.OK
	});
};
SX.Util = function(){
    var msgCt;

    function createBox(t, s){
        return ['<div class="msg">',
                '<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
                '<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc"><h3>', t, '</h3>', s, '</div></div></div>',
                '<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
                '</div>'].join('');
    }
    return {
        msg : function(title, format){
            if(!msgCt){
                msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
            }
            msgCt.alignTo(document, 't-t');
            var s = String.format.apply(String, Array.prototype.slice.call(arguments, 1));
            var m = Ext.DomHelper.append(msgCt, {html:createBox(title, s)}, true);
            m.slideIn('t').pause(1).ghost("t", {remove:true});
        },
		alert: function(title,msg,icon) {
			Ext.Msg.show({
				title    : title,
				msg 	 : msg,
				minWidth : 200,
				modal    : false,
				icon     : icon,
				buttons  : Ext.Msg.OK
			});
		},
        init : function(){
            var lb = Ext.get('lib-bar');
            if(lb){
                lb.show();
            }
        }
    };
}();