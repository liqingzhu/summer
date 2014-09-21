AttachmentWindow = function(obj){
	var uploadpanel = {
		xtype : 'uploadpanel',
		uploadUrl : obj.url,
//		uploadUrl : __ctxPath+'/back/article/uploadfileinfo.html',
		filePostName : 'fileimg', // 这里很重要，默认值为'fileData',这里匹配action中的setMyUpload属性
		flashUrl : __ctxPath+'/assert/ext/ui/uploader/swfupload.swf',
		fileSize : '500 MB',
		height : 500,
		border : false,
		fileTypes : '*.*', // 在这里限制文件类型:'*.jpg,*.png,*.gif'
		fileTypesDescription : '所有文件',
		postParams : {
			uid:userinfo.uid,
			path : 'files\\' // 上传到服务器的files目录下面
		}
	};
	var config = {
		constrainHeader:true,
		constrain:true,
		modal : true,
		title: obj.title,
		iconCls:obj.iconcls,
        width : 500,
		height : 500,
        resizable : true,
		layout : 'fit',
		items:[uploadpanel]
	}
	AttachmentWindow.superclass.constructor.call(this,config);
}
Ext.extend(AttachmentWindow,Ext.Window);