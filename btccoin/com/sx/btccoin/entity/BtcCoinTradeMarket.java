package com.sx.btccoin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
/**
 * CREATE TABLE `sx_btc_trade_market` (
  `t_id` int(50) NOT NULL AUTO_INCREMENT,
  `t_sellmarketid` int(11) NOT NULL,
  `t_sellprice` decimal(10,5) NOT NULL DEFAULT '0.00000',
  `t_sellqty` decimal(10,5) NOT NULL DEFAULT '0.00000',
  `t_sellusprice` decimal(10,5) NOT NULL DEFAULT '0.00000',
  `t_selltradestatus` int(11) NOT NULL DEFAULT '0',
  `t_buymarketid` int(11) NOT NULL DEFAULT '0',
  `t_buyprice` decimal(10,5) NOT NULL DEFAULT '0.00000',
  `t_buyqty` decimal(10,5) NOT NULL DEFAULT '0.00000',
  `t_buyusprice` decimal(10,5) NOT NULL DEFAULT '0.00000',
  `t_buytradestatus` int(11) NOT NULL DEFAULT '0',
  `t_status` int(11) NOT NULL DEFAULT '0',
  `t_tradeid` varchar(50) DEFAULT '',
  `t_tradeqty` decimal(10,5) DEFAULT '0.00000' COMMENT '交易的数量',
  `ts` datetime DEFAULT NULL,
  `t_cancelstatus` int(11) DEFAULT '0' COMMENT '0=未取消 1=已经取消',
  `version` int(11) DEFAULT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;*/
public class BtcCoinTradeMarket implements Serializable {
	private Integer id;
	 private int version;   
	private String tradeid;
	/**
	 * 可以交易的数量
	 **/
	private BigDecimal tradeqty;
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=MtGOx
	 *5=火币网
	 **/
	private Integer buymarketid;
	private Integer buytradestatus;
	private String buymarketname;
	private BigDecimal buyprice;
	private BigDecimal buyqty;
	private BigDecimal buyusprice;
	private BigDecimal buycharge;
	
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=MtGOx
	 *5=火币网
	 **/
	private Integer sellmarketid;
	private Integer selltradestatus;
	private String sellmarketname;
	private BigDecimal sellprice;
	private BigDecimal sellqty;
	private BigDecimal sellcharge;
	private BigDecimal sellusprice;
	/**
	 *0=可以执行 
	 *1=执行中
	 *2=执行成功
	 *3=执行完了 有一方没执行成功
	 *4=执行完了 把没执行成功的对应的买/卖给退了，成功退了
	 *5=执行过程中, 发现余额不足或者没有可用的Btc的数量
	 *6=正在执行
	 *7=返回委卖那里没有执行成功,即没有成功买进
	 *8=返回委买那里没有执行成功,即没有成功卖出
	 *9=两个地方都没执行成功
	 *-1=没有凑成交易对列的,仅用来进行分析的数据
	 */
	private Integer status;
	private Double rate;
	private Date ts;
	/*
	 * 0=未取消 1=已经取消
	 */
	private Integer cancelstatus;
	/*
	 * 0=未取消 1=已经取消
	 */
	public Integer getCancelstatus() {
		return cancelstatus;
	}
	/*
	 * 0=未取消 1=已经取消
	 */
	public void setCancelstatus(Integer cancelstatus) {
		this.cancelstatus = cancelstatus;
	}
	public BigDecimal getTradeqty() {
		return tradeqty;
	}
	public void setTradeqty(BigDecimal tradeqty) {
		this.tradeqty = tradeqty;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTradeid() {
		return tradeid;
	}
	public void setTradeid(String tradeid) {
		this.tradeid = tradeid;
	}
	public Integer getBuymarketid() {
		return buymarketid;
	}
	public void setBuymarketid(Integer buymarketid) {
		this.buymarketid = buymarketid;
	}
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=MtGOx
	 *5=火币网
	 **/
	public String getBuymarketname() {
		switch (buymarketid) {
			case 1:
				buymarketname = "比特币中国";
				break;
			case 2:
				buymarketname = "OKCoin";
				break;
			case 3:
				buymarketname = "769市场";		
				break;
			case 4:
				buymarketname = "MtGOx";
				break;
			case 5:
				buymarketname = "火币网";
				break;
		}
		return buymarketname;
	}
	public void setBuymarketname(String buymarketname) {
		this.buymarketname = buymarketname;
	}
	public BigDecimal getBuyprice() {
		return buyprice;
	}
	public void setBuyprice(BigDecimal buyprice) {
		this.buyprice = buyprice;
	}
	public BigDecimal getBuyqty() {
		return buyqty;
	}
	public void setBuyqty(BigDecimal buyqty) {
		this.buyqty = buyqty;
	}
	public BigDecimal getBuyusprice() {
		return buyusprice;
	}
	public void setBuyusprice(BigDecimal buyusprice) {
		this.buyusprice = buyusprice;
	}
	public BigDecimal getBuycharge() {
		return buycharge;
	}
	public void setBuycharge(BigDecimal buycharge) {
		this.buycharge = buycharge;
	}
	public Integer getSellmarketid() {
		return sellmarketid;
	}
	public void setSellmarketid(Integer sellmarketid) {
		this.sellmarketid = sellmarketid;
	}
	/**
	 *1=比特币中国
	 *2=OKCoin
	 *3=769市场
	 *4=MtGOx
	 *5=火币网
	 **/
	public String getSellmarketname() {
		switch (sellmarketid) {
			case 1:
				sellmarketname = "比特币中国";
				break;
			case 2:
				sellmarketname = "OKCoin";
				break;
			case 3:
				sellmarketname = "769市场";		
				break;
			case 4:
				sellmarketname = "MtGOx";
				break;
			case 5:
				sellmarketname = "火币网";
				break;
		}
		return sellmarketname;
	}
	public void setSellmarketname(String sellmarketname) {
		this.sellmarketname = sellmarketname;
	}
	public BigDecimal getSellprice() {
		return sellprice;
	}
	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}
	public BigDecimal getSellqty() {
		return sellqty;
	}
	public void setSellqty(BigDecimal sellqty) {
		this.sellqty = sellqty;
	}
	public BigDecimal getSellcharge() {
		return sellcharge;
	}
	public void setSellcharge(BigDecimal sellcharge) {
		this.sellcharge = sellcharge;
	}
	public BigDecimal getSellusprice() {
		return sellusprice;
	}
	public void setSellusprice(BigDecimal sellusprice) {
		this.sellusprice = sellusprice;
	}
	/**
	 *0=可以执行 
	 *1=执行成功
	 *2=执行完了 有一方没执行成功
	 *3=执行完了 把没执行成功的对应的买/卖给退了，成功退了
	 *4=执行过程中, 发现余额不足或者没有可用的Btc的数量
	 *5=正在执行
	 *6=返回委卖那里没有执行成功,即没有成功买进
	 *7=返回委买那里没有执行成功,即没有成功卖出
	 *8=两个地方都没执行成功
	 *-1=没有凑成交易对列的,仅用来进行分析的数据
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 *0=可以执行 
	 *1=执行成功
	 *2=执行完了 有一方没执行成功
	 *3=执行完了 把没执行成功的对应的买/卖给退了，成功退了
	 *4=执行过程中, 发现余额不足或者没有可用的Btc的数量
	 *5=正在执行
	 *6=返回委卖那里没有执行成功,即没有成功买进
	 *7=返回委买那里没有执行成功,即没有成功卖出
	 *8=两个地方都没执行成功
	 *-1=没有凑成交易对列的,仅用来进行分析的数据
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	public Integer getBuytradestatus() {
		return buytradestatus;
	}
	public void setBuytradestatus(Integer buytradestatus) {
		this.buytradestatus = buytradestatus;
	}
	public Integer getSelltradestatus() {
		return selltradestatus;
	}
	public void setSelltradestatus(Integer selltradestatus) {
		this.selltradestatus = selltradestatus;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
