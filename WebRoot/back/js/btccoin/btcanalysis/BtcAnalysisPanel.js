
BtcAnalysisPanel = function() {
	var config = {
		layout : 'fit',
		height : 500,
		autoLoad:{
			url:'/summer/back/jsp/btc/markets.jsp',  
			scripts: true
		},
		title : 'BTC搬砖分析',
		iconCls : 'money_euro',
		id : 'BtcAnalysisPanel'
	};
	BtcAnalysisPanel.superclass.constructor.call(this, config);
};
Ext.extend(BtcAnalysisPanel, Ext.Panel);