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
public class RolesAndUserGroupsEntity extends com.sx.core.entity.PageEntity implements Serializable {
	/*
	 * users.uid,
u.r_name as userrolename,
u.r_id  as userroleid,
roles.r_name as usergrouprolename,
roles.r_id as usergrouproleid,
groups.ug_name as usergroupname,
groups.ug_id as usergroupid 
	 * 
	 */
	private Integer uid;
	private Integer usergroupid;
	private Integer userroleid;
	private Integer usergrouproleid;
	private String usergroupname;
	private String userrolename;
	private String usergrouprolename;
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
	 * @return the usergroupid
	 */
	public Integer getUsergroupid() {
		return usergroupid;
	}
	/**
	 * @param usergroupid the usergroupid to set
	 */
	public void setUsergroupid(Integer usergroupid) {
		this.usergroupid = usergroupid;
	}
	/**
	 * @return the userroleid
	 */
	public Integer getUserroleid() {
		return userroleid;
	}
	/**
	 * @param userroleid the userroleid to set
	 */
	public void setUserroleid(Integer userroleid) {
		this.userroleid = userroleid;
	}
	/**
	 * @return the usergrouproleid
	 */
	public Integer getUsergrouproleid() {
		return usergrouproleid;
	}
	/**
	 * @param usergrouproleid the usergrouproleid to set
	 */
	public void setUsergrouproleid(Integer usergrouproleid) {
		this.usergrouproleid = usergrouproleid;
	}
	/**
	 * @return the usergroupname
	 */
	public String getUsergroupname() {
		return usergroupname;
	}
	/**
	 * @param usergroupname the usergroupname to set
	 */
	public void setUsergroupname(String usergroupname) {
		this.usergroupname = usergroupname;
	}
	/**
	 * @return the userrolename
	 */
	public String getUserrolename() {
		return userrolename;
	}
	/**
	 * @param userrolename the userrolename to set
	 */
	public void setUserrolename(String userrolename) {
		this.userrolename = userrolename;
	}
	/**
	 * @return the usergrouprolename
	 */
	public String getUsergrouprolename() {
		return usergrouprolename;
	}
	/**
	 * @param usergrouprolename the usergrouprolename to set
	 */
	public void setUsergrouprolename(String usergrouprolename) {
		this.usergrouprolename = usergrouprolename;
	}
	
}
