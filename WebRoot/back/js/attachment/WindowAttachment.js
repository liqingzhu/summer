WindowAttachment = function(obj){
	var title = obj.title;
	var url = __ctxPath+'/back/article/findattachmentsfortypeandid.jspx';
	var store = new Ext.data.JsonStore(
		{
			url:url,
			totalProperty:'totalCount',
			baseParams:{start:0,limit:20,type:obj.typeid,id:obj.id},
			root:'data',
			autoLoad:true,
			fields:['id','fdisplayname','fvaluepath','fvaluename','fid']
		}
	);
	var cm = [
		{header:"标题",dataIndex:"fdisplayname",width:200},
		{header:"类型",dataIndex:"fvaluename",width:150,
			renderer : function(_v, cellmeta, record) {
				var returnValue = '';
				var extensionName = _v.substring(_v.lastIndexOf('.')+1,_v.length);
				var fileId = record.data.fid;
				if (_v) {
					var css = '.db-ft-' + extensionName.toLowerCase() + '-small';
					if (Ext.isEmpty(Ext.util.CSS.getRule(css), true)) { // 判断样式是否存在
						returnValue = '<div id="fileType_' + fileId
								+ '" class="db-ft-unknown-small" style="height: 16px;background-repeat: no-repeat;">'
								+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + extensionName.toUpperCase() + '</div>';
					} else {
						returnValue = '<div id="fileType_' + fileId + '" class="db-ft-' + extensionName.toLowerCase()
								+ '-small" style="height: 16px;background-repeat: no-repeat;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
								+ extensionName.toUpperCase();
						+'</div>';
					}
					return returnValue;
				}
				return '<div id="fileType_'
						+ fileId
						+ '" class="db-ft-unknown-small" style="height: 16px;background-repeat: no-repeat;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
						+ extensionName.toUpperCase() + '</div>';
			}
		},
		{header:"操作",dataIndex:"fvaluepath",width:100,
			renderer:function(value,meta,record){
				var fvaluename = record.get('fvaluename');
				var str = '<div  style="width: 55px;" ' 
				+'ext:qtip="<img width=\'80\' height=\'50\' src=\''+value+"/"+fvaluename+'\'/>">查看图片</div>';
				return str;
			}
		}
	];
	var grid = new Ext.grid.GridPanel(
		{
			store:store,
			width:500,
			height:400,
			columns:cm
		}
	);
	var bbar = [
		new Ext.PagingToolbar(
			{
				 store: store,       // grid and PagingToolbar using same store
        		 displayInfo: true,
        		 pageSize: 20,
        		 plugins: new Ext.ux.ProgressBarPager({progBarWidth:200})
			}
		)
	];
	var tbar = [
		/*{
			text:'新增信息',
			iconCls:'add',
			handler:function(){}
		}*/
	];
	var config = {
		constrainHeader:true,
		constrain:true,
		modal : true,
		tbar:tbar,
		bbar:bbar,
		title:title,
		layout:"fit",
		iconCls:'application',
		width:500,
		height:400,
		items:[grid]
	};
	WindowAttachment.superclass.constructor.call(this,config);
}
Ext.extend(WindowAttachment,Ext.Window);