package com.sx.btccoin.util.sort;

import java.math.BigDecimal;
import java.util.Comparator;


@SuppressWarnings("unchecked")
public class CompareFireCoinBuyerSort2   implements Comparator{
	/**
	 *委托买比较排序 
	 *从大到小排序
	 **/
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		int count = 0;
		BigDecimal[] bcte1 = (BigDecimal[]) o1;
		BigDecimal[] bcte2 = (BigDecimal[]) o2;
		if(bcte1[0].doubleValue()>=bcte2[0].doubleValue()){
			count = 0;
		}else{
			count = 1;
		}
		return count;
	}

}
