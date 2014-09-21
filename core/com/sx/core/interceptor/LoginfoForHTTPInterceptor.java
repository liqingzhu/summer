package com.sx.core.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
//@Namespace(value="/back/jsp/core")  
//@ParentPackage(value="struts-default")
//@Controller(value="loginfoInteception")
@Component(value="loginfoInteception")  
//@Controller("loginfoInteception")
//@Component
@Scope("prototype")
@SuppressWarnings("serial")
public class LoginfoForHTTPInterceptor extends MethodFilterInterceptor {
	private static String SESSIONKEY="adminSession";//设置session的key 
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		try {
			HttpServletRequest request=ServletActionContext.getRequest(); 
			//请求IP 
			String employeeIp=request.getRemoteHost(); 
			//请求的方法名 
			String actionName=invocation.getProxy().getActionName(); 
			Map map =  invocation.getInvocationContext().getParameters();
			Set set = map.keySet();
			for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				//在上传时候是object的对象 不能这样进行转换
				Object result = ((Object[])map.get(key))[0];
				System.out.println("请求的参数是"+key+"===值是===="+result.toString());
//				System.out.println("请求的参数是"+key);
			}
			//请求的类以及包结构 
			String actionClass=invocation.getProxy().getClass().getName(); 
			java.util.Map<String,Object>session=ActionContext.getContext().getSession(); 
			Object object=session.get(SESSIONKEY); 
			//获取发起请求的员工名 
			if(object==null){//如果在没有登录的情况下  拦截的这个请求是登录请求 所以要从值栈 （Value Stack）中获取数据 
				String loginfoEmployee=(String)invocation.getStack().findValue("employee.employeeNickName"); 
			}else{//登录情况下的操作  直接从session中获得 
//				Employee emp=(Employee)object; 
//				log.setLoginfoEmployee(emp.getEmployeeNickName()); 
			}
			System.out.println("=====本次请求开始=====");
			System.out.println("ip="+employeeIp+"  actionName="+actionName+" actionClass="+actionClass);
			System.out.println("IP="+employeeIp);
			System.out.println("actionName="+actionName);
			System.out.println("Action："+invocation.getAction().getClass().getName());  
			System.out.println("Struts2 中配置的Action："+invocation.getProxy().getActionName());  
			System.out.println("调用的方法："+invocation.getProxy().getMethod());  
			System.out.println("+++++本次请求结束+++++");
			invocation.invoke();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("在拦截信息时出现异常情况", e);
		}
		return ""; 
	}

}
