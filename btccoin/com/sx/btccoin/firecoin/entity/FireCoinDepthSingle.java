package com.sx.btccoin.firecoin.entity;

import java.math.BigDecimal;

/*
{"sells":[{"price":3813,"level":1,"amount":2},
{"price":"3813.89","level":1,"amount":1.6}],
"buys":[{"price":"3812.83","level":1,"amount":0.1406},{"price":"3812.81","level":1,"amount":1.0385}],
"trades":[{"time":"23:07:21","price":3813,"amount":1.578,"type":"卖出"},{"time":"23:07:21","price":3813.97,"amount":5.5692,"type":"买入"}],
"p_new":3813,"level":297,"amount":226870,"total":850516360.16484,"amp":8,"p_open":3516,"p_high":3920,"p_low":3515.99,"p_last":3516,
"top_sell":{
"0":{"price":3813,"level":1,"amount":2,"accu":2},
"1":{"price":"3813.89","level":1,"amount":1.6javascript:void(0),"accu":3.6},
"2":{"price":"3813.97","level":1,"amount":6.8848,"accu":10.4848},
"3":{"price":3814,"level":1,"amount":0.137,"accu":10.6218},
"4":{"price":"3814.3","level":1,"amount":1.1787,"accu":11.8005}},
"top_buy":[{"price":"3812.83","level":1,"amount":0.1406,"accu":0.1406},
{"price":"3812.81","level":1,"amount":1.0385,"accu":1.1791},{"price":"3812.07","level":1,"amount":2.1015,"accu":3.2806},
{"price":"3812.04","level":1,"amount":0.2,"accu":3.4806},{"price":"3812.01","level":1,"amount":2.3085,"accu":5.7891}]}*/
public class FireCoinDepthSingle {
	private BigDecimal price;
	private BigDecimal amount;
	private Integer level;
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
