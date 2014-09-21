package com.sx.btccoin.firecoin.entity;

import java.math.BigDecimal;
import java.util.List;

public class FireCoinDepth {
	private List<BigDecimal[]> asks;
	private List<BigDecimal[]> bids;
	public List<BigDecimal[]> getAsks() {
		return asks;
	}
	public void setAsks(List<BigDecimal[]> asks) {
		this.asks = asks;
	}
	public List<BigDecimal[]> getBids() {
		return bids;
	}
	public void setBids(List<BigDecimal[]> bids) {
		this.bids = bids;
	}
}
