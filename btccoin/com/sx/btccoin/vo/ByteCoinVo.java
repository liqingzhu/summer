package com.sx.btccoin.vo;

import java.math.BigDecimal;
import java.util.List;


public class ByteCoinVo  {
	private String pkid;
	private Integer times;
	/**
	 *是否获取全部的历史交易记录 
	 *0= 10条
	 *1= 全部
	 **/
	private Integer getall;
	private Double rate;
	private Integer cursor;
	private Double diff;
	private String msg;
	private Integer switchinfo;
	private Double extra;
	private Double minqty;
	private Integer switchvalue;
	private String startdate;
	private String enddate;
	private String tradedate;
	private Integer tradestatus;
	private Double maxtradeqty;
//	private List<FireCoinEntity> firevos;
	
	private Integer period;
	private Integer marketid;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 获取Trade数据信息
	 **/
	private BigDecimal btcchinaqty;
	private BigDecimal btcchinabalance;
	private BigDecimal okcoinqty;
	private BigDecimal okcoinbalance;
	/**
	 *0= BTCChina 买 OKCOIN 卖 
	 *1= OKCOIN 买 BTCChina 卖
	 **/
	private Integer tradetype;
	private String tradetypestr;
	
	private Integer balancetime;
    private BigDecimal chargefee;
    private Integer depthtime;
    private BigDecimal maxqty;
    private Integer ntime;
    private BigDecimal tadediff;
	
    
	/**
	 * @return the balancetime
	 * 账户刷新频率
	 */
	public Integer getBalancetime() {
		return balancetime;
	}


	/**
	 * @param balancetime the balancetime to set
	 * 账户刷新频率
	 */
	public void setBalancetime(Integer balancetime) {
		this.balancetime = balancetime;
	}


	/**
	 * @return the chargefee
	 * 手续费
	 */
	public BigDecimal getChargefee() {
		return chargefee;
	}


	/**
	 * @param chargefee the chargefee to set
	 * 手续费
	 */
	public void setChargefee(BigDecimal chargefee) {
		this.chargefee = chargefee;
	}


	/**
	 * @return the depthtime
	 * 交易深度
	 */
	public Integer getDepthtime() {
		return depthtime;
	}


	/**
	 * @param depthtime the depthtime to set
	 * 交易深度
	 */
	public void setDepthtime(Integer depthtime) {
		this.depthtime = depthtime;
	}


	/**
	 * @return the maxqty
	 * 最大交易数量
	 */
	public BigDecimal getMaxqty() {
		return maxqty;
	}


	/**
	 * @param maxqty the maxqty to set
	 * 最大交易数量
	 */
	public void setMaxqty(BigDecimal maxqty) {
		this.maxqty = maxqty;
	}


	/**
	 * @return the ntime
	 * 第几个记录
	 */
	public Integer getNtime() {
		return ntime;
	}


	/**
	 * @param ntime the ntime to set
	 * 第几个记录
	 */
	public void setNtime(Integer ntime) {
		this.ntime = ntime;
	}


	/**
	 * @return the tadediff
	 * 交易差额
	 */
	public BigDecimal getTadediff() {
		return tadediff;
	}


	/**
	 * @param tadediff the tadediff to set
	 * 交易差额
	 */
	public void setTadediff(BigDecimal tadediff) {
		this.tadediff = tadediff;
	}


	public String getTradedate() {
		return tradedate;
	}


	public void setTradedate(String tradedate) {
		this.tradedate = tradedate;
	}


	public Integer getTimes() {
		return times;
	}
	
	
	public Double getMaxtradeqty() {
		return maxtradeqty;
	}


	public void setMaxtradeqty(Double maxtradeqty) {
		this.maxtradeqty = maxtradeqty;
	}


	public void setTimes(Integer times) {
		this.times = times;
	}


	public String getTradetypestr() {
		if (tradetype!=null&&tradetype.equals(0)) {
			tradetypestr = "BTCChina 买 OKCOIN 卖";
		} else {
			tradetypestr = "OKCOIN 买 BTCChina 卖";
		}
		return tradetypestr;
	}
	

	public String getPkid() {
		return pkid;
	}


	public void setPkid(String pkid) {
		this.pkid = pkid;
	}


	public void setTradetypestr(String tradetypestr) {
		this.tradetypestr = tradetypestr;
	}


	public BigDecimal getBtcchinaqty() {
		return btcchinaqty;
	}


	public void setBtcchinaqty(BigDecimal btcchinaqty) {
		this.btcchinaqty = btcchinaqty;
	}


	public BigDecimal getBtcchinabalance() {
		return btcchinabalance;
	}


	public void setBtcchinabalance(BigDecimal btcchinabalance) {
		this.btcchinabalance = btcchinabalance;
	}


	public BigDecimal getOkcoinqty() {
		return okcoinqty;
	}


	public void setOkcoinqty(BigDecimal okcoinqty) {
		this.okcoinqty = okcoinqty;
	}


	public BigDecimal getOkcoinbalance() {
		return okcoinbalance;
	}


	public void setOkcoinbalance(BigDecimal okcoinbalance) {
		this.okcoinbalance = okcoinbalance;
	}


	public Integer getTradetype() {
		return tradetype;
	}


	public void setTradetype(Integer tradetype) {
		this.tradetype = tradetype;
	}


	public Integer getSwitchvalue() {
		return switchvalue;
	}


	public void setSwitchvalue(Integer switchvalue) {
		this.switchvalue = switchvalue;
	}


	public Double getRate() {
		return rate;
	}


	public void setRate(Double rate) {
		this.rate = rate;
	}


	

	public Integer getCursor() {
		return cursor;
	}


	public void setCursor(Integer cursor) {
		this.cursor = cursor;
	}


	public Double getDiff() {
		return diff;
	}


	public void setDiff(Double diff) {
		this.diff = diff;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Double getExtra() {
		return extra;
	}


	public void setExtra(Double extra) {
		this.extra = extra;
	}


	public Integer getSwitchinfo() {
		return switchinfo;
	}


	public void setSwitchinfo(Integer switchinfo) {
		this.switchinfo = switchinfo;
	}


	public Integer getGetall() {
		return getall;
	}


	public void setGetall(Integer getall) {
		this.getall = getall;
	}


	public Double getMinqty() {
		return minqty;
	}


	public void setMinqty(Double minqty) {
		this.minqty = minqty;
	}


	public String getStartdate() {
		return startdate;
	}


	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}


	public String getEnddate() {
		return enddate;
	}


	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	/**
	 *0=可以执行 等待交易
	 *1=执行成功
	 *2=执行完了 有一方没执行成功
	 *3=执行完了 把没执行成功的对应的买/卖给退了，成功退了
	 *4=执行过程中, 发现余额不足或者没有可用的Btc的数量
	 *5=正在执行
	 *6=返回委卖那里没有执行成功,即没有成功买进(仅委买成功)
	 *7=返回委买那里没有执行成功,即没有成功卖出(仅委卖成功)
	 *8=两个地方都没执行成功
	 *-1=没有凑成交易对列的,仅用来进行分析的数据
	 */
	public Integer getTradestatus() {
		return tradestatus;
	}
	/**
	 *0=可以执行 等待交易
	 *1=执行成功
	 *2=执行完了 有一方没执行成功
	 *3=执行完了 把没执行成功的对应的买/卖给退了，成功退了
	 *4=执行过程中, 发现余额不足或者没有可用的Btc的数量
	 *5=正在执行
	 *6=返回委卖那里没有执行成功,即没有成功买进(仅委买成功)
	 *7=返回委买那里没有执行成功,即没有成功卖出(仅委卖成功)
	 *8=两个地方都没执行成功
	 *-1=没有凑成交易对列的,仅用来进行分析的数据
	 */
	public void setTradestatus(Integer tradestatus) {
		this.tradestatus = tradestatus;
	}


	


	public Integer getPeriod() {
		return period;
	}


	public void setPeriod(Integer period) {
		this.period = period;
	}


	public Integer getMarketid() {
		return marketid;
	}


	public void setMarketid(Integer marketid) {
		this.marketid = marketid;
	}
	
}
