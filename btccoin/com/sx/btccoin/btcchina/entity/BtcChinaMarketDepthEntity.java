package com.sx.btccoin.btcchina.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BtcChinaMarketDepthEntity implements Serializable {
	private BtcChinaMraketDepthResultEntity result;
	private Integer id;
	public BtcChinaMraketDepthResultEntity getResult() {
		return result;
	}
	public void setResult(BtcChinaMraketDepthResultEntity result) {
		this.result = result;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
