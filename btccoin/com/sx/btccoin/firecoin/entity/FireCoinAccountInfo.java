package com.sx.btccoin.firecoin.entity;

import java.math.BigDecimal;
import java.util.List;

/*
 {"total":"1039.21",
 "net_asset":"1039.21",
 "available_cny_display":"1.24",
 "available_btc_display":"0.3000",
 "frozen_cny_display":"0.00",
 "frozen_btc_display":"0.0000",
 "loan_cny_display":"0.00",
 "loan_btc_display":"0.0000"}
 */
public class FireCoinAccountInfo {
	private BigDecimal total;
	private BigDecimal net_asset;
	private BigDecimal available_cny_display;
	private BigDecimal available_btc_display;
	private BigDecimal frozen_cny_display;
	private BigDecimal frozen_btc_display;
	private BigDecimal loan_cny_display;
	private BigDecimal loan_btc_display;
	
	public BigDecimal getFrozen_btc_display() {
		return frozen_btc_display;
	}
	public void setFrozen_btc_display(BigDecimal frozen_btc_display) {
		this.frozen_btc_display = frozen_btc_display;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getNet_asset() {
		return net_asset;
	}
	public void setNet_asset(BigDecimal net_asset) {
		this.net_asset = net_asset;
	}
	public BigDecimal getAvailable_cny_display() {
		return available_cny_display;
	}
	public void setAvailable_cny_display(BigDecimal available_cny_display) {
		this.available_cny_display = available_cny_display;
	}
	public BigDecimal getAvailable_btc_display() {
		return available_btc_display;
	}
	public void setAvailable_btc_display(BigDecimal available_btc_display) {
		this.available_btc_display = available_btc_display;
	}
	public BigDecimal getFrozen_cny_display() {
		return frozen_cny_display;
	}
	public void setFrozen_cny_display(BigDecimal frozen_cny_display) {
		this.frozen_cny_display = frozen_cny_display;
	}
	public BigDecimal getLoan_cny_display() {
		return loan_cny_display;
	}
	public void setLoan_cny_display(BigDecimal loan_cny_display) {
		this.loan_cny_display = loan_cny_display;
	}
	public BigDecimal getLoan_btc_display() {
		return loan_btc_display;
	}
	public void setLoan_btc_display(BigDecimal loan_btc_display) {
		this.loan_btc_display = loan_btc_display;
	}
}
