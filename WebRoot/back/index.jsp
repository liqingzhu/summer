<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>夏天-比特币物流智联平台--首页</title>
    <!--Ext核心代码开始-->
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <script type="text/javascript" src="/summer/assets/frame/ext/ext-base.gzjs"></script>
	<script type="text/javascript" src="/summer/assets/frame/ext/ext-all.gzjs"></script>
	<script type="text/javascript" src="../assets/frame/ext/ext-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="../assets/frame/ext/resources/css/ext-all.css">
	<!--Ext核心代码结束-->
	<!-- Protal类库开始 -->
	<script type="text/javascript" src="../assets/frame/ext/ui/protal/Portal.js"></script>
	<script type="text/javascript" src="../assets/frame/ext/ui/protal/PortalColumn.js"></script>
	<script type="text/javascript" src="../assets/frame/ext/ui/protal/Portlet.js"></script>
	<link rel="stylesheet" type="text/css" href="../assets/frame/ext/ui/protal/Portal.css">
	<!-- Protal类库结束 -->
	<!--动态加载js核心代码开始 -->
	<script type="text/javascript" src="../assets/js/core/core.js"></script>
	<script type="text/javascript" src="../assets/js/core/App.js"></script>
	<script type="text/javascript" src="../assets/js/core/AppUtil.js"></script>
	<script type="text/javascript" src="../assets/js/core/ScriptMgr.js"></script>
	<!-- -->
	<script type="text/javascript" src="../back/jsp/core.jsp"></script>
	<!--动态加载js核心代码结束-->
	<!--脚本路径库开始 -->
	<script type="text/javascript" src="../assets/js/impjs/Import.js"></script>
	<!--脚本路径库结束-->
	<!-- 系统核心代码CSS -->
	<link rel="stylesheet" type="text/css" href="../assets/frame/ext/ui/imagechoose/chooser.css">
	<link rel="stylesheet" type="text/css" href="../assets/frame/ext/ui/icon.css">
	<link rel="stylesheet" type="text/css" href="../assets/css/index.css">
	<link rel="stylesheet" type="text/css" href="../assets/css/filetype.css">
	<link rel="stylesheet" type="text/css" href="../assets/css/admin.css">
	<link rel="stylesheet" type="text/css" href="../assets/frame/ext/ui/superboxselect/superboxselect.css">
	<link rel="stylesheet" type="text/css" href="../assets/frame/ext/ui/columnheadergroup/ColumnHeaderGroup.css">
	<!-- 系统核心代码结束CSS -->
	<script type="text/javascript" src="../assets/frame/ext/ui/uploader/swfupload.js"></script>
	<script type="text/javascript" src="../assets/frame/ext/ui/uploader/UploadPanel.js"></script>
	<script type="text/javascript" src="../back/js/attachment/AttachmentWindow.js"></script>
	<script type="text/javascript" src="../back/js/home/home.js"></script>
    <script type="text/javascript" src="../back/js/index/index.js"></script>    
  </head>
   <body oncontextmenu="return false">
  	<div id="app-header">
		<div id="header-left">
			<img id ="CompanyLogo" src="../assets/img/back/index/ht-logo.png" height="50" style="max-width:230px;"/>
		</div>
		<div id="header-main">
			<div id="topInfoPanel" style="float:left;padding-bottom: 4px">
				<div id="welcomeMsg">欢迎您，${sessionScope.sysuser.username}，[<a href="/joffice/j_logout.do">注销</a>]</div>
				<div id="currentTime"><span id="nowTime"></span><span id="nowTime2"></span></div>
			</div>
			<div class="clear"></div>
			<ul id="header-topnav">
				<li class="activeli"><a href="#" onclick="App.MyDesktopClick()" class="menu-desktop">桌面</a></li>
				<li class="commonli"><a href="#" onclick="App.clickTopTab('PersonalMailBoxView')" class="menu-mail_box">邮件</a></li>
				<li class="commonli"><a href="#" onclick="App.clickTopTab('CalendarPlanView')" class="menu-task">任务</a></li>
				<li class="commonli"><a href="#" onclick="App.clickTopTab('WorkPlanView')" class="menu-workPlan">计划</a></li>
				<li class="commonli"><a href="#" onclick="App.clickTopTab('PersonalDocumentView')" class="menu-document">文档</a></li>
			</ul>
		</div>
		<div id="header-right">
			<div id="setting">
				<a href="/joffice/help/manual.zip" target="blank">帮助</a>&nbsp;|&nbsp;<a href="#" onclick="App.clickTopTab('SysConfigView')">设置</a>
			</div>
			<div id="searchFormDisplay" style="width:260px;float:right;padding-top:8px;">&nbsp;</div>
		</div>
	</div>
  </body>
</html>
