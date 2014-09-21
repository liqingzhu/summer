/**
 * 
 */
package com.sx.core.entity;

import java.io.Serializable;

import com.sx.core.entity.PageEntity;


/**
 * @author Administrator
 *	附件数据信息
 */
@SuppressWarnings("serial")
public class AttachmentEntity extends PageEntity implements Serializable {
	 private Integer id;
	 private Integer fid; 
	 private String  fdisplayname;// '文件显示名称',
	 private String fvaluename; // '文件真实物理名称',
	 private String  fvaluepath; //'网页相对路径',
	 private String  fdate; // '上传时间',
	 private String  fuserid; //'用户编号',
	 private String  ftype; //  '上传文件类型后台用户=sys_users 其他=sys_applyers',
	 private String  fshortname; // '图片描述',
	 private String  fnote; //'图片备注',
	 /**
	  *按照类型对附件种类进行查询
	  *1=地块附件信息 
	  **/
	 private Integer type;
	 
	/**
	 * 按照类型对附件种类进行查询
	 * 1=地块附件信息 
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 按照类型对附件种类进行查询
	 * 1=地块附件信息 
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the fid
	 */
	public Integer getFid() {
		return fid;
	}
	/**
	 * @param fid the fid to set
	 */
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	/**
	 * @return the fdisplayname
	 */
	public String getFdisplayname() {
		return fdisplayname;
	}
	/**
	 * @param fdisplayname the fdisplayname to set
	 */
	public void setFdisplayname(String fdisplayname) {
		this.fdisplayname = fdisplayname;
	}
	/**
	 * @return the fvaluename
	 */
	public String getFvaluename() {
		return fvaluename;
	}
	/**
	 * @param fvaluename the fvaluename to set
	 */
	public void setFvaluename(String fvaluename) {
		this.fvaluename = fvaluename;
	}
	/**
	 * @return the fvaluepath
	 */
	public String getFvaluepath() {
		return fvaluepath;
	}
	/**
	 * @param fvaluepath the fvaluepath to set
	 */
	public void setFvaluepath(String fvaluepath) {
		this.fvaluepath = fvaluepath;
	}
	/**
	 * @return the fdate
	 */
	public String getFdate() {
		return fdate;
	}
	/**
	 * @param fdate the fdate to set
	 */
	public void setFdate(String fdate) {
		this.fdate = fdate;
	}
	/**
	 * @return the fuserid
	 */
	public String getFuserid() {
		return fuserid;
	}
	/**
	 * @param fuserid the fuserid to set
	 */
	public void setFuserid(String fuserid) {
		this.fuserid = fuserid;
	}
	/**
	 * @return the ftype
	 */
	public String getFtype() {
		return ftype;
	}
	/**
	 * @param ftype the ftype to set
	 */
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	/**
	 * @return the fshortname
	 */
	public String getFshortname() {
		return fshortname;
	}
	/**
	 * @param fshortname the fshortname to set
	 */
	public void setFshortname(String fshortname) {
		this.fshortname = fshortname;
	}
	/**
	 * @return the fnote
	 */
	public String getFnote() {
		return fnote;
	}
	/**
	 * @param fnote the fnote to set
	 */
	public void setFnote(String fnote) {
		this.fnote = fnote;
	}
	 
}
