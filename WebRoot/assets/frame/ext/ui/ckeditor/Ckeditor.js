ImageUploadWindow = function(){
	var uploadpanel = new Ext.form.FormPanel(
		{
			width:400,
			height:500,
			items:[
				{
					xtype:'textfield',
					name:'',
					fieldLabel:'图片'
				}
			]
		}
	);
	var config = {
		constrainHeader:true,
		constrain:true,
		modal : true,
		title: '图片上传S',
		iconCls:'image',
        width : 500,
		height : 500,
		bodyStyle : 'z-index: 10000;',
        resizable : true,
		layout : 'fit',
		items:[uploadpanel]
	}
	ImageUploadWindow.superclass.constructor.call(this,config);
}
Ext.extend(ImageUploadWindow,Ext.Window);

Ext.form.CKEditor = function(config) {
	this.config = config;
	Ext.form.CKEditor.superclass.constructor.call(this, config);
};
Ext.extend(Ext.form.CKEditor, Ext.form.TextArea, {
			hideLabel : true,
			constructor : function(config) {
				config = config || {};
				config.listeners = config.listeners || {};
				Ext.applyIf(config.listeners, {
							beforedestroy : this.onBeforeDestroy
									.createDelegate(this),
							scope : this
						});
				Ext.form.CKEditor.superclass.constructor.call(this, config);
			},
			onBeforeDestroy : function() {
				this.ckEditor.destroy();
			},
			onRender : function(ct, position) {
				if (!this.el) {
					this.defaultAutoCreate = {
						tag : "textarea",
						autocomplete : "off"
					};
				}
				Ext.form.TextArea.superclass.onRender.call(this, ct, position);
				this.ckEditor = CKEDITOR.replace(this.id, 
						Ext.apply(
								{
									skin : 'office2003'
								}, 
							this.config.CKConfig
						)
				);
				
				(function addUploadButton(editor){
					CKEDITOR.on('dialogDefinition', function(ev){
						var dialogName = ev.data.name;
						var dialogDefinition = ev.data.definition;
						if (dialogName=='image'){
							var infoTab = dialogDefinition.getContents( 'info' );
								infoTab.add({
									type : 'button',
									id : 'upload_image',
									align : 'center',
									label : '浏览',
									onClick : function(evt){
										var thisDialog = this.getDialog();
				 						var txtUrlObj = thisDialog.getContentElement('info', 'txtUrl');
										var txtUrlId = txtUrlObj.getInputElement().$.id;
										addUploadImage(txtUrlId);
									}
								 },'browse'); //place front of the browser button
								
							}
					});
				})();
				function addUploadImage(theURLElementId){
					var uploadUrl = __ctxPath+"/assert/ext/ui/uploadfile/uploadwin.jsp"; //这是我自己的处理文件/图片上传的页面URL
					var imgUrl = window.showModalDialog(uploadUrl);
					/*var imgUrl = '';
					var win = new ImageUploadWindow();
					win.show();*/
					//在upload结束后通过js代码window.returnValue=...可以将图片url返回给imgUrl变量。
					//更多window.showModalDialog的使用方法参考
					var urlObj = document.getElementById(theURLElementId);
					urlObj.value = imgUrl;
					urlObj.fireEvent("onchange"); //触发url文本框的onchange事件，以便预览图片
				}
			},

			setValue : function(value) {
				if (Ext.isEmpty(value)) {
					value = "";
				}
				Ext.form.TextArea.superclass.setValue.apply(this, [value]);
				CKEDITOR.instances[this.id].setData(value);
			},

			getValue : function() {
				CKEDITOR.instances[this.id].updateElement();
				this.value = CKEDITOR.instances[this.id].getData();
				return Ext.form.TextArea.superclass.getValue.apply(this);
			},

			getRawValue : function() {
				CKEDITOR.instances[this.id].updateElement();
				this.value = CKEDITOR.instances[this.id].getData();
				return Ext.form.TextArea.superclass.getRawValue.apply(this);
			}
		});
Ext.reg('ckeditor', Ext.form.CKEditor);



/*
   new Ext.form.CKEditor({
		xtype : 'ckeditor',
		name  : 'content',
		width : '100%',
		height: 250
	});
 */