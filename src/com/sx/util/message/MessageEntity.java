package com.sx.util.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.sx.core.entity.PageEntity;


@SuppressWarnings("serial")
public class MessageEntity extends PageEntity implements Serializable {
	private Integer id;
	private String title;
	private String content;
	private String sender;
	private String receiver;
	private String templatelocation;
	private Map<String, String> data = new HashMap<String, String>();
	public MessageEntity(String title, String content, String sender,
			String receiver, String templatelocation, Map<String, String> data) {
		this.title = title;
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
		this.templatelocation = templatelocation;
		this.data = data;
	}
	public MessageEntity() {
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
	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}
	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	/**
	 * @return the templatelocation
	 */
	public String getTemplatelocation() {
		return templatelocation;
	}
	/**
	 * @param templatelocation the templatelocation to set
	 */
	public void setTemplatelocation(String templatelocation) {
		this.templatelocation = templatelocation;
	}
	/**
	 * @return the data
	 */
	public Map<String, String> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Map<String, String> data) {
		this.data = data;
	}  
}
