/**
 *UploadForm表单元素
 * 
 **/
UploadForm = Ext.extend(Ext.form.FormPanel,{
	labelAlign : 'left',
	frame : true,
	autoScroll:true,
	items: [
		{
			xtype:'textfield',
			fieldLabel:'图片上传',
			width:400,
			height:300,
			readOnly:true,
			inputType:'image'
		},{
			xtype: 'fileuploadfield',
			emptyText: '请上传二级菜单图片',
			fieldLabel: '上传图片',
			name: 'secondicon',
			anchor:'95%',
			buttonCfg: {
			     text: '',
			     iconCls: 'upload-icon'
			},
			listeners : {
				'fileselected' : function(obj, val) {
					var _panel = obj.ownerCt;
					var _txts = _panel.findByType('textfield');
					_txts[0].getEl().dom.src = val;
				}
			}
		}
	],
	initComponent : function() {
		UploadForm.superclass.initComponent.call(this);
	},
	getValue:function(){
		if(this.getForm().isValid()){
			return new Ext.data.Record(this.getForm().getValues());
		}else{
			throw Error('表单未能通过！');
		}
	},
	setValue:function(_r){
		this.getForm().loadRecord(_r);
	},
	reset:function(){
		this.getForm().reset();
	}
});
/**
 *上传的窗体
 *UploadWindow 
 **/
UploadWindow = Ext.extend(Ext.Window,{
	form : null,
	constructor : function() {
		UploadWindow.superclass.constructor.call(this, {
			plain : true,
			autoScroll : true,
			width : 600,
			height : 250,
			autoShow:true, 
			modal : true,
			layout: 'fit', 
			closeAction : 'hide',
			buttonAlign : 'center',
			listeners:{'hide':function(){this.close();},scope:this},
			buttons : [{
				text : '确定',
//				iconCls:'confirm',
				handler : this.onSubmitClick,
				scope : this
			}, {
				text : '取消',
//				iconCls:'cancel',
				handler : this.onCancelClick,
				scope : this
			}]
		});
		this.addEvents('submit');
	},
	close : function() {
		this.form.reset();
		this.hide();
	},
	onCancelClick : function() {
		this.close();
	},
	onSubmitClick : function() {
		try {
			var valflag = this.form.getForm().isValid();
			if (valflag) {
				this.fireEvent('submit', this, this.form.getValue());
			} else {
				//msg('系统提示', '请填写正确信息！', Ext.MessageBox.ERROR);
			}
		} catch (_err) {
			return;
		}
	},
	onCanelClick : function() {
		this.close();
	}
});