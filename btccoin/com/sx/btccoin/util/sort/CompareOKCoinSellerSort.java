package com.sx.btccoin.util.sort;

import java.util.Comparator;


/**
 *OKCOIN委卖的列表 由大到小进行排序 
 **/
@SuppressWarnings("unchecked")
public class CompareOKCoinSellerSort implements Comparator {
	/***
	 * 从小到大排序
	 * 委托卖
	 **/
	public int compare(Object o1, Object o2) {
		int count = 0;
		Double[] bcte1 = (Double[]) o1;
		Double[] bcte2 = (Double[]) o2;
		if(bcte1[0].doubleValue()<bcte2[0].doubleValue()){
			count = 0;
		}else{
			count = 1;
		}
		return count;
	}

}
