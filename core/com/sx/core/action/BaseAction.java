package com.sx.core.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 *获取路径系统 
	 * 
	 ***/
	public String getProjectPath(){
		//String path = getRequest().getContextPath();
		String basePath = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+"/";
		return basePath;
	}
	
	
	
	public void outJsonString(String str) {
//		getResponse().setContentType("text/javascript;charset=UTF-8");
		getResponse().setContentType("text/html;charset=UTF-8");	
		outString(str);
	}

	/*
	 * public void outJson(Object obj) {
	 * outJsonString(JSONObject.fromObject(obj).toString()); }
	 * 
	 * public void outJsonArray(Object array) {
	 * outJsonArray(JSONArray.fromObject(array).toString()); }
	 */

	public void outString(String str) {
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			getResponse().setCharacterEncoding("utf-8");
			PrintWriter out = getResponse().getWriter();
			out.write(str);
		} catch (IOException e) {
			log.error("输出信息",e);
		}
	}

	public void outXMLString(String xmlStr) {
		getResponse().setContentType("application/xml;charset=UTF-8");
		outString(xmlStr);
	}

	/**
	 * 获得request
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得response
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获得session
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/**
	 * 获得servlet上文路径
	 * 
	 * @return
	 */
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public String getRealyPath(String path) {
		return getServletContext().getRealPath(path);
	}
}
