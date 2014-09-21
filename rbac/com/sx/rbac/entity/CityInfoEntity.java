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
public class CityInfoEntity extends PageEntity implements Serializable {
	/**
	 *城市ID 
	 */
	private Integer cid;
	/**
	 *名称
	 */
	private String cname;
	/**
	 * 父类ID编号
	 **/
	private Integer cpid;
	/**
	 * 地址
	 **/
	private String caddress;
	/**
	 * 电话
	 **/
	private String ctelephone;
	/**
	 * 手机
	 **/
	private String cmobile;
	/**
	 * 传真
	 **/
	private String cfax;
	/**
	 * 电子邮件
	 **/
	private String cemail;
	/**
	 * 备注
	 **/
	private String cnote;
	/**
	 * 类型
	 **/
	private Integer ctype;
	/**
	 * 是否为叶子节点
	 **/
	private Boolean leaf;
	/**
	 * 图片
	 **/
	private String iconCls;
	private String clevel;
	
	/**
	 * @return the clevel
	 */
	public String getClevel() {
		return clevel;
	}

	/**
	 * @param clevel the clevel to set
	 */
	public void setClevel(String clevel) {
		this.clevel = clevel;
	}

	/**
	 * 经销商信息类
	 */
	public CityInfoEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cid
	 */
	public Integer getCid() {
		return cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/**
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	/**
	 * @return the cpid
	 */
	public Integer getCpid() {
		return cpid;
	}

	/**
	 * @param cpid the cpid to set
	 */
	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}

	/**
	 * @return the caddress
	 */
	public String getCaddress() {
		return caddress;
	}

	/**
	 * @param caddress the caddress to set
	 */
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	/**
	 * @return the ctelephone
	 */
	public String getCtelephone() {
		return ctelephone;
	}

	/**
	 * @param ctelephone the ctelephone to set
	 */
	public void setCtelephone(String ctelephone) {
		this.ctelephone = ctelephone;
	}

	/**
	 * @return the cmobile
	 */
	public String getCmobile() {
		return cmobile;
	}

	/**
	 * @param cmobile the cmobile to set
	 */
	public void setCmobile(String cmobile) {
		this.cmobile = cmobile;
	}

	/**
	 * @return the cfax
	 */
	public String getCfax() {
		return cfax;
	}

	/**
	 * @param cfax the cfax to set
	 */
	public void setCfax(String cfax) {
		this.cfax = cfax;
	}

	/**
	 * @return the cemail
	 */
	public String getCemail() {
		return cemail;
	}

	/**
	 * @param cemail the cemail to set
	 */
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	/**
	 * @return the cnote
	 */
	public String getCnote() {
		return cnote;
	}

	/**
	 * @param cnote the cnote to set
	 */
	public void setCnote(String cnote) {
		this.cnote = cnote;
	}

	/**
	 * @return the ctype
	 */
	public Integer getCtype() {
		return ctype;
	}

	/**
	 * @param ctype the ctype to set
	 */
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	/**
	 * @return the leaf
	 */
	public Boolean getLeaf() {
		if (this.getCtype()!=null&&getCtype().equals(new Integer(1))) {
			leaf = true;
		}
		return leaf;
	}

	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * @param iconCls the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
}
