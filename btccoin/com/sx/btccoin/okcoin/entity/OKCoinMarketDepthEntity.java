package com.sx.btccoin.okcoin.entity;

import java.io.Serializable;
import java.util.List;
@SuppressWarnings("serial")
/*
 * {"asks":[[7310,0.01]],"bids":[[7124 ,8.212],[7120,8.633]]}
 */
public class OKCoinMarketDepthEntity implements Serializable {
	
	private List<Double[]> bids;
	private List<Double[]> asks;
	public List<Double[]> getBids() {
		return bids;
	}
	public void setBids(List<Double[]> bids) {
		this.bids = bids;
	}
	public List<Double[]> getAsks() {
		return asks;
	}
	public void setAsks(List<Double[]> asks) {
		this.asks = asks;
	}
	
	
}
