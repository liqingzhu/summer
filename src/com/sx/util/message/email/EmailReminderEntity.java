package com.sx.util.message.email;

import java.io.Serializable;
import java.util.HashMap;

import com.sx.util.message.MessageEntity;

@SuppressWarnings("serial")
public class EmailReminderEntity extends MessageEntity implements Serializable {
	private Integer id;//编号
	private String filetemplatepath;//文件模版地址
	private Integer status;//0=需要发送 1=已经发送 2=取消
	private String toaddress;//接收人地址
	private String appendtime;//添加时间
	private String sendtime;//发送时间
	private String edittime;//更新时间
	private String sendaddress;//发送人地址
	private String title;//标题信息
	private String content;//内容信息
	private String mapstring;//内容信息参数为JSONObject形式最后要转换成HashMap类型
	private HashMap<String,String> mapmsg;
	
	
	
	
	/**
	 * @return the mapstring
	 */
	public String getMapstring() {
		return mapstring;
	}
	/**
	 * @param mapstring the mapstring to set
	 */
	public void setMapstring(String mapstring) {
		this.mapstring = mapstring;
	}
	/**
	 * @return the mapmsg
	 */
	public HashMap<String, String> getMapmsg() {
		return mapmsg;
	}
	/**
	 * @param mapmsg the mapmsg to set
	 */
	public void setMapmsg(HashMap<String, String> mapmsg) {
		this.mapmsg = mapmsg;
	}
	/**
	 * @return the edittime
	 */
	public String getEdittime() {
		return edittime;
	}
	/**
	 * @param edittime the edittime to set
	 */
	public void setEdittime(String edittime) {
		this.edittime = edittime;
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
	 * @return the filetemplatepath
	 */
	public String getFiletemplatepath() {
		return filetemplatepath;
	}
	/**
	 * @param filetemplatepath the filetemplatepath to set
	 */
	public void setFiletemplatepath(String filetemplatepath) {
		this.filetemplatepath = filetemplatepath;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the toaddress
	 */
	public String getToaddress() {
		return toaddress;
	}
	/**
	 * @param toaddress the toaddress to set
	 */
	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}
	/**
	 * @return the appendtime
	 */
	public String getAppendtime() {
		return appendtime;
	}
	/**
	 * @param appendtime the appendtime to set
	 */
	public void setAppendtime(String appendtime) {
		this.appendtime = appendtime;
	}
	/**
	 * @return the sendtime
	 */
	public String getSendtime() {
		return sendtime;
	}
	/**
	 * @param sendtime the sendtime to set
	 */
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	/**
	 * @return the sendaddress
	 */
	public String getSendaddress() {
		return sendaddress;
	}
	/**
	 * @param sendaddress the sendaddress to set
	 */
	public void setSendaddress(String sendaddress) {
		this.sendaddress = sendaddress;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
}
