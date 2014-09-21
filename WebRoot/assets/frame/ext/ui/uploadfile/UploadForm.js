Ext.apply(Ext.form.VTypes, {
	filetypeRange : function(val, field) {
		try {
			var txtvalue = field.getValue();
			var index = txtvalue.indexOf('.');
			var str = txtvalue.substring(index + 1, txtvalue.length);
			var vals = ['jpeg', 'jpg', 'GIF', 'gif', 'png'];
			var flag = false;
			for (var index = 0; index < vals.length; index++) {
				if (vals[index] == str.toLowerCase()) {
					flag = true;
					break;
				}
			}
			return flag;
		} catch (e) {
			alert(e.description);
			return false;

		}
	},
	filetypeRangeText:'请上传文件格式为Jpeg,Jpg,Gif,Png的图片文件'
});
/**
 *UploadForm表单元素
 * 
 **/
UploadForm = Ext.extend(Ext.form.FormPanel,{
	constructor : function() {
		UploadForm.superclass.constructor.call(this, {
			labelAlign : 'left',
			frame : true,
			fileUpload : true,
			autoScroll:true,
			buttonAlign:'center',
			buttons:[
				{
					text:'上传',
					align:'center',
					iconCls:'upload_',
					handler:function(){
						var _form = this.getForm();
						_form.submit(
							{
								waitTitle:'系统提示',
								waitMsg:'数据保存中，请稍侯...', 
								url:__ctxPath+'/back/jsp/core/uploadfileimage.html',
								method:'POST',
								params:{path:'articleimage'},
								success:function(form,action){
									var _m = action.result.filepath;
									window.returnValue=_m;
    								window.close();
								},
								failure:function(form,action){
									msg('系统提示','上传文件失败!',Ext.MessageBox.ERROR);
									return;
								}
							}
						);
					},
					scope:this
				},
				{
					text:'取消',
					align:'center',
					iconCls:'cancel',
					handler:function(){
						window.close();
					}
				}],
			items: [
				{
					xtype: 'fileuploadfield',
					emptyText: '请上传图片',
					fieldLabel: '上传图片',
					name: 'fileimg',
					vtype : 'filetypeRange',
					anchor:'95%',
					buttonCfg: {
					   text: '',
					   iconCls: 'upload-icon'
					},
					listeners : {
						'fileselected' : function(obj, val) {
//							var _panel = obj.ownerCt;
//							Ext.getCmp('showimage').getEl().dom.src=val;//显示小区照片，必须先显示小区照片窗口
//							Ext.getCmp('showimage').setVisible(true);     
						}
					}
				},{
                   bodyStyle: ' margin:4px 10px 10px 5px',
                   layout: 'form',
                   items: [{
                       xtype: 'panel',
                       html:'上传后图片出现在下面!',
                       border:true,
                       autoEl: {
                           width: 200, height: 200,
                           tag: 'div',id: 'browser_up_forth'
                       }
                   }]
				}
			]
		})
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


/*
 	
	initComponent : function() {
		UploadForm.superclass.initComponent.call(this,{});
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
 */