package com.sx.core.services.impl;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.sx.core.dao.IReminderDao;
import com.sx.core.services.IReminderBiz;
import com.sx.util.exception.SXException;
import com.sx.util.message.ISendMessage;
import com.sx.util.message.MessageEntity;
import com.sx.util.message.email.EmailReminderEntity;
//@Service(value="emailreminderbiz")
public class EmailReminderBiz implements IReminderBiz {
	@Autowired
	private IReminderDao reminderdao;
	@Autowired
	private ISendMessage sendmessage;
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * 将EmailReminderEntity转换成MessageEntity
	 **/
	public EmailReminderEntity transformFormMessageEntity(MessageEntity message){
		EmailReminderEntity mailreminder = new EmailReminderEntity();
		mailreminder.setTitle(message.getTitle());
		mailreminder.setContent(message.getContent());
		mailreminder.setSendaddress(message.getSender());
		mailreminder.setToaddress(message.getReceiver());
		mailreminder.setFiletemplatepath(message.getTemplatelocation());
		mailreminder.setMapstring(JSONObject.toJSONString(message.getData()));
		mailreminder.setMapmsg((HashMap<String,String>)message.getData());
		return mailreminder;
	}
	
	/**
	 * 将emailreminder的数据信息
	 **/
	public MessageEntity transfanerFromEmailReminderEntity(EmailReminderEntity emailreminder){
		String title = emailreminder.getTitle();
		String content = emailreminder.getContent();
		String sender = emailreminder.getSendaddress();
		String to = emailreminder.getToaddress();
		String templatelocation = emailreminder.getFiletemplatepath();
		String mapstring = emailreminder.getMapstring();
		HashMap<String,String> map = (HashMap<String,String>)JSONObject.parseObject(mapstring, HashMap.class);
		MessageEntity message =  new MessageEntity(title,content,sender,to,templatelocation,map);
		return message;
	}
	/**
	 * 发送单个电子邮件
	 **/
	private void senderSingleEmailInfo(EmailReminderEntity reminder) throws SXException{
		boolean flag = false;
		try {
			//1.更新状态
			reminder.setStatus(new Integer(1));
			flag = this.updateReminderInfo(reminder);
			//2.开始发送邮件
			//2.1 转换邮件信息类
			MessageEntity message = transfanerFromEmailReminderEntity(reminder);
			flag = sendmessage.sendMessage(message);
			if(flag==false){
				reminder.setStatus(new Integer(2));
				flag = this.updateReminderInfo(reminder);
				//如果邮件发送失败
			}
		} catch(SXException ex){
			//如果是EmailSender的异常就在这里捕获然后更新一下状态是2
			reminder.setStatus(new Integer(2));
			flag = this.updateReminderInfo(reminder);
		} catch(Exception e) {
			// TODO: handle exception
			log.error("发送电子邮件数据信息",e);
		}
	}
	/**
	 * 发送电子邮件数据信息
	 **/
	public void senderEmailInfoList(EmailReminderEntity param) throws SXException{
		try {
			//1.查询所有的电子邮件数据信息
			List<Object> list = this.findReminderInfos(param);
			for (Object object : list) {
				EmailReminderEntity reminder = (EmailReminderEntity)object;
				this.senderSingleEmailInfo(reminder);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("发送电子邮件数据信息",e);
			throw new SXException("发送电子邮件数据信息",e);
		}
	}
	
	/***
	 *发送电子邮件提醒信息 
	 ***/
	public boolean senderReminderInfo(Object object) throws SXException {
		boolean flag = false;
		try {
			EmailReminderEntity reminder = (EmailReminderEntity)object;
			this.senderEmailInfoList(reminder);
			flag = true;
		} catch (Exception e) {
			flag = false;
			log.error("发送提醒数据信息!",e);
			throw new SXException("发送提醒数据信息!",e);
		}
		return flag;
	}
	
	public Integer addReminderInfo(Object object) throws SXException{
		Integer id = null;
		try {
			if (object instanceof MessageEntity) {
				object = this.transformFormMessageEntity((MessageEntity)object);
				EmailReminderEntity m = (EmailReminderEntity)object;
				m.setStatus(new Integer(0));
			}
			id = reminderdao.addReminderInfo(object);
		} catch (Exception e) {
			log.error("新增提醒信息",e);
			throw new SXException(e);
		}
		return id;
	}

	public boolean deleteReminderInfo(Object object) throws SXException{
		boolean id = false;
		try {
			Integer count = reminderdao.deleteReminderInfo(object);
			if(count>0){
				id = true;
			}
		} catch (Exception e) {
			log.error("删除提醒信息",e);
			throw new SXException("删除提醒信息",e);
		}
		return id;
	}

	public List<Object> findReminderInfos(Object object) throws SXException{
		List<Object> list= null;
		try {
			list = reminderdao.findReminderInfos(object);
		} catch (Exception e) {
			log.error("获取提醒信息",e);
			throw new SXException(e);
		}
		return list;
	}

	

	public boolean updateReminderInfo(Object object) throws SXException {
		boolean flag = false;
		try {
			Integer count = reminderdao.updateReminderInfo(object);
			if(count>0){
				flag = true;
			}
		} catch (Exception e) {
			log.error("修改提醒信息",e);
			throw new SXException(e);
		}
		return flag;
	}

	/**
	 * @return the reminderdao
	 */
	public IReminderDao getReminderdao() {
		return reminderdao;
	}

	/**
	 * @param reminderdao the reminderdao to set
	 */
	public void setReminderdao(IReminderDao reminderdao) {
		this.reminderdao = reminderdao;
	}
	/**
	 * @return the sendmessage
	 */
	public ISendMessage getSendmessage() {
		return sendmessage;
	}
	/**
	 * @param sendmessage the sendmessage to set
	 */
	public void setSendmessage(ISendMessage sendmessage) {
		this.sendmessage = sendmessage;
	}
	

}