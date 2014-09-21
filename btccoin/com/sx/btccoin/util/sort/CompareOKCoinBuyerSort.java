package com.sx.btccoin.util.sort;

import java.util.Comparator;


@SuppressWarnings("unchecked")
public class CompareOKCoinBuyerSort   implements Comparator{
	/**
	 *委托买比较排序
	 *从大到小排序 
	 **/
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		int count = 0;
		Double[] bcte1 = (Double[]) o1;
		Double[] bcte2 = (Double[]) o2;
		if(bcte1[0].doubleValue()>=bcte2[0].doubleValue()){
			count = 0;
		}else{
			count = 1;
		}
		return count;
	}

}
