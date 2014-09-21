package com.sx.btccoin.okcoin.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * {"info":
 *   {
 *     "funds":{
 *        "free":{"btc":0.01,"cny":620.688,"ltc":0},
 *        "freezed":{"btc":0,"cny":9.91,"ltc":0}
 *       }
 *      },
 *   "result":true}
 **/
public class OKCoinMarketAccountEntity implements Serializable {
	private OKCoinAccountInfo info;
	private Boolean result;
	public OKCoinAccountInfo getInfo() {
		return info;
	}
	public void setInfo(OKCoinAccountInfo info) {
		this.info = info;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
}
