package com.sx.core.action;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import nl.captcha.Captcha;
import nl.captcha.Captcha.Builder;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Namespace(value="/")  
@ParentPackage(value="coreaction")
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class UtilAction extends BaseAction {
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 *获取用户注册的验证码信息 
	 **/
	@Action(value="/front/blackimgcode")
	public void getRegisterVaildCodeImage(){
		try {
			//验证码取值范围
			char[] numberChar = new char[] { '2', '3', '4', '5', '6', '7', '8' }; 
			//定义字体样式
			 List<Font> fontList = new ArrayList<Font>();  
//	         fontList.add(new Font("Arial", Font.HANGING_BASELINE, 40));//可以设置斜体之类的  
	         fontList.add(new Font("Courier", Font.BOLD, 55));      
	         //定义颜色
	         List<Color> colorList=new ArrayList<Color>();  
	         colorList.add(Color.BLACK);  
	         //定义字体装载
	         DefaultWordRenderer  cwr= new DefaultWordRenderer (colorList,fontList);
	         Builder builder=new Captcha.Builder(200, 55);
	         //开始创建验证码
	         builder.addText(new DefaultTextProducer(Integer.parseInt("4"),numberChar),cwr);
	         Captcha captcha =  builder .build();  
	         CaptchaServletUtil.writeImage(ServletActionContext.getResponse(), captcha.getImage());
//			ServletActionContext.getResponse().getWriter().close();
//			ServletActionContext.getResponse().getWriter().flush(); 
			getSession().setAttribute("simpleCaptcha", captcha);
		} catch (Exception e) {
			log.error("获取验证码失败!",e);
		}
	}
	/**
	 *获取登录首页的验证码信息 
	 **/
	@Action(value="/front/imgcode")  
	public void getVaildCodeImage(){
		try {
			//验证码取值范围
			char[] numberChar = new char[] { '2', '3', '4', '5', '6', '7', '8' }; 
			//定义字体样式
			 List<Font> fontList = new ArrayList<Font>();  
//	         fontList.add(new Font("Arial", Font.HANGING_BASELINE, 40));//可以设置斜体之类的  
	         fontList.add(new Font("Courier", Font.BOLD, 55));      
	         //定义颜色
	         List<Color> colorList=new ArrayList<Color>();  
	         colorList.add(Color.WHITE);  
	         //定义字体装载
	         DefaultWordRenderer  cwr= new DefaultWordRenderer (colorList,fontList);
	         Builder builder=new Captcha.Builder(200, 55);
	         //开始创建验证码
	         builder.addText(new DefaultTextProducer(Integer.parseInt("5"),numberChar),cwr);
	         Captcha captcha =  builder .build();  
	         CaptchaServletUtil.writeImage(ServletActionContext.getResponse(), captcha.getImage());
//			ServletActionContext.getResponse().getWriter().close();
//			ServletActionContext.getResponse().getWriter().flush(); 
			getSession().setAttribute("simpleCaptcha", captcha);
		} catch (Exception e) {
			log.error("获取验证码失败!",e);
		}
	}
}
