package com.sx.btccoin.entity;
import java.io.Serializable;
import java.util.List;
@SuppressWarnings("serial")
public class ByteCoinTradeForListEntity implements Serializable{
	/**
	 * 0=返回数据成功了
	 * 1=没返回BtcChina的买入交易深度数据
	 * 2=没返回BtcChina的卖出交易深度数据
	 * 3=没返回OKCoin的买入交易深度数据
	 * 4=没返回OKCoin的卖出交易深度数据
	 * 5=没返回火币网的卖出交易深度数据
	 * 6=没返回火币网的买入交易深度数据
	 * 7=什么数据都没返回
	 **/
	private Integer flag;
	private List<ByteCoinTradeEntity> selllist ;
	private List<ByteCoinTradeEntity> buylist ;
	public List<ByteCoinTradeEntity> getSelllist() {
		return selllist;
	}
	public void setSelllist(List<ByteCoinTradeEntity> selllist) {
		this.selllist = selllist;
	}
	public List<ByteCoinTradeEntity> getBuylist() {
		return buylist;
	}
	public void setBuylist(List<ByteCoinTradeEntity> buylist) {
		this.buylist = buylist;
	}
	/**
	 * 0=返回数据成功了
	 * 1=没返回BtcChina的买入交易深度数据
	 * 2=没返回BtcChina的卖出交易深度数据
	 * 3=没返回OKCoin的买入交易深度数据
	 * 4=没返回OKCoin的卖出交易深度数据
	 * 5=没返回火币网的卖出交易深度数据
	 * 6=没返回火币网的买入交易深度数据
	 * 7=什么数据都没返回
	 **/
	public Integer getFlag() {
		return flag;
	}
	/**
	 * 0=返回数据成功了
	 * 1=没返回BtcChina的买入交易深度数据
	 * 2=没返回BtcChina的卖出交易深度数据
	 * 3=没返回OKCoin的买入交易深度数据
	 * 4=没返回OKCoin的卖出交易深度数据
	 * 5=没返回火币网的卖出交易深度数据
	 * 6=没返回火币网的买入交易深度数据
	 * 7=什么数据都没返回
	 **/
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
