/**
 * 
 */
package com.sx.rbac.entity;

import java.io.Serializable;
import java.util.List;

import com.sx.core.entity.PageEntity;
import com.sx.rbac.entity.CityInfoEntity;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class SysUserEntity extends PageEntity implements Serializable {
	private Integer uid;
	private String userid;
	private String pwd;
	private String imgcode;
	private String cid;
	
	private String urroleid;
	private String ugroupid;
	private String username;
	private String usersex;
	private String useremail;
	private String useradddate;
	private String usereditdate;
	private String userstartdate;
	private String userenddate;
	private String usernote;
	private Integer available; //0=可用 1=不可用
  	
	private Integer roleid;//权限编号
	private List<CityInfoEntity> citys;
	private List<RolesAndUserGroupsEntity> rolesandgroups;
	private Integer cityid;
	
	
	
	
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
	/**
	 * 权限编号
	 * @return the roleid
	 */
	public Integer getRoleid() {
		return roleid;
	}
	/**
	 * 权限编号
	 * @param roleid the roleid to set
	 */
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	/**
	 * @return the cid
	 */
	public String getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}
	/**
	 * @return the citys
	 */
	public List<CityInfoEntity> getCitys() {
		return citys;
	}
	/**
	 * @param citys the citys to set
	 */
	public void setCitys(List<CityInfoEntity> citys) {
		this.citys = citys;
	}
	/**
	 * @return the imgcode
	 */
	public String getImgcode() {
		return imgcode;
	}
	/**
	 * @param imgcode the imgcode to set
	 */
	public void setImgcode(String imgcode) {
		this.imgcode = imgcode;
	}
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
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return the urroleid
	 */
	public String getUrroleid() {
		return urroleid;
	}
	/**
	 * @param urroleid the urroleid to set
	 */
	public void setUrroleid(String urroleid) {
		this.urroleid = urroleid;
	}
	/**
	 * @return the ugroupid
	 */
	public String getUgroupid() {
		return ugroupid;
	}
	/**
	 * @param ugroupid the ugroupid to set
	 */
	public void setUgroupid(String ugroupid) {
		this.ugroupid = ugroupid;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the usersex
	 */
	public String getUsersex() {
		return usersex;
	}
	/**
	 * @param usersex the usersex to set
	 */
	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}
	/**
	 * @return the useremail
	 */
	public String getUseremail() {
		return useremail;
	}
	/**
	 * @param useremail the useremail to set
	 */
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	/**
	 * @return the useradddate
	 */
	public String getUseradddate() {
		return useradddate;
	}
	/**
	 * @param useradddate the useradddate to set
	 */
	public void setUseradddate(String useradddate) {
		this.useradddate = useradddate;
	}
	/**
	 * @return the usereditdate
	 */
	public String getUsereditdate() {
		return usereditdate;
	}
	/**
	 * @param usereditdate the usereditdate to set
	 */
	public void setUsereditdate(String usereditdate) {
		this.usereditdate = usereditdate;
	}
	/**
	 * @return the userstartdate
	 */
	public String getUserstartdate() {
		return userstartdate;
	}
	/**
	 * @param userstartdate the userstartdate to set
	 */
	public void setUserstartdate(String userstartdate) {
		this.userstartdate = userstartdate;
	}
	/**
	 * @return the userenddate
	 */
	public String getUserenddate() {
		return userenddate;
	}
	/**
	 * @param userenddate the userenddate to set
	 */
	public void setUserenddate(String userenddate) {
		this.userenddate = userenddate;
	}
	/**
	 * @return the usernote
	 */
	public String getUsernote() {
		return usernote;
	}
	/**
	 * @param usernote the usernote to set
	 */
	public void setUsernote(String usernote) {
		this.usernote = usernote;
	}
	
	/**
	 * @return the available
	 */
	public Integer getAvailable() {
		return available;
	}
	/**
	 * @param available the available to set
	 */
	public void setAvailable(Integer available) {
		this.available = available;
	}
	/**
	 * @return the rolesandgroups
	 */
	public List<RolesAndUserGroupsEntity> getRolesandgroups() {
		return rolesandgroups;
	}
	/**
	 * @param rolesandgroups the rolesandgroups to set
	 */
	public void setRolesandgroups(List<RolesAndUserGroupsEntity> rolesandgroups) {
		this.rolesandgroups = rolesandgroups;
	}
	
}
