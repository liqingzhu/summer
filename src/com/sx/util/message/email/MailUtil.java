package com.sx.util.message.email;


import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * 邮件发送器
 */
//测试的时候加上 需要加上@Service

public class MailUtil {

    protected final Log log = LogFactory.getLog(getClass());
    
    private JavaMailSender javaMailSender;
    private VelocityEngine velocityEngine;  
    private String from;
    private String title;
    private String encoding;
    private String templateLocation;
    private String[] toEmails;
    private Map<String,String> model;

    
    public final String regex1 = ".*[<][^>]*[>].*"; 	//判断是 xxxx <xxx>格式文本
    public final String regex2 = "<([^>]*)>";		//尖括号匹配
    /**
     * 获取发件人
     * Spring发送电子邮件发件人中文名乱码问题解决方法
     * @param from
     * @return
     */
    public InternetAddress getFromInternetAddress(String from) {
    	String personal = null;
    	String address = null;

    	if(from.matches(regex1)){
    		personal = from.replaceAll(regex2, "").trim();
    		Matcher m = Pattern.compile(regex2).matcher(from);
    		if(m.find()){
    			address = m.group(1).trim();
    		}
    		try {
    			return new InternetAddress(address, personal, "gb2312");
    		} catch (UnsupportedEncodingException e) {
    			 log.error("发送邮件失败! 邮件标题为: "+title,e);
    		}
    	}else{
    		try {
    			return new InternetAddress(from);
    		} catch (AddressException e) {
    			 log.error("发送邮件失败! 邮件标题为: "+title,e);
    		}
    	}
    	return null;
    }
    
    public boolean send(){
        try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(msg, true, "UTF-8");
			/*System.out.println(from);
			message.setFrom("铁锁互换<qhdtshh@sina.cn> ");*/
            message.setFrom(getFromInternetAddress(from));
            message.setSubject(title);
            message.setTo(toEmails);
			message.setText(getMessage(), true); // 如果发的不是html内容去掉true参数
//			message.addInline("myLogo",new ClassPathResource("img/mylogo.gif"));
//			message.addAttachment("myDocument.pdf", new ClassPathResource("doc/myDocument.pdf"));
			javaMailSender.send(msg);
            
        } catch (MessagingException e) {
            if(log.isWarnEnabled()) {
                log.error("邮件信息导常! 邮件标题为: "+title,e);
            }
            return false;
        } catch (MailException me) {
//        	me.printStackTrace();
            if(log.isWarnEnabled()) {
                log.error("发送邮件失败! 邮件标题为: "+title,me);
            }
            return false;
        }
        return true;    
    }
    
    
    /**
     * 邮件模板中得到信息
     * @return 返回特发送的内容
     */
    private String getMessage() {
        try {
        	return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, encoding, model);
        } catch (VelocityException e) {
			log.error("邮件模板读取失败!邮件标题为: "+title);
		}
		return "";  
		/*return "发了一个测试邮件";*/
    }

    private String[] createToEmail(String to) {
        return new String[] {to};
    }
    
    public void setToEmail(String to) {
        setToEmails(createToEmail(to));
    }
    
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
  /*  public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }*/

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setModel(Map<String,String> model) {
        this.model = model;
    }

    public void setTemplateLocation(String templateLocation) {
        this.templateLocation = templateLocation;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setToEmails(String[] toEmails) {
        this.toEmails = toEmails;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTemplateLocation() {
        return templateLocation;
    }


	/**
	 * @return the velocityEngine
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}


	/**
	 * @param velocityEngine the velocityEngine to set
	 */
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
    
}