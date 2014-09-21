package com.sx.timer;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sx.util.DateUtil;


public class AutoRemainder extends TimerTask{
	private Log log = LogFactory.getLog(this.getClass());
	@Autowired
//	private IReminderBiz ireminderbiz;
	@Override
	public void run() {
		try{
			/*EmailReminderEntity emailreminder = new EmailReminderEntity();
			emailreminder.setStatus(new Integer(0));
			this.ireminderbiz.senderReminderInfo(emailreminder);*/
			log.info("每1分钟执行一次 执行时间为"+DateUtil.getToday());
//			System.out.println("每1分钟执行一次 执行时间为");
		}catch(Exception ex){
			log.error("自动发送邮件失败请查明原因",ex);
		}
	}

}
