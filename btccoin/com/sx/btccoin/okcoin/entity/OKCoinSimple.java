package com.sx.btccoin.okcoin.entity;
import java.io.Serializable;
import java.math.BigDecimal;
@SuppressWarnings("serial")
//{"btc":0,"cny":9.91,"ltc":0}
public class OKCoinSimple implements Serializable{
	private BigDecimal btc;
	private BigDecimal cny;
	private BigDecimal ltc;
	public BigDecimal getBtc() {
		return btc;
	}
	public void setBtc(BigDecimal btc) {
		this.btc = btc;
	}
	public BigDecimal getCny() {
		return cny;
	}
	public void setCny(BigDecimal cny) {
		this.cny = cny;
	}
	public BigDecimal getLtc() {
		return ltc;
	}
	public void setLtc(BigDecimal ltc) {
		this.ltc = ltc;
	}
	
}
