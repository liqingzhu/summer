package com.sx.btccoin.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class BtcCoinTradeMarketInfo implements Serializable {
	private Integer id;
	private String pkid;
	private Date ts;
	private List<BtcCoinTradeMarket> list;
	private String result;
	private String tradeid;
	
	public BtcCoinTradeMarketInfo() {
		ts = new Date();
	}
	/**
	 *0=失败
	 *1=成功 
	 **/
	private Integer status;
	/**
	 *0=失败
	 *1=成功 
	 **/
	public Integer getStatus() {
		return status;
	}
	/**
	 *0=失败
	 *1=成功 
	 **/
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<BtcCoinTradeMarket> getList() {
		return list;
	}
	public void setList(List<BtcCoinTradeMarket> list) {
		this.list = list;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTradeid() {
		return tradeid;
	}
	public void setTradeid(String tradeid) {
		this.tradeid = tradeid;
	}
	public String getPkid() {
		return pkid;
	}
	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	
}
