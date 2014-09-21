package com.sx.btccoin.okcoin.entity;
import java.io.Serializable;
public class OKCoinAccountInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OKCoinAccount funds;

	public OKCoinAccount getFunds() {
		return funds;
	}

	public void setFunds(OKCoinAccount funds) {
		this.funds = funds;
	}
	
}
