package com.sx.core.services;

import java.util.List;

import com.sx.util.exception.SXException;
import com.sx.util.message.email.EmailReminderEntity;

public interface IReminderBiz {
	public void senderEmailInfoList(EmailReminderEntity param)throws SXException;
	public Integer addReminderInfo(Object object) throws SXException;
	public boolean updateReminderInfo(Object object) throws SXException;
	public boolean deleteReminderInfo(Object object) throws SXException;
	public List<Object> findReminderInfos(Object object) throws SXException;
	public boolean senderReminderInfo(Object object)throws SXException;
}
