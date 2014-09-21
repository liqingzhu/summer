package com.sx.core.services.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sx.core.services.ISendMessage;
import com.sx.util.exception.SXException;
import com.sx.util.message.MessageEntity;
import com.sx.util.message.email.MailUtil;


public class SendMessage  implements ISendMessage {
	private MailUtil mailutil;
	private Log log = LogFactory.getLog(this.getClass());
	public boolean sendMessage(MessageEntity message) throws SXException{
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			/*mailutil.setToEmail("291310887@qq.com");
			Map<String, String> data = new HashMap<String, String>();  
			data.put("username", "村长");  
			data.put("username", "村长");  */
			mailutil.setTemplateLocation(message.getTemplatelocation());  
			mailutil.setModel(message.getData());  
			mailutil.setToEmail(message.getReceiver());
			mailutil.setTitle(message.getTitle());
			flag = mailutil.send();
		} catch (Exception e) {
			log.error("邮件发送",e);
			throw new SXException();
		}
		return flag;
	}
	/**
	 * @return the mailutil
	 */
	public MailUtil getMailutil() {
		return mailutil;
	}
	/**
	 * @param mailutil the mailutil to set
	 */
	public void setMailutil(MailUtil mailutil) {
		this.mailutil = mailutil;
	}
	
}
