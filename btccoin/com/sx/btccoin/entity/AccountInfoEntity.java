package com.sx.btccoin.entity;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class AccountInfoEntity implements Serializable {
	private Integer id;
	private String username;
	private String pwd;
	private String tradecode;
	private String marketid;
	private BigDecimal balance;
	private BigDecimal bytecoinqty;
	private BigDecimal frozenbal;
	private BigDecimal frozenqty;
	private BigDecimal leftbal;
	private BigDecimal leftqty;
	private String marketname;
	private BigDecimal rate;
	private Integer status;
	
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	/**
	 * @return the marketid
	 */
	public String getMarketid() {
		return marketid;
	}
	/**
	 * @param marketid the marketid to set
	 */
	public void setMarketid(String marketid) {
		this.marketid = marketid;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return the tradecode
	 */
	public String getTradecode() {
		return tradecode;
	}
	/**
	 * @param tradecode the tradecode to set
	 */
	public void setTradecode(String tradecode) {
		this.tradecode = tradecode;
	}
	/**
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	/**
	 * @return the bytecoinqty
	 */
	public BigDecimal getBytecoinqty() {
		return bytecoinqty;
	}
	/**
	 * @param bytecoinqty the bytecoinqty to set
	 */
	public void setBytecoinqty(BigDecimal bytecoinqty) {
		this.bytecoinqty = bytecoinqty;
	}
	/**
	 * @return the frozenbal
	 */
	public BigDecimal getFrozenbal() {
		return frozenbal;
	}
	/**
	 * @param frozenbal the frozenbal to set
	 */
	public void setFrozenbal(BigDecimal frozenbal) {
		this.frozenbal = frozenbal;
	}
	/**
	 * @return the frozenqty
	 */
	public BigDecimal getFrozenqty() {
		return frozenqty;
	}
	/**
	 * @param frozenqty the frozenqty to set
	 */
	public void setFrozenqty(BigDecimal frozenqty) {
		this.frozenqty = frozenqty;
	}
	/**
	 * @return the leftbal
	 */
	public BigDecimal getLeftbal() {
		return leftbal;
	}
	/**
	 * @param leftbal the leftbal to set
	 */
	public void setLeftbal(BigDecimal leftbal) {
		this.leftbal = leftbal;
	}
	/**
	 * @return the leftqty
	 */
	public BigDecimal getLeftqty() {
		return leftqty;
	}
	/**
	 * @param leftqty the leftqty to set
	 */
	public void setLeftqty(BigDecimal leftqty) {
		this.leftqty = leftqty;
	}
	/**
	 * @return the marketname
	 */
	public String getMarketname() {
		Integer id = Integer.parseInt(this.marketid);
		switch (id) {
			case 1: marketname = "BTCChina";break;
			case 2: marketname = "OKCoin";break;
			case 5: marketname = "FireCoin";break;
		}
		return marketname;
	}
	/**
	 * @param marketname the marketname to set
	 */
	public void setMarketname(String marketname) {
		this.marketname = marketname;
	}
	
	
}
