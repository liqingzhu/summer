package com.sx.btccoin.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sx.btccoin.okcoin.entity.OKCoinTrade;
import com.sx.btccoin.util.analyse.OKCoinAnalyseUtil;
import com.sx.core.action.CoreAction;
import com.sx.util.exception.SXException;
@Namespace(value="/back/ltc")
@ParentPackage(value="coreaction")
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class LtcAction extends CoreAction {
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 *设置交易参数信息
	 **/
	@SuppressWarnings("unchecked")
	@Action(value="gettradeinfo")
	public void analysisLtc() throws SXException{
		List<OKCoinTrade> list = null;
		try {
			String str = OKCoinAnalyseUtil.getOKCoinMarketLTCDepthEntity();
			list = (List<OKCoinTrade>)JSON.parseArray(str, OKCoinTrade.class);
			for (OKCoinTrade coin : list) {
				coin.setDate(Long.parseLong((coin.getDate()+"000")));
			}
//			JSONArray.parse(input, features)
		} catch (Exception e) {
			// TODO: handle exception
			log.error("获取交易数据出现错误",e);
			throw new SXException("获取交易数据出现错误!",e);
		}
		outJsonString(JSONObject.toJSONString(list));
	}
}
