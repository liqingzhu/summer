UploadImageWindow = function(){
	var form = new Ext.FormPanel({
        fileUpload: true,
        width: 500,
        frame: true,
        autoHeight: true,
        bodyStyle: 'padding: 10px 10px 0 10px;',
        labelWidth: 55,
        defaults: {
            anchor: '95%',
            allowBlank: false,
            msgTarget: 'side'
        },
        items: [{
            xtype: 'textfield',
            name:'fshortname',
            fieldLabel: '图片名称'
        },{
            xtype: 'fileuploadfield',
            id: 'form-file',
            emptyText: '请选择图片',
            allowBlank:true,
            fieldLabel: '图片',
            name: 'fileimg',
            buttonText: '',
            buttonCfg: {
                iconCls: 'upload-icon'
            }
        },{
        	xtype:'hidden',
        	name:'fuserid',
        	value:userinfo.uid
        }],
        buttons: [{
            text: '保存',
            handler: function(){
                this.fireEvent('submit',this,form);
            },
            scope:this
        },{
            text: '取消',
            handler: function(){
                this.close();
            },
            scope:this
        }]
    });
    
	var config = {
		autoWidth:true,
		autoHeight:true,
        title: '上传图片',
		closeAction:'close',
		layout:'fit',
		items:[form]
	};
	UploadImageWindow.superclass.constructor.call(this,config);
}
Ext.extend(UploadImageWindow,Ext.Window);