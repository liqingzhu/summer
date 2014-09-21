package com.sx.btccoin.btcchina.entity;
import java.io.Serializable;
@SuppressWarnings("serial")
/**
 * {"result":{
    "profile":{
        "username":"btc",
        "trade_password_enabled":true,
        "otp_enabled":true,
        "trade_fee":0.003,
        "daily_btc_limit":10,
        "btc_deposit_address":"123myZyM9jBYGw5EB3wWmfgJ4Mvqnu7gEu",
        "btc_withdrawal_address":"123GzXJnfugniyy7ZDw3hSjkm4tHPHzHba"
    },
    "balance":{
 "btc":{
            "currency":"BTC",
            "symbol":"\u0e3f",
            "amount":9999.4997,
            "amount_integer":"999949970000",
            "amount_decimal":8
        },
 "cny":{
            "currency":"CNY",
            "symbol":"\u00a5",
            "amount":998999.99339,
            "amount_integer":"99899999339",
            "amount_decimal":5
        }
    },
    "frozen":{
 "btc":{
            "currency":"BTC",
            "symbol":"\u0e3f",
            "amount":0,
            "amount_integer":"",
            "amount_decimal":8
        },
 "cny":{
            "currency":"CNY",
            "symbol":"\u00a5",
            "amount":0,
            "amount_integer":"",
            "amount_decimal":5
        }
    },
}
 * */
public class BtcChinaAccountInfoFrozen implements Serializable{
	private BtcChinaAccountInfoUnit btc;
	private BtcChinaAccountInfoUnit cny;
	public BtcChinaAccountInfoUnit getBtc() {
		return btc;
	}
	public void setBtc(BtcChinaAccountInfoUnit btc) {
		this.btc = btc;
	}
	public BtcChinaAccountInfoUnit getCny() {
		return cny;
	}
	public void setCny(BtcChinaAccountInfoUnit cny) {
		this.cny = cny;
	}
	
}
