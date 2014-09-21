package com.sx.rbac.entity;

import java.io.Serializable;

import com.sx.core.entity.PageEntity;

@SuppressWarnings("serial")
public class SysUserAndCityInfoEntity extends PageEntity implements Serializable{
	private Integer uid;
	private Integer cityid;
	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * @return the cityid
	 */
	public Integer getCityid() {
		return cityid;
	}
	/**
	 * @param cityid the cityid to set
	 */
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
}
