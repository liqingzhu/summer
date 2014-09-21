/**
 * 
 */
package com.sx.rbac.entity;

import java.io.Serializable;

import com.sx.core.entity.PageEntity;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class MenuEntity extends PageEntity implements Serializable {
	private Integer mid;
	private Integer pid;
	private String mname;
	private String mcode;
	private String type;
	private String params;
	
	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}
	/**
	 * @return the pid
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the mid
	 */
	public Integer getMid() {
		return mid;
	}
	/**
	 * @param mid the mid to set
	 */
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	/**
	 * @return the mname
	 */
	public String getMname() {
		return mname;
	}
	/**
	 * @param mname the mname to set
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}
	/**
	 * @return the mcode
	 */
	public String getMcode() {
		return mcode;
	}
	/**
	 * @param mcode the mcode to set
	 */
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	
}
