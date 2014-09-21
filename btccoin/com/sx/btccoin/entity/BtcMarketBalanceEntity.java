package com.sx.btccoin.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class BtcMarketBalanceEntity implements Serializable{
	private String id;
	private String apikey;
	private Integer marketid;
	private String screct;
	private Date ts;
	private AccountInfoEntity aie;
	/**
	 * 交易费用
	 **/
	private BigDecimal rate;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the apikey
	 */
	public String getApikey() {
		return apikey;
	}
	/**
	 * @param apikey the apikey to set
	 */
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	/**
	 * @return the marketid
	 */
	public Integer getMarketid() {
		return marketid;
	}
	/**
	 * @param marketid the marketid to set
	 */
	public void setMarketid(Integer marketid) {
		this.marketid = marketid;
	}
	/**
	 * @return the screct
	 */
	public String getScrect() {
		return screct;
	}
	/**
	 * @param screct the screct to set
	 */
	public void setScrect(String screct) {
		this.screct = screct;
	}
	/**
	 * @return the ts
	 */
	public Date getTs() {
		return ts;
	}
	/**
	 * @param ts the ts to set
	 */
	public void setTs(Date ts) {
		this.ts = ts;
	}
	/**
	 * @return the aie
	 */
	public AccountInfoEntity getAie() {
		return aie;
	}
	/**
	 * @param aie the aie to set
	 */
	public void setAie(AccountInfoEntity aie) {
		this.aie = aie;
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
	
}
