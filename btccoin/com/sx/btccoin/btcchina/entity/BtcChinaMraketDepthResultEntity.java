package com.sx.btccoin.btcchina.entity;

import java.io.Serializable;


@SuppressWarnings("serial")
public class BtcChinaMraketDepthResultEntity implements Serializable {
	private BtcChinaMarketDE market_depth;
	
	public BtcChinaMarketDE getMarket_depth() {
		return market_depth;
	}
	public void setMarket_depth(BtcChinaMarketDE marketDepth) {
		market_depth = marketDepth;
	}
}
