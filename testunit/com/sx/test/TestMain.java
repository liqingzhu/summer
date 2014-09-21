package com.sx.test;

import com.alibaba.fastjson.JSONObject;
import com.sx.btccoin.okcoin.entity.OKCoinMarketDepthEntity;

public class TestMain {
	public static void main(String[] args) {
		String str =  "{\"asks\":[[7310,0.01]],\"bids\":[[7124 ,8.212],[7120,8.633]]}";
		System.out.println("====>"+str); 
		OKCoinMarketDepthEntity mde = JSONObject.parseObject(str,OKCoinMarketDepthEntity.class);
	}
}
