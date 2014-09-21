package com.sx.btccoin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
/**
 *   t_id int not null default '' primary key,
	t_marketid int  not null,
	t_price decimal(10,5) not null,
	t_qty decimal(10,5) not null,
	t_usprice decimal(10,5) not null,
	t_type int not null default 0,
	t_markettradeid varchar(50) not null,
 *   ts date default null    
 *获取各系统交易成功记录数据信息
 */
public class BtcCoinTradeDepthRec implements Serializable {
	private Integer id;
	private String date;
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=MtGOx
	 *5=火币网
	 **/
	private Integer marketid;
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=MtGOx
	 *5=火币网
	 **/
	private String marketname;
	private BigDecimal price;
	private BigDecimal qty;
	////委卖是1 委买是0
	private Integer type;
	private BigDecimal usprice;
	private String tradeid;
	private Date ts;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=MtGOx
	 *5=火币网
	 **/
	public Integer getMarketid() {
		return marketid;
	}
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=MtGOx
	 *5=火币网
	 **/
	public void setMarketid(Integer marketid) {
		this.marketid = marketid;
	}
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=MtGOx
	 *5=火币网
	 **/
	public String getMarketname() {
		switch (this.getMarketid().intValue()) {
		case 1:
			this.marketname = "比特币中国";
			break;
		case 2:
			this.marketname = "OKCoin";
			break;
		case 5:
			this.marketname = "火币网";
			break;
		default:
			break;
		}
		return marketname;
	}
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=MtGOx
	 *5=火币网
	 **/
	public void setMarketname(String marketname) {
		this.marketname = marketname;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	
}
