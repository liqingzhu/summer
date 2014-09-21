package com.sx.util.message;

import java.util.List;

import com.sx.util.exception.SXException;


/**
 *发送消息 
 **/
public interface ISendMessage {
	public boolean sendMessage(MessageEntity menssage) throws SXException;
	public List<MessageEntity> getMessageList(MessageEntity menssage) throws SXException;
}
