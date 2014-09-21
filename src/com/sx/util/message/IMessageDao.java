package com.sx.util.message;

import java.util.List;

public interface IMessageDao {
	public List<MessageEntity> findMessageInfo(MessageEntity message) ;
	public boolean updateMessageInfo(MessageEntity message);
}
