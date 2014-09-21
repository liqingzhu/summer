package com.sx.btccoin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
*CREATE TABLE `sx_btc_trade_log` (
*  `t_id` int(50) NOT NULL AUTO_INCREMENT,
*  `t_marketid` int(11) NOT NULL COMMENT '1=btcchina,2=okcoin,3=firecoin',
*  `t_price` decimal(10,5) NOT NULL DEFAULT '0.00000',
*  `t_qty` decimal(10,5) NOT NULL DEFAULT '0.00000',
*  `t_method` varchar(10) NOT NULL DEFAULT '' COMMENT 'buy,sell',
*  `t_status` int(11) NOT NULL DEFAULT '0' COMMENT '1=success,0=fail',
*  `t_response` int(11) NOT NULL DEFAULT '0' COMMENT '服务器返回的id或信息',
*  `t_tradetype` varchar(10) NOT NULL DEFAULT '' COMMENT 'btc,ltc',
*  `t_tradeid` varchar(50) NOT NULL DEFAULT '' COMMENT '我们交易的id只能有一个',
*  `t_note` varchar(100) DEFAULT NULL COMMENT '存储异常的内容',
*  `ts` datetime DEFAULT NULL,
*  PRIMARY KEY (`t_id`)
*) ENGINE=InnoDB DEFAULT CHARSET=utf8;
*/
@SuppressWarnings("serial")
public class TradeLogEntity implements Serializable {
	private Integer id;
	private Integer marketid;
	private BigDecimal price;
	private BigDecimal qty;
	private String method;
	private Integer response;
	private Integer status;
	private String tradetype;
	private String tradeid;
	private String note;
	private Date ds;
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
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the response
	 */
	public Integer getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(Integer response) {
		this.response = response;
	}
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
	 * @return the tradetype
	 */
	public String getTradetype() {
		return tradetype;
	}
	/**
	 * @param tradetype the tradetype to set
	 */
	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}
	/**
	 * @return the tradeid
	 */
	public String getTradeid() {
		return tradeid;
	}
	/**
	 * @param tradeid the tradeid to set
	 */
	public void setTradeid(String tradeid) {
		this.tradeid = tradeid;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the ds
	 */
	public Date getDs() {
		return ds;
	}
	/**
	 * @param ds the ds to set
	 */
	public void setDs(Date ds) {
		this.ds = ds;
	}
	
}
