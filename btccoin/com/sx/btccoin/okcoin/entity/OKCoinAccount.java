package com.sx.btccoin.okcoin.entity;
import java.io.Serializable;
@SuppressWarnings("serial")
public class OKCoinAccount implements Serializable{
	private OKCoinSimple free;
	private OKCoinSimple freezed;
	public OKCoinSimple getFree() {
		return free;
	}
	public void setFree(OKCoinSimple free) {
		this.free = free;
	}
	public OKCoinSimple getFreezed() {
		return freezed;
	}
	public void setFreezed(OKCoinSimple freezed) {
		this.freezed = freezed;
	}
}
