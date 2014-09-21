package com.sx.core.dao;

import java.util.List;

public interface IReminderDao {
	public Integer addReminderInfo(Object object);
	public Integer updateReminderInfo(Object object);
	public Integer deleteReminderInfo(Object object);
	public List<Object> findReminderInfos(Object object);
}
