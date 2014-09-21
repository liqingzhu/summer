package com.sx.btccoin.entity;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
/**
 *
CREATE TABLE `trade_depthrec` (
  `t_id` varchar(50) NOT NULL DEFAULT '',
  `t_marketid` int(11) NOT NULL,
  `t_price` decimal(10,5) NOT NULL,
  `t_qty` decimal(10,5) NOT NULL,
  `t_usprice` decimal(10,5) DEFAULT '0.00000',
  `t_type` int(11) NOT NULL DEFAULT '0' COMMENT '0=买 1=卖',
  `t_markettradeid` varchar(20) DEFAULT NULL,
  `t_markettradetime` varchar(35) DEFAULT NULL,
  `t_tradeid` varchar(50) NOT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
 **/
public class ByteCoinTradeEntity implements Serializable {
	private Integer status;
	private String message;
	private String tradeid;
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=Mt.Gox
	 *5=火币网
	 **/
	private Integer marketid;
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=Mt.Gox
	 *5=火币网
	 **/
	private String marketname;
	
	/**
	 *价格 
	 **/
	private BigDecimal price;
	/**
	 *美元价格 
	 **/
	private BigDecimal usprice;
	/**
	 *数量
	 **/
	private BigDecimal qty;
	/**
	 *货币类型
	 *0=人民币
	 *1=美金 
	 **/
	private Integer curtype;
	/**
	 *0=sell
	 *1=buy
	 **/
	private Integer tradetype;
	/**
	 *交易费用
	 **/
	private Double chargefee;
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=Mt.Gox
	 *5=火币网
	 **/
	public String getMarketname() {
		if (marketid==1) {
			marketname = "比特币中国";
		} else if(marketid==2){
			marketname = "OKCoin";
		}else if(marketid==3){
			marketname = "769市场";
		}else if(marketid==4){
			marketname = "Mt.Gox";
		}else if(marketid==5){
			marketname = "火币网";
		}
		return marketname;
	}/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=Mt.Gox
	 *5=火币网
	 **/
	public void setMarketname(String marketname) {
		this.marketname = marketname;
	}
	/**
	 *交易费用 
	 **/
	public Double getChargefee() {
		return chargefee;
	}
	/**
	 *交易费用 
	 **/
	public void setChargefee(Double chargefee) {
		this.chargefee = chargefee;
	}
	/**
	 *美元汇率 
	 **/
	private Double uscurreny;
	
	public Double getUscurreny() {
		return uscurreny;
	}
	public void setUscurreny(Double uscurreny) {
		this.uscurreny = uscurreny;
	}
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=Mt.Gox
	 *5=火币网
	 **/
	public Integer getMarketid() {
		return marketid;
	}
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=Mt.Gox
	 *5=火币网
	 **/
	public void setMarketid(Integer marketid) {
		this.marketid = marketid;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	/**
	 *货币类型
	 *0=人民币
	 *1=美金 
	 **/
	public Integer getCurtype() {
		return curtype;
	}
	/**
	 *货币类型
	 *0=人民币
	 *1=美金 
	 **/
	public void setCurtype(Integer curtype) {
		this.curtype = curtype;
	}
	/**
	 *0=sell
	 *1=buy
	 **/
	public Integer getTradetype() {
		return tradetype;
	}
	/**
	 *0=sell
	 *1=buy
	 **/
	public void setTradetype(Integer tradetype) {
		this.tradetype = tradetype;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BigDecimal getUsprice() {
		return usprice;
	}
	public void setUsprice(BigDecimal usprice) {
		this.usprice = usprice;
	}
	public String getTradeid() {
		return tradeid;
	}
	public void setTradeid(String tradeid) {
		this.tradeid = tradeid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
