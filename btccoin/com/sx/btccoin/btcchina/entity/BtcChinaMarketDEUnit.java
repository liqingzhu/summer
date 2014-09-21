package com.sx.btccoin.btcchina.entity;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class BtcChinaMarketDEUnit implements Serializable {
	private BigDecimal price;
	private BigDecimal amount;
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
	
}
