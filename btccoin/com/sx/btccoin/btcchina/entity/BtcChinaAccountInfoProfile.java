package com.sx.btccoin.btcchina.entity;
import java.io.Serializable;
@SuppressWarnings("serial")
/**
 "profile":{
        "username":"btc",
        "trade_password_enabled":true,
        "otp_enabled":true,
        "trade_fee":0.003,
        "daily_btc_limit":10,
        "btc_deposit_address":"123myZyM9jBYGw5EB3wWmfgJ4Mvqnu7gEu",
        "btc_withdrawal_address":"123GzXJnfugniyy7ZDw3hSjkm4tHPHzHba"
    },
 * */
public class BtcChinaAccountInfoProfile implements Serializable{
	private String username;
	private String trade_password_enabled;
	private String otp_enabled;
	private Double trade_fee;
	private Integer daily_btc_limit;
	private String btc_deposit_address;
	private String btc_withdrawal_address;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTrade_password_enabled() {
		return trade_password_enabled;
	}
	public void setTrade_password_enabled(String tradePasswordEnabled) {
		trade_password_enabled = tradePasswordEnabled;
	}
	public String getOtp_enabled() {
		return otp_enabled;
	}
	public void setOtp_enabled(String otpEnabled) {
		otp_enabled = otpEnabled;
	}
	public Double getTrade_fee() {
		return trade_fee;
	}
	public void setTrade_fee(Double tradeFee) {
		trade_fee = tradeFee;
	}
	public Integer getDaily_btc_limit() {
		return daily_btc_limit;
	}
	public void setDaily_btc_limit(Integer dailyBtcLimit) {
		daily_btc_limit = dailyBtcLimit;
	}
	public String getBtc_deposit_address() {
		return btc_deposit_address;
	}
	public void setBtc_deposit_address(String btcDepositAddress) {
		btc_deposit_address = btcDepositAddress;
	}
	public String getBtc_withdrawal_address() {
		return btc_withdrawal_address;
	}
	public void setBtc_withdrawal_address(String btcWithdrawalAddress) {
		btc_withdrawal_address = btcWithdrawalAddress;
	}
	
}
