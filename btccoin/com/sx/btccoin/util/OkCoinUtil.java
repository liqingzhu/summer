package com.sx.btccoin.util;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.alibaba.fastjson.JSONObject;
import com.sx.btccoin.entity.AccountInfoEntity;
import com.sx.btccoin.okcoin.entity.OKCoinMarketAccountEntity;
import com.sx.btccoin.okcoin.entity.OKCoinMarketDepthEntity;
import com.sx.btccoin.util.balance.OKCoinBalanceUtil;
import com.sx.btccoin.util.trade.OKCoinTradeUtil;

public class OkCoinUtil {
	public static  String PARTNER = "";	
	public static  String SECRET_KEY = "";
	private static Log log = LogFactory.getLog(OkCoinUtil.class);
	public static OKCoinMarketDepthEntity getOKCoinDepth(){
		return OKCoinBalanceUtil.getOKCoinMarketDepthEntity();
	}
	/**
	 * 下卖单，卖出比特币
	 **/
	public static HashMap<String,Object> tradeSellBtcCoin(BigDecimal price,BigDecimal qty) throws Exception{
		HashMap<String,Object> map = null;
		try {
			map = OKCoinTradeUtil.makeSellOrderBtc(price.doubleValue()+"", qty.doubleValue()+"");
		} catch (Exception e) {
			log.error("OKCoin下卖单，卖出比特币", e);
			throw new Exception("BtcChina下买单，卖出比特币", e);
		}
		return map;
	}
	/**
	 * 下买单，买入比特币
	 **/
	public static HashMap<String,Object> tradeBuyBtcCoin(BigDecimal price,BigDecimal qty) throws Exception{
		HashMap<String,Object> map = null;
		try {
			map =  OKCoinTradeUtil.makeBuyOrderBtc(price.doubleValue()+"", qty.doubleValue()+"");
		} catch (Exception e) {
			log.error("BtcChina下买单，买入比特币", e);
			throw new Exception("OKCoin下买单，买入比特币", e);
		}
		return map;
	}
	/**
	 * 获取账面余额
	 **/
	public static AccountInfoEntity getOKCoinBalance(String accesskey,String secretkey){
		AccountInfoEntity aie = new AccountInfoEntity();
		try {
			OkCoinUtil.PARTNER = accesskey;
			OkCoinUtil.SECRET_KEY = secretkey;
			String stringstr = OKCoinBalanceUtil.getOKCoinBalanceInfo();
//			log.debug("======>"+stringstr);
			OKCoinMarketAccountEntity btc = JSONObject.parseObject(stringstr, OKCoinMarketAccountEntity.class);
			aie.setBalance(btc.getInfo().getFunds().getFree().getCny());
			aie.setBytecoinqty(btc.getInfo().getFunds().getFree().getBtc());
			aie.setFrozenbal(btc.getInfo().getFunds().getFreezed().getCny());
			aie.setFrozenqty(btc.getInfo().getFunds().getFreezed().getBtc());
			aie.setLeftbal(btc.getInfo().getFunds().getFree().getCny());
			aie.setLeftqty(btc.getInfo().getFunds().getFree().getBtc());
			aie.setMarketid("2");
		} catch (Exception e) {
			log.error("获取账面余额",e);
		}
		return aie;
	}
	
	
}
