package com.sx.btccoin.btcchina.entity;
import java.io.Serializable;
import java.math.BigDecimal;
@SuppressWarnings("serial")
/*
 "cny":{
            "currency":"CNY",
            "symbol":"\u00a5",
            "amount":0,
            "amount_integer":"",
            "amount_decimal":5
        }
 * */
public class BtcChinaAccountInfoUnit implements Serializable{
	private String currency;
	private String symbol;
	private BigDecimal amount;
	private String amount_integer;
    private Integer amount_decimal;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAmount_integer() {
		return amount_integer;
	}
	public void setAmount_integer(String amountInteger) {
		amount_integer = amountInteger;
	}
	public Integer getAmount_decimal() {
		return amount_decimal;
	}
	public void setAmount_decimal(Integer amountDecimal) {
		amount_decimal = amountDecimal;
	}
}
