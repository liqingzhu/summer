package com.sx.btccoin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.sx.btccoin.entity.AccountInfoEntity;
import com.sx.btccoin.firecoin.entity.FireCoinAccountInfo;
import com.sx.btccoin.firecoin.entity.FireCoinDepth;
import com.sx.btccoin.util.balance.FireCoinBalanceUtil;
import com.sx.btccoin.util.trade.FireCoinTradeUtil;

public class FireCoinUtil {
	public static  String ACCESS_KEY = "65a72c8b-cc83a348-3d7c3c23-b9168";
	public static  String SECRET_KEY = "867f12c5-a208cf86-670a93e3-c461f";
	public static  String DEPTH_PATH = "http://market.huobi.com/staticmarket/depth_btc_json.js";
	private static Log log = LogFactory.getLog(FireCoinUtil.class);
	/**
	 * 下卖单，卖出比特币
	 **/
	public static HashMap<String,Object> tradeSellBtcCoin(BigDecimal price,BigDecimal qty) throws Exception{
		HashMap<String,Object> map = null;
		try {
			map = FireCoinTradeUtil.makeSellOrderBtc(price.doubleValue()+"", qty.doubleValue()+"");
		} catch (Exception e) {
			log.error("FireCoin下卖单，卖出比特币", e);
			throw new Exception("FireCoin下卖单，卖出比特币", e);
		}
		return map;
	}
	/**
	 * 下买单，买入比特币
	 **/
	public static HashMap<String,Object> tradeBuyBtcCoin(BigDecimal price,BigDecimal qty) throws Exception{
		HashMap<String,Object> map = null;
		try {
			map =  FireCoinTradeUtil.makeBuyOrderBtc(price.doubleValue()+"", qty.doubleValue()+"");
		} catch (Exception e) {
			log.error("FireCoin下买单，买入比特币", e);
			throw new Exception("FireCoin下买单，买入比特币", e);
		}
		return map;
	}
	/**
	 *获取火币网第二个接口交易深度 
	 **/
	public static FireCoinDepth getFireCoinDepthInfo(){
		FireCoinDepth fcd = new FireCoinDepth();
		try {
			String str = FireCoinUtil.getMarketDepthString();
			fcd = JSONObject.parseObject(str, FireCoinDepth.class);
		} catch (Exception e) {
			log.error("获取交易深度失败,请重试!",e);
		}
		return fcd;
	}
	/**
	 *火币网获取交易深度 
	 *返回JSON格式的String数据
	 **/
	public static String getMarketDepthString(){
		String result = "";
		try {
			result = getMarketDepth(FireCoinUtil.DEPTH_PATH, null, "UTF8", true);
		} catch (Exception e) {
			log.error("获取信息火币网的深度",e);
		}
		return result;
	}
	/**
	 * 获取账面余额
	 **/
	public static AccountInfoEntity getFireCoinBalance(String accesskey,String secretkey){
		AccountInfoEntity aie = new AccountInfoEntity();
		try {
			FireCoinUtil.ACCESS_KEY = accesskey;
			FireCoinUtil.SECRET_KEY = secretkey;
			String jsonstr = FireCoinBalanceUtil.getFireCoinAccountInfo();
//			{"total":"14070.20","net_asset":"8576.20","available_cny_display":"0.25","available_btc_display":"1.1205","frozen_cny_display":"0.00","frozen_btc_display":"2.5179","loan_cny_display":"5494.00","loan_btc_display":"0.0000"}
			FireCoinAccountInfo btc = JSONObject.parseObject(jsonstr,FireCoinAccountInfo.class);
			aie.setBalance(btc.getAvailable_cny_display());
			aie.setBytecoinqty(btc.getAvailable_btc_display());
			aie.setFrozenbal(btc.getFrozen_cny_display());
			aie.setFrozenqty(btc.getFrozen_btc_display());
			aie.setLeftbal(btc.getAvailable_cny_display());
			aie.setLeftqty(btc.getAvailable_btc_display());
			aie.setMarketid("5");
		} catch (Exception e) {
			log.error("获取账面余额",e);
		}
		return aie;
	}
	/**
	 * 获取http协议数据的核心算法
	 **/
	public static String getMarketDepth(String url, String queryString,
			String charset, boolean pretty) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();

		HttpMethod method = new GetMethod(url);
		try {
			// 代理上网时设置这个
			// client.getHostConfiguration().setProxy("10.128.7.50", 3128);
			if (StringUtils.isNotBlank(queryString))
				// 对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串
				method.setQueryString(URIUtil.encodeQuery(queryString));
			/*client.setTimeout(9000);
			client.setConnectionTimeout(9000);*/
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);  
			client.getHttpConnectionManager().getParams().setSoTimeout(10000);
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(method.getResponseBodyAsStream(),
								charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty)
						response.append(line).append(System.getProperty("line.separator"));
					else
						response.append(line);
				}
				reader.close();
			}
		} catch (URIException e) {
			 log.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
			
		} catch (IOException e) {
			 log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}
}
