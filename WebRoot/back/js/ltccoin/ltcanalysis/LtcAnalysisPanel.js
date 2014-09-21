LtcAnalysisPanel = function() {
	
	var url = __ctxPath+'/back/ltc/gettradeinfo.jspx';
	var store = new Ext.data.JsonStore({
        fields:['name', 'visits', 'views','amount','date','price','tid','type'],
        url:url,
        autoLoad:true
    });
    
    var tradestoreloader = function(){
    	store.reload();
    }
    var task = {
	    run: tradestoreloader,
	    interval: 1000 //1 second
	}
	//depthrunner 深度定时器
	var runner = new Ext.util.TaskRunner();
	
	var config = {
		listeners :{
			'render':function(){},
			'afterrender':function(){
//				runner.start(task);
			}
		},
		layout : 'fit',
		items: {
			y:1000,
            xtype: 'linechart',
            store: store,
            url: '../assets/frame/ext/resources/charts.swf',
            xField: 'date',
            series:[
                {type:'line',displayName:'买入价格',yField:'price',style:{color:0xCCFF00}},  
                {type:'line',displayName:'卖出价格',yField:'price',style:{color:0xCC0000}}
            ], 
            xAxis: new Ext.chart.TimeAxis({
                displayName: '时间',
                labelRenderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')
            }),
            yAxis: new Ext.chart.NumericAxis({
                displayName: '价格',
                labelRenderer : Ext.util.Format.numberRenderer('10')
            }),
            tipRenderer : function(chart, record){
            	return '-|'+record.get('price')+'|-';
//                return Ext.util.Format.number(record.data.price, '0.00') +'-'+Ext.util.Format.date(record.data.date,'Y-m-d H:i:s') +'-' + record.data.amount;
            },
            extraStyle:{  
                legend: {  
                    display: 'bottom',  
                    padding: 5,  
                    font:  {family: 'Tahoma',size: 13 }  
                }  
            } 
        },
		title : 'LTC深入分析',
		iconCls : 'money_euro',
		id : 'LtcAnalysisPanel'
	};
	LtcAnalysisPanel.superclass.constructor.call(this, config);
};
Ext.extend(LtcAnalysisPanel, Ext.Panel);