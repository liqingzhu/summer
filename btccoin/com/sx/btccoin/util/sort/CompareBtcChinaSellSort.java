package com.sx.btccoin.util.sort;

import java.util.Comparator;

import com.sx.btccoin.btcchina.entity.BtcChinaMarketDEUnit;



/**
 *比特币中国的委卖列表重小到大排序 
 **/
@SuppressWarnings("unchecked")
public class CompareBtcChinaSellSort implements Comparator {

	public int compare(Object o1, Object o2) {
		int count = 0;
		BtcChinaMarketDEUnit bcte1 = (BtcChinaMarketDEUnit) o1;
		BtcChinaMarketDEUnit bcte2 = (BtcChinaMarketDEUnit) o2;
		if(bcte1.getPrice().doubleValue()<bcte2.getPrice().doubleValue()){
			count = 0;
		}else{
			count = 1;
		}
		return count;
	}

}
