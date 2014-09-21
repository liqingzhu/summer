package com.sx.btccoin.util.sort;

import java.util.Comparator;

import com.sx.btccoin.entity.ByteCoinTradeEntity;

@SuppressWarnings("unchecked")
public class CompareSellerSort implements Comparator{
	/**
	 *委托卖比较排序 
	 **/
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		int count = 0;
		ByteCoinTradeEntity bcte1 = (ByteCoinTradeEntity) o1;
		ByteCoinTradeEntity bcte2 = (ByteCoinTradeEntity) o2;
		if((bcte1!=null&&bcte2!=null)&&bcte1.getPrice().doubleValue()<bcte2.getPrice().doubleValue()){
			count = 0;
		}else{
			count = 1;
		}
		return count;
	}
}
