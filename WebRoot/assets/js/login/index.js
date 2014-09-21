var login = function(btn){
	var url = "back/login.jspx";
	var forminfos = $(":input");
	var postdate = {userid:forminfos[0].value,pwd:forminfos[1].value};
	$.post(url, postdate,
	   function(data){
	     if(data.success){
	     	location.href = 'back/index.jsp';
	     }
	   }, 
	"json");
}
$(function(){
	$("#btnlogin").bind("click",login);
});