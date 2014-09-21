package com.sx.core.services;

import com.sx.util.exception.SXException;
import com.sx.util.message.MessageEntity;


/**
 *发送消息 
 **/
public interface ISendMessage {
	public boolean sendMessage(MessageEntity menssage) throws SXException;
}
