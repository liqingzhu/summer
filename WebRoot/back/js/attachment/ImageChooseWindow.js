ImageChooseWindow = function(){
	
	/*var data = [
		{
			filename:"a.png",
			filepath:"attachment/articleimage/workflow/2012-09-16date/a.jpg",
			filetype:"png",
			userinfo:"张三1",
			filetime:"2012-01-02 12:12:12"
		},
		{
			filename:"b.png",
			filepath:"attachment/articleimage/workflow/2012-09-16date/b.jpg",
			filetype:"jpg",
			userinfo:"张三2",
			filetime:"2012-01-02 12:12:12"
		},
		{
			filename:"c.png",
			filepath:"attachment/articleimage/workflow/2012-09-16date/c.png",
			filetype:"gif",
			userinfo:"张三3",
			filetime:"2012-01-02 12:12:12"
		}
	];*/
	var data = {"images":[
		{"id":1,"name":"a.jpg","size":2826,"lastmod":1307598192000,"url":"/sx\/back\/attachment\/articleimage\/workflow\/2012-09-16date\/a.jpg"},
		{"id":2,"name":"zacks_grill.jpg","size":2825,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/zacks_grill.jpg"},
		{"id":3,"name":"kids_hug2.jpg","size":2476,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/kids_hug2.jpg"},
		{"id":4,"name":"zack_dress.jpg","size":2645,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/zack_dress.jpg"},
		{"id":5,"name":"zack.jpg","size":2901,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/zack.jpg"},
		{"id":6,"name":"sara_pink.jpg","size":2154,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/sara_pink.jpg"},
		{"id":7,"name":"zack_hat.jpg","size":2323,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/zack_hat.jpg"},
		{"id":8,"name":"gangster_zack.jpg","size":2115,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/gangster_zack.jpg"},
		{"id":9,"name":"zack_sink.jpg","size":2303,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/zack_sink.jpg"},
		{"id":10,"name":"up_to_something.jpg","size":2120,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/up_to_something.jpg"},
		{"id":11,"name":"sara_smile.jpg","size":2410,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/sara_smile.jpg"},
		{"id":12,"name":"sara_pumpkin.jpg","size":2588,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/sara_pumpkin.jpg"},
		{"id":13,"name":"kids_hug.jpg","size":2477,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/kids_hug.jpg"},
		{"id":14,"name":"dance_fever.jpg","size":2067,"lastmod":1307598194000,"url":"/sx\/assert\/ext\/ui\/imagechoose\/images\/thumbs\/dance_fever.jpg"}
	]};
	var store = new Ext.data.JsonStore(
		{
//			data:data,
//			url:__ctxPath+'/assert/ext/ui/imagechoose/get-images.json',
			url:__ctxPath+'/back/article/findmyuploadimage.jspx',
			autoLoad:true,
			root: 'images',
			    fields: [
			    	'id',
			    	 'fid',
					 'fdisplayname',
					'fvaluename',
					'fvaluepath' ,
					 'fdate' ,
					  'fuserid',
					'ftype',
					' fshortname',
					'fnote'
			    	/*'id',
			        'name', 'url',
			        {name:'size', type: 'float'},
			        {name:'lastmod', type:'date', dateFormat:'timestamp'}*/
			    ],
			    listeners: {
			    	'load': {fn:function(){ /*dataview.select(0);*/ }, scope:this, single:true}
			    }
		}
	);
	var thumbTemplate = new Ext.XTemplate(
		'<tpl for=".">',
			'<div class="thumb-wrap" id="{fdisplayname}">',
			'<div class="thumb"><img src="{fvaluepath}\/{fvaluename}" title="{fdisplayname}"></div>',
			'<span>{fdisplayname}</span></div>',
		'</tpl>'
	);
	
	var detailsTemplate = new Ext.XTemplate(
		'<div class="details">',
			'<tpl for=".">',
				'<img src="{fvaluepath}\/{fvaluename}" width=100 height=75><div class="details-info">',
				'<b>图片名称:</b>',
				'<span>{fdisplayname}</span>',
				'<b></b>',
				'<span></span>',
				'<b>上传时间:</b>',
				'<span>{fdate}</span></div>',
			'</tpl>',
		'</div>'
	);
	var showDetails = function(){
	    var selNode = dataview.getSelectedNodes();;
	    var detailEl = Ext.getCmp('img-detail-panel').body;
		if(selNode && selNode.length > 0){
			selNode = selNode[0];
		    var data = lookup[selNode.id];
		    
            detailEl.hide();
            detailsTemplate.overwrite(detailEl, data);
            detailEl.slideIn('l', {stopFx:true,duration:.2});
		}else{
		    detailEl.update('');
		}
	};	
	var doCallback = function(){
		var selNode = dataview.getSelectedNodes()[0];
		if(selNode){
			var data = lookup[selNode.id];
			this.fireEvent('selectimage',this,data);
		}
		
       /* var selNode = this.view.getSelectedNodes()[0];
		var callback = this.callback;
		var lookup = this.lookup;
		this.hide(this.animateTarget, function(){
            if(selNode && callback){
				var data = lookup[selNode.id];
				callback(data);
			}
		});*/
    };
	var lookup = {};
	var formatData = function(data){
		data.fdate = data.fdate.substring(0,19);
    	lookup[data.fdisplayname] = data;
    	return data;
    };
	var dataview = new Ext.DataView(
		{
			tpl: thumbTemplate,
			singleSelect: true,
			overClass:'x-view-over',
			itemSelector: 'div.thumb-wrap',
			emptyText : '<div style="padding:10px;">No images match the specified filter</div>',
			store: store,
			listeners: {
				'selectionchange': {fn:showDetails, scope:this, buffer:100},
				'dblclick'       : {fn:doCallback, scope:this}
				/*'loadexception'  : {fn:this.onLoadException, scope:this},
				'beforeselect'   : {fn:function(view){
			        return view.store.getRange().length > 0;
			    }}*/
			},
			prepareData: formatData.createDelegate(this)
		}
	);
	/**
	 *排序 
	 **/
	var sortImages = function(combo,rec,index){
		store.baseParams = {sort:rec.get('name')};
		store.reload();
	};
	var filter = function(){
		var filter = Ext.getCmp('filter');
		store.baseParams = {fdisplayname:filter.getValue()};
		store.reload();
		//store.filter('fvaluename', filter.getValue());
		//dataview.select(0);
	};
	var config = {
		title: '选择图片',
    	id: 'img-chooser-dlg',
    	iconCls:'picture',
    	layout: 'border',
		/*minWidth: 500,
		minHeight: 300,*/
		width:600,
		height:400,
		modal: true,
		closeAction: 'close',
		border: false,
		items:[
			{
				id: 'img-chooser-view',
				region: 'center',
				autoScroll: true,
				items: dataview,
	            tbar:[
		            {
		            	text: '查询:'
		            },{
		            	xtype: 'textfield',
		            	id: 'filter',
		            	selectOnFocus: true,
		            	width: 100,
		            	listeners: {
		            		'render': {
		            			fn:function(){
						    		Ext.getCmp('filter').getEl().on(
						    			'keyup', function(){
						    				filter();
						    			}, 
						    			this, 
						    			{buffer:500}
						    		);
		            			}, 
		            			scope:this
		            		}
		            	}
		            }, ' ', '-', {
		            	text: '排序:'
		            }, {
		            	id: 'sortSelect',
		            	xtype: 'combo',
				        typeAhead: true,
				        triggerAction: 'all',
				        width: 100,
				        editable: false,
				        mode: 'local',
				        displayField: 'desc',
				        valueField: 'name',
				        lazyInit: false,
//				        value: 'name',
				        store: new Ext.data.ArrayStore({
					        fields: ['name', 'desc'],
					        data : [
					        	['displayname', '名字'],
					        	['date', '上传时间']
					        ]
					    }),
					    listeners: {
							'select': {fn:sortImages}
					    }
				    },'-',{
				    	text:'图片上传',
				    	handler:function(){
				    		var imgwin = new UploadImageWindow();
				    		imgwin.show();
				    		imgwin.on('submit',function(win,form){
				    			form.getForm().submit(
				    				{
				    					url:__ctxPath+'/back/article/addimageinfo.jspx',
				    					params:{path:'imagepath'},
				    					method:'POST',
				    					success:function(form,option){
				    						SX.Util.msg('系统提示','图片上传成功!');
				    						win.hide();
				    						imgwin.close();
				    						store.reload();
				    					},
				    					failure:function(form,option){
				    						SX.Util.msg('系统提示','图片上传失败!');
				    					}
				    				}
				    			);
				    		});
				    	}
				    }
			 ]
		},{
			id: 'img-detail-panel',
			region: 'east',
			split: true,
			width: 150,
			minWidth: 150,
			maxWidth: 250
		}],
		buttons: [
			{
				text: '选择',
				handler: function(){
					var win = this;
					var selNode = dataview.getSelectedNodes()[0];
					var data = lookup[selNode.id];
					this.fireEvent('selectimage',win,data);
				},
				scope: this
			},{
				text: '取消',
				handler: function(){ this.close(); },
				scope: this
			}
		],
		keys: {
			key: 27, // Esc key
			handler: function(){ this.close(); },
			scope: this
		}
	};
	/*var config = {
		constrainHeader:true,
		constrain:true,
		modal : true,
		title: '选择图片',
		iconCls:'picture',
        width : 500,
		height : 500,
        resizable : true,
        items:[dataview],
        layout:'fit'
	};*/
	ImageChooseWindow.superclass.constructor.call(this,config);
}
Ext.extend(ImageChooseWindow,Ext.Window);