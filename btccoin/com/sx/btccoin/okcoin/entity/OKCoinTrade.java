package com.sx.btccoin.okcoin.entity;

import java.math.BigDecimal;

/**
 *  "amount" : "0.1",
*	"date" : 1407676343000,
*	"price" : "43.09",
*	"tid" : 43205318,
*	"type" : "sell" 
 ***/
public class OKCoinTrade {
	private BigDecimal amount;
	private long date;
	private BigDecimal price;
	private Integer tid;
	private String type;
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the date
	 */
	public long getDate() {
//		date = Long.parseLong(date+"000");
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(long date) {
		this.date = date;
	}
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
	 * @return the tid
	 */
	public Integer getTid() {
		return tid;
	}
	/**
	 * @param tid the tid to set
	 */
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
