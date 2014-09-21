package com.sx.btccoin.util;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.sx.btccoin.btcchina.entity.BtcChinaAccountInfo;
import com.sx.btccoin.btcchina.entity.BtcChinaMarketDepthEntity;
import com.sx.btccoin.entity.AccountInfoEntity;
import com.sx.btccoin.util.balance.BtcChinaBalanceUtil;
import com.sx.btccoin.util.trade.BtcChinaTradeUtil;

public class BtcChinaUtil {
	public static  String ACCESS_KEY = "";
	public static  String SECRET_KEY = "";
	public static BigDecimal BTCCHINA_CHARGE =  new BigDecimal(0.0);
	private static Log log = LogFactory.getLog(BtcChinaUtil.class);
	private static String removeTailZero(BigDecimal b) {
		 String s = b.toString();
		 int i, len = s.length();
		 for (i = 0; i < len; i++){
			  if (s.charAt(len - 1 - i) != '0'){
				  break;
			  }
		 }
		 if (s.charAt(len - i - 1) == '.'){
			 return s.substring(0, len - i - 1);
		 }
		 return s.substring(0, len - i);
	}
	/**
	 * 下卖单，卖出比特币
	 **/
	public static HashMap<String,Object> tradeSellBtcCoin(BigDecimal price,BigDecimal qty) throws Exception{
		HashMap<String,Object> map = null;
		try {
			map = BtcChinaTradeUtil.makeSellOrderBtc(removeTailZero(price),removeTailZero(qty));
		} catch (Exception e) {
			log.error("BtcChina下买单，卖出比特币", e);
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
			map = BtcChinaTradeUtil.makeBuyOrderBtc(removeTailZero(price),removeTailZero(qty));
		} catch (Exception e) {
			log.error("BtcChina下买单，买入比特币", e);
			throw new Exception("BtcChina下买单，买入比特币", e);
		}
		return map;
	}
	/**
	 * 获取一个委买的数据深度，对于委买数据我们进行卖出操作
	 **/
	public static  BtcChinaMarketDepthEntity getBtcChinaMarketDepthEntityInfo() throws Exception{
		BtcChinaMarketDepthEntity bcde = new BtcChinaMarketDepthEntity();
		try {
			String str = BtcChinaBalanceUtil.execCoreMethod("getMarketDepth2");
			bcde = JSONObject.parseObject(str,BtcChinaMarketDepthEntity.class);
		} catch (Exception e) {
			log.error("获取BtcChina交易深度", e);
			throw new Exception("获取BtcChina交易深度", e);
		}
		return bcde;
	}
	/**
	 * 获取账面余额
	 **/
	public static AccountInfoEntity getBtcChinaBalance(String accesskey,String secretkey){
		AccountInfoEntity aie = new AccountInfoEntity();
		try {
			BtcChinaUtil.ACCESS_KEY = accesskey;
			BtcChinaUtil.SECRET_KEY = secretkey;
//			log.error("BtcChinaUtil.SECRET_KEY====>"+BtcChinaUtil.SECRET_KEY);
			String jsonstr = BtcChinaBalanceUtil.getAccoutInfo();
			BtcChinaAccountInfo btc = JSONObject.parseObject(jsonstr,BtcChinaAccountInfo.class);
			aie.setBalance(btc.getResult().getBalance().getCny().getAmount());
			aie.setBytecoinqty(btc.getResult().getBalance().getBtc().getAmount());
			aie.setFrozenbal(btc.getResult().getFrozen().getCny().getAmount());
			aie.setFrozenqty(btc.getResult().getFrozen().getBtc().getAmount());
			aie.setLeftbal(btc.getResult().getBalance().getCny().getAmount());
			aie.setLeftqty(btc.getResult().getBalance().getBtc().getAmount());
			aie.setMarketid("1");
		} catch (Exception e) {
			log.error("获取账面余额",e);
		}
		return aie;
	}
}
