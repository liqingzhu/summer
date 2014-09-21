package com.sx.btccoin.service;

import java.util.List;

import com.sx.btccoin.entity.AccountInfoEntity;
import com.sx.btccoin.entity.BtcCoinTradeDepthRec;
import com.sx.btccoin.entity.BtcCoinTradeMarket;
import com.sx.btccoin.entity.BtcCoinTradeMarketInfo;
import com.sx.btccoin.entity.ByteCoinTradeForListEntity;
import com.sx.btccoin.vo.ByteCoinVo;

public interface IBtcTradeService {
	
	/**
	 *开始交易比特币 
	 **/
	public String tradeBtcCoinTrade(BtcCoinTradeMarket bctm) throws Exception;
	/**
	 * 根据id返回一条交易列表记录信息
	 **/
	public BtcCoinTradeMarket findBtcCoinTradeMarketById(String id) throws Exception;
	
	/**
	 * 根据根据id更新交易信息列表
	 **/
	public Integer updateBtcCoinTradeMarketStatusById(BtcCoinTradeMarket bctm) throws Exception;
	
	/**
	 * 根据状态查询交易信息返回List集合列表
	 **/
	public List<BtcCoinTradeMarket> findBtcCoinTradeMarkets(BtcCoinTradeMarket bctm) throws Exception;
	/**
	 * 获取每次配对的交易队列
	 **/
	public List<BtcCoinTradeDepthRec> findBtcCoinTradeDepthRec(BtcCoinTradeDepthRec aie);
	/**
	 * 循环获取交易深度
	 * 把它按照买和卖分别存储
	 **/
	public boolean addDepthRec(String tradeid,ByteCoinTradeForListEntity bctfl);
	/**
	 * param ByteCoinVo 根据给定的VO信息进行循环 返回一个对象此对象里面有一个买的数组和一个卖的数组
	 */
	@SuppressWarnings("unchecked")
	public ByteCoinTradeForListEntity getByteCoinTradeEntity(ByteCoinVo bcv,String tradeid);
	public BtcCoinTradeMarketInfo makeBtcCoinTradeMarketInfo(ByteCoinTradeForListEntity bctfl,String tradeid)  throws Exception;
	public List<AccountInfoEntity> findAllAccount(AccountInfoEntity aie);
}
