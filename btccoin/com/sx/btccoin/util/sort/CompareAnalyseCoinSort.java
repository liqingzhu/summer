package com.sx.btccoin.util.sort;

import java.util.Comparator;

import com.sx.btccoin.entity.AnalyseCoinEntity;
import com.sx.btccoin.entity.ByteCoinTradeEntity;


@SuppressWarnings("unchecked")
public class CompareAnalyseCoinSort   implements Comparator{
	/**
	 *
	 **/
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		int count = 0;
		AnalyseCoinEntity bcte1 = (AnalyseCoinEntity) o1;
		AnalyseCoinEntity bcte2 = (AnalyseCoinEntity) o2;
//		if(bcte1.getPrice().doubleValue()<bcte2.getPrice().doubleValue()){
		if((bcte1!=null&&bcte2!=null)&&bcte1.getDate().longValue()>=bcte2.getDate().longValue()){
			count = 1;
		}else{
			count = 0;
		}
		return count;
	}

}
