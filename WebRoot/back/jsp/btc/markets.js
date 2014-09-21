$(function() {
  var times = [];
  var getDataInfo = function(type,data){
  	 //仅获取卖的数量
  	 var qtydata = [];
  	 if(type=='average'){
  	 	 for (var i = 0; i < data.length; i++) {
	  	 	var datainfo = [data[i].date,data[i].price];
			qtydata.push(datainfo);
	  	 }
  	 }else{
	  	 for (var i = 0; i < data.length; i++) {
	  	 	var datainfo = [data[i].date,data[i].qty];
	 		qtydata.push(datainfo);
	  	 }
  	 }
  	 return qtydata;
  }
  //动态添加一个点
  var addPointForType = function(type,series){
  	var url = __ctxPath+'/back/btc/addanalysisbtcpoint.jspx';
  	var config = {
  		url:url,
  		method:'POST',
  		params:{tradetype:type},
  		success:function(response,option){
  			var trade = Ext.decode(response.responseText);
  			if(type==0){
  				var array = [];
  				var x = (new Date()).getTime(),y = trade[trade.length-1].qty;
//  				series.addPoint([x, y], true, true);
//  				console.log("--->"+100);
  				series.addPoint([x, y], true, true);
  			}else if(type==1){
//  				console.log("--->"+35);
  				series.addPoint([(new Date()).getTime(), trade[trade.length-1].qty], true, true);
  			}else{
  				var price = trade[trade.length-1].price;
  				var time = (new Date()).getTime();
//  				console.log(time +'---------'+price);
  				series.addPoint([time, price], true, true);
  			}
  			/*var trade = Ext.decode(response.responseText);
  			var array = [];
  			var x = trade[trade.length-1].date,y = trade[trade.length-1].qty;
  			series.addPoint([x, y], true, true);*/
  		},
  		failure:function(response,option){
  			console.log('26 执行出现了异常');
  		}
  	};
  	Ext.Ajax.request(config);
  }
  /**
   * 获取主动卖的数量用于 
   * 初始化的时候加载一条直线出来
   * 加载主动卖的数据
   **/
  var masterSellQtyForLine = function(chart){
	var config = {
		url:__ctxPath+'/back/btc/btcqtytradeforselling.jspx',
		method:'POST',
		success:function(response,option){
			var arr = Ext.decode(response.responseText);
			var data = getDataInfo(1,arr);
			chart.addSeries({data : data,name:'主动卖',color:'red',yAxis: 0});
			var series =  chart.series[0];
			
			setInterval(function() {
//			   addPointForType(1,series);
			}, 1000);
		},
		failure:function(response,option){
		
		}
	};
	Ext.Ajax.request(config);
  }
   /**
   * 获取主动买的数量用于 
   * 初始化的时候加载一条直线出来
   * 加载主动买的数据
   **/
  var masterBuyQtyForLine = function(chart){
  	var config = {
		url:__ctxPath+'/back/btc/btcqtytradeforbuying.jspx',
		method:'POST',
		success:function(response,option){
			var arr = Ext.decode(response.responseText);
			var data = getDataInfo(0,arr);
			chart.addSeries({data : data,name:'主动买',color:'blue'});
			
//			var series =  chart.series[0];
//			var series1 =  chart.series[1];
			var series =  chart.series[1];
			/*console.log("a--->"+series); 
			console.log("nn---->"+series1); console.log("---->"+series2); 
			addPointForType(0,series);addPointForType(0,series);
			addPointForType(0,series);addPointForType(0,series);
			addPointForType(0,series);addPointForType(0,series);*/
			setInterval(function() {
//			   addPointForType(0,series);
			}, 1000);
		},
		failure:function(response,option){
			
		}
	};
	Ext.Ajax.request(config);
  }
  
  /**
   * 获取各大网站的数据的平均价格
   **/
  var averagePrice = function(chart){
  	var config = {
		url:__ctxPath+'/back/btc/getavergeprice.jspx',
		method:'POST',
		success:function(response,option){
			var arr = Ext.decode(response.responseText);
			var data = getDataInfo('average',arr);
			chart.addSeries({data :data,name:'平均价格',color:'green',yAxis: 1});
			var series =  chart.series[2];
			setInterval(function() {
			   addPointForType(-1,series);
//			   series.addPoint([(new Date()).getTime(), 3211.00], true, true);
			}, 1000);
		},
		failure:function(response,option){
			
		}
	};
	Ext.Ajax.request(config);
  }
	
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	
    //获取主动买的数量数据
    var masterBuyQty = function(){
	  	 // generate an array of random data
		var data = [], time = (new Date()).getTime(), i;
		/*for( i = -1000; i <= 0; i++) {
			data.push([time + i * 1000,Math.round(Math.random() * 100)]);
		}*/
		return data;
	}
	
	var seriesOptions = [
		/*{name: '主动买',yAxis: 0,data : masterBuyQty()},
		{name: '主动卖',yAxis: 0,data : masterSellQty()},
		{name: '总和数量',yAxis: 0,data :masterSumQty()},
		{name: '全部市场平均价格',yAxis: 1,data : averagePrice()}*/
	];
	 
	 
	 
	 
	// Create the chart
	$('#container').highcharts('StockChart', {
		chart : {
			events : {
				load : function() {
					var chart = $('#container').highcharts();
					//添加卖的数量
					masterSellQtyForLine(chart);
					//添加买的数量
					masterBuyQtyForLine(chart);
					//添加平均价格
					averagePrice(chart);
					
//					console.log(chart);
//					masterSellQtyForLine(chart);
					/*chart.addSeries({
						data : [114.1, 95.6, 54.4, 29.9, 71.5,
								106.4, 129.2, 144.0, 176.0,
								135.6, 148.5, 216.4],
						name:'主动卖',
						color:'red'
					});
					chart.addSeries({
						data : [914.1, 195.6, 154.4, 129.9, 171.5,
								16.4, 29.2, 44.0, 76.0,
								15.6, 48.5, 16.4],
						
						name:'主动买',color:'blue'
					});
					chart.addSeries({
						data : [3914.1, 3195.6, 3154.4, 3129.9, 3171.5,
								3216.4, 3129.2, 3144.0, 3176.0,
								3015.6, 3448.5, 3316.4],
						yAxis:1,
						name:'平均价格',color:'green'
					});*/
					/*
					console.log('---147---');
					var series0 =  chart.series[0];
					console.log("====>"+series0);
					var series1 =  chart.series[2];
					setInterval(function() {
						var x = (new Date()).getTime(), 
						    y = Math.round(Math.random() * 100),
						   y1 = Math.round(Math.random() * 100);
						series0.addPoint([x, y], true, true);
						series1.addPoint([x, y1], true, true);
					}, 1000);*/
					
					/*chart.addSeries({
						data : [1914.1, 95.6, 54.4, 29.9, 71.5,
								106.4, 129.2, 144.0, 176.0,
								135.6, 148.5, 216.4]
					});*/
					
					// set up the updating of the chart each second
					/*var series =  chart.series[0];
					console.log(chart.series.length);
					setInterval(function() {
						var x = (new Date()).getTime(), // current time
						y = Math.round(Math.random() * 100);
						y1 = Math.round(Math.random() * 100),
						y2 = Math.round(Math.random() * 100),
						y3 = Math.round(Math.random() * 10000);
						series.addPoint([x, y], true, true);
					}, 1000);*/
				}
			}
		},
		
		rangeSelector: {
			buttons: [{
				count: 1,
				type: 'minute',
				text: '1M'
			}, {
				count: 5,
				type: 'minute',
				text: '5M'
			}, {
				type: 'all',
				text: 'All'
			}],
			inputEnabled: false,
			selected: 0
		},
		
		title : {
			text : '<h1>比特币交易分析平台</h1>'
		},
		
		exporting: {
			enabled: false
		},
		yAxis: [
		    {
		        labels: {align: 'right',x: -3},
		        title: {text: '交易量'},rotation:0,
		        height: '70%',  lineWidth: 2
		    },{
	    		labels: {align: 'right',x: -3},
	            title: {text: '交易价格'},
	            top: '65%',
	            height: '35%',
	            offset: 0,lineWidth: 2
	    	}
	    ],
		series : seriesOptions
	});

});