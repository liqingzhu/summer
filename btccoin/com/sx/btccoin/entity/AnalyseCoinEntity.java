package com.sx.btccoin.entity;

import java.math.BigDecimal;
import java.io.Serializable;
@SuppressWarnings("serial")
public class AnalyseCoinEntity implements Serializable{
	private BigDecimal price;
	private BigDecimal qty;
	private Long date;
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the qty
	 */
	public BigDecimal getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	/**
	 * @return the date
	 */
	public Long getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Long date) {
		this.date = date;
	}
	
}
