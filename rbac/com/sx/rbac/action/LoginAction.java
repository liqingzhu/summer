package com.sx.rbac.action;

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
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import com.sx.core.action.CoreAction;
import com.sx.rbac.biz.ISysUserBiz;
import com.sx.rbac.entity.SysUserEntity;
import com.sx.util.Secret;
@Namespace(value="/back")  
@ParentPackage(value="coreaction")
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
@Results({
	@Result(name="success",location="/index.jsp"),
	@Result(name="input",location="/back/login/default/login.jsp")
})
public class LoginAction extends CoreAction implements ModelDriven<SysUserEntity>{
	private Log log = LogFactory.getLog(this.getClass());
	@Autowired
	private ISysUserBiz sysuserbiz;
	private SysUserEntity sysuser = new SysUserEntity();
	/**
	 * 系统登录后退出
	 **/
	@Action(value="logout")
	public void logout(){
		try {
			getSession().removeAttribute("sysuser");
			getSession().removeAttribute("simpleCaptcha");
			getSession().invalidate();
		} catch (Exception e) {
			log.error("退出系统出现异常",e);
		}
		outJsonString("{success:true}");
	}
	/**
	 * 检查验证码是否正常显示
	 *  
	 **/
	@Action(value="imgcodeverify")
	public void imgcodeVerify(){
		try{
			Captcha captcha =  (Captcha)super.getSession().getAttribute("simpleCaptcha");
			if(captcha.isCorrect(sysuser.getImgcode())){
				outJsonString("{\"success\":true,status:\"0\"}");
			}
		}catch(Exception e){
			log.error("检查验证码是否正确",e);
		}
	}
	/**
	 * 系统主要界面登录
	 **/
	@Action(value="login")
	public void checkUser(){
		try {
			Captcha captcha =  (Captcha)super.getSession().getAttribute("simpleCaptcha");
			if(sysuser.getUserid()==null/*||captcha==null*/){
				//说明从历史登录直接返回到系统的login页面
				outJsonString("{\"success\":false}");
				return;
			}
			if(1==1||captcha.isCorrect(sysuser.getImgcode())){
				//按照登录名获取用户信息
				SysUserEntity user = new SysUserEntity();
				user.setUserid(sysuser.getUserid());
				user = sysuserbiz.findSysUserOnlyOne(user);
				if(user!=null&&sysuser.getUserid().equalsIgnoreCase(user.getUserid())){
					log.error("sysuser1.getPwd()===?"+Secret.getMD5(sysuser.getPwd()));
					log.error("user1.getPwd()==="+user.getPwd());
					if(Secret.getMD5(sysuser.getPwd()).equals(user.getPwd())){
//					if(sysuser.getPwd().equals(user.getPwd())){	
						getSession().setAttribute("sysuser", user);
						String str = JSONObject.toJSONString(user);
						getSession().setAttribute("userinfo", str);
						outJsonString("{\"success\":true,\"status\":\"3\",\"userinfo\":"+str+"}");
					}else{
						//登录密码错误
						outJsonString("{\"success\":false,\"status\":\"2\"}");
					}
				}else{
					//登录名错误
					outJsonString("{\"success\":false,\"status\":\"1\"}");
				}
			}else{
				//验证码错误
				outJsonString("{\"success\":false,\"status\":\"0\"}");
			}
		} catch (Exception e) {
			//登录失败
			outJsonString("{\"success\":false}");
			log.error("登录失败请查明原因",e);
		}
	}
	/**
	 * 系统登录
	 **/
	@Action(value="framework")
	public String Login(){
		String page = "success";
		System.out.println("======login=========");
		return page;
	}
	/**
	 * CMS系统LOGIN代码
	 **/
	@Action(value="cmslogin")  
	public void cmsLogin(){
		try {
			Captcha captcha =  (Captcha)super.getSession().getAttribute("simpleCaptcha");
			if(sysuser.getUserid()==null||captcha==null){
				//说明从历史登录进去
				outJsonString("{success:false}");
				return;
			}
			if(captcha.isCorrect(sysuser.getImgcode())){
				//按照登录名获取用户信息
				SysUserEntity user = new SysUserEntity();
				user.setUserid(sysuser.getUserid());
				user = sysuserbiz.findSysUserOnlyOne(user);
				if(user!=null&&sysuser.getUserid().equalsIgnoreCase(user.getUserid())){
//					if(sysuser.getPwd().equals(user.getPwd())){
					if(Secret.getMD5(sysuser.getPwd()).equals(user.getPwd())){	
						getSession().setAttribute("sysuser", user);
						String str = JSONObject.toJSONString(user);
						getSession().setAttribute("userinfo", str);
						outJsonString("{success:true,msg:\"3\",userinfo:"+str+"}");
					}else{
						//登录密码错误
						outJsonString("{success:false,msg:\"2\"}");
					}
				}else{
					//登录名错误
					outJsonString("{success:false,msg:\"1\"}");
				}
			}else{
				//验证码错误
				outJsonString("{success:false,msg:\"0\"}");
			}
		} catch (Exception e) {
			//登录失败
			outJsonString("{success:false}");
			// TODO: handle exception
			log.error("登录失败请查明原因",e);
		}
	}
	/**
	 *获取用户注册的验证码信息 
	 **/
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
	@Action(value="vaildcode")
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
	         colorList.add(Color.GRAY);  
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
	public SysUserEntity getModel() {
		// TODO Auto-generated method stub
		return sysuser;
	}
	/**
	 * @return the sysuserbiz
	 */
	public ISysUserBiz getSysuserbiz() {
		return sysuserbiz;
	}
	/**
	 * @param sysuserbiz the sysuserbiz to set
	 */
	public void setSysuserbiz(ISysUserBiz sysuserbiz) {
		this.sysuserbiz = sysuserbiz;
	}
	
}

