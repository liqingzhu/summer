package com.sx.util.message.email;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sx.util.exception.SXException;
import com.sx.util.message.ISendMessage;
import com.sx.util.message.email.MailUtil;
import com.sx.util.message.MessageEntity;
@Service
public class SendEmailMessage implements ISendMessage {
	
	/* (non-Javadoc)
	 * @see com.sx.util.message.ISendMessage#getMessageList(com.sx.util.message.MessageEntity)
	 */
	public List<MessageEntity> getMessageList(MessageEntity menssage)
			throws SXException {
		// TODO Auto-generated method stub
		return null;
	}
	@Resource
	private MailUtil mailutil;
	private Log log = LogFactory.getLog(this.getClass());
	public boolean sendMessage(MessageEntity message) throws SXException {
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
			throw new SXException("邮件发送",e);
		}
		return flag;
	}
	/**
	 * @return the mailutil
	 *//*
	public MailUtil getMailutil() {
		return mailutil;
	}
	*//**
	 * @param mailutil the mailutil to set
	 *//*
	public void setMailutil(MailUtil mailutil) {
		this.mailutil = mailutil;
	}*/
}
