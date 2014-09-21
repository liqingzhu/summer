package com.sx.btccoin.util.analyse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.sx.btccoin.okcoin.entity.OKCoinMarketDepthEntity;
import com.sx.btccoin.util.balance.OKCoinBalanceUtil;

public class OKCoinAnalyseUtil {
	public static String TRADES_LTC_URL = "https://www.okcoin.cn/api/trades.do?symbol=ltc_cny";
	public static String TRADES_BTC_URL = "https://www.okcoin.cn/api/trades.do";
	private static Log log = LogFactory.getLog(OKCoinAnalyseUtil.class);
	/**
	 * 获取OKCoin的LTC交易深度数据
	 **/
	public static String getOKCoinMarketLTCDepthEntity(){
		String str = "";
		try {
			 str = OKCoinBalanceUtil.getInfos(OKCoinAnalyseUtil.TRADES_LTC_URL, null, "UTF8", true);
			/*System.out.println("=====>"+str);*/
			
		} catch (Exception e) {
			log.error("获取OKCOIN交易深度数据信息!",e);
		}
		return str;
	}
	/**
	 * 获取OKCoin的BTC交易深度数据
	 **/
	public static String getOKCoinMarketBTCDepthEntity(){
		String str = "";
		try {
			 str = OKCoinBalanceUtil.getInfos(OKCoinAnalyseUtil.TRADES_BTC_URL, null, "UTF8", true);
			/*System.out.println("=====>"+str);*/
			
		} catch (Exception e) {
			log.error("获取OKCOIN交易深度数据信息!",e);
		}
		return str;
	}
}
