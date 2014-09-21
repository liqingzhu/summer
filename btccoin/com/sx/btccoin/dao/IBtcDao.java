package com.sx.btccoin.dao;

import java.util.List;

import com.sx.btccoin.entity.AccountInfoEntity;
import com.sx.btccoin.entity.BtcCoinTradeDepthRec;
import com.sx.btccoin.entity.BtcCoinTradeMarket;
import com.sx.btccoin.entity.TradeLogEntity;


public interface IBtcDao {
	public Integer addBtcCoinTradeMarket(BtcCoinTradeMarket bctm);
	public List<AccountInfoEntity> findAllAccount(AccountInfoEntity aie);
	public Integer addBtcCoinTradeDepthRec(BtcCoinTradeDepthRec bctd);
	public List<BtcCoinTradeDepthRec> findBtcCoinTradeDepthRec(BtcCoinTradeDepthRec aie);
	public List<BtcCoinTradeMarket> findBtcCoinTradeMarket(BtcCoinTradeMarket bctm);
	public Integer updateBtcCoinTradeMarket(BtcCoinTradeMarket bctm);
	public BtcCoinTradeMarket findBtcCoinTradeMarketById(BtcCoinTradeMarket bctm);
	public Integer addBtcTradeLog(TradeLogEntity tle);
	public List<TradeLogEntity> findTradeLogEntity(TradeLogEntity tle);
}
