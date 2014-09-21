Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	Ext.BLANK_IMAGE_URL = __ctxPath+'/assert/ext/resources/images/default/s.gif';
	
	var _form = new UploadForm();
	var _view = new Ext.Viewport({
		layout:'fit',
		items:[_form]
	});
	
});