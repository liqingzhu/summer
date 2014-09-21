var js_fckname  = "context";
CKEDITOR.replace(js_fckname,{skin:'kama',language:_local},addUploadButton(this));
function addUploadButton(editor){
	CKEDITOR.on('dialogDefinition', function(ev){
		var dialogName = ev.data.name;
		var dialogDefinition = ev.data.definition;
		if (dialogName=='image'){
			var infoTab = dialogDefinition.getContents( 'info' );
				infoTab.add({
					type : 'button',
					id : 'upload_image',
					align : 'center',
					label : '上传',
					onClick : function(evt){
						var thisDialog = this.getDialog();
 						var txtUrlObj = thisDialog.getContentElement('info', 'txtUrl');
						var txtUrlId = txtUrlObj.getInputElement().$.id;
						addUploadImage(txtUrlId);
					}
				 },'browse'); //place front of the browser button
			}
	});
}
function addUploadImage(theURLElementId){
	var uploadUrl = apppath+"/ext/ui/fileupload/uploadwin.jsp"; //这是我自己的处理文件/图片上传的页面URL
	var imgUrl = window.showModalDialog(uploadUrl);
	//在upload结束后通过js代码window.returnValue=...可以将图片url返回给imgUrl变量。
	//更多window.showModalDialog的使用方法参考
	var urlObj = document.getElementById(theURLElementId);
	urlObj.value = imgUrl;
	urlObj.fireEvent("onchange"); //触发url文本框的onchange事件，以便预览图片
}