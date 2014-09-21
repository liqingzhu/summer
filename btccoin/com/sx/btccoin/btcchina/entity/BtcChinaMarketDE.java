package com.sx.btccoin.btcchina.entity;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class BtcChinaMarketDE implements Serializable {
	private List<BtcChinaMarketDEUnit> bid;
	private List<BtcChinaMarketDEUnit> ask;
	public List<BtcChinaMarketDEUnit> getBid() {
		return bid;
	}
	public void setBid(List<BtcChinaMarketDEUnit> bid) {
		this.bid = bid;
	}
	public List<BtcChinaMarketDEUnit> getAsk() {
		return ask;
	}
	public void setAsk(List<BtcChinaMarketDEUnit> ask) {
		this.ask = ask;
	}
	
}
