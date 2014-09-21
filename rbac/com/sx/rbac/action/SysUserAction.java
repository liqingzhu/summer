/**
 * 
 */
package com.sx.rbac.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import com.sx.core.action.CoreAction;
import com.sx.rbac.biz.ISysUserBiz;
import com.sx.rbac.entity.SysUserEntity;
import com.sx.util.Secret;
import com.sx.util.exception.SXException;

/**
 * @author Administrator
 *
 */
@Namespace(value="/back")  
@ParentPackage(value="coreaction")
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class SysUserAction extends CoreAction implements ModelDriven<SysUserEntity> {
	private SysUserEntity sysuser = new SysUserEntity();
	@Autowired
	private ISysUserBiz userbiz;
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 *修改用户密码 
	 **/
	@Action(value="/back/rbac/changepassword")
	public void changeUserPassword() throws SXException{
		try {
			SysUserEntity user = super.getUserFromSession();
			sysuser.setUid(user.getUid());
			sysuser.setPwd(Secret.getMD5(sysuser.getPwd()));
			userbiz.editUser(sysuser);
			outJsonString("{success:true}");
		} catch (Exception e) {
			outJsonString("{success:false}");
			log.error("修改用户密码 ",e);
			throw new SXException("修改用户密码 ",e);
		}
	}
	/**
	 *按照用户登录名查询用户信息 
	 **/
	@Action(value="/back/rbac/finduserbyuserid")
	public void findUserByUserId() throws SXException{
		try {
			SysUserEntity user = userbiz.findSysUserOnlyOne(sysuser);
			if (user==null) {
				outJsonString("{success:true}");
			}else{
				outJsonString("{success:false}");
			}
		} catch (Exception e) {
			outJsonString("{success:false}");
			log.error("查询用户信息",e);
			throw new SXException(e);
		}
	}
	/**
	 *新增用户信息 
	 **/
	@Action(value="/back/rbac/adduserinfo")
	public void addUserInfo() throws SXException{
		try {
			sysuser.setPwd(Secret.getMD5("123456"));
			Integer id = userbiz.addUser(sysuser);
			outJsonString("{success:true}");
		} catch (Exception e) {
			// TODO: handle exception
			outJsonString("{success:false}");
			log.error("新增用户数据信息",e);
			throw new SXException(e);
		}
	}
	/**
	 * 
	 **/
	public SysUserEntity getModel() {
		// TODO Auto-generated method stub
		return sysuser;
	}
	/**
	 * @return the userbiz
	 */
	public ISysUserBiz getUserbiz() {
		return userbiz;
	}
	/**
	 * @param userbiz the userbiz to set
	 */
	public void setUserbiz(ISysUserBiz userbiz) {
		this.userbiz = userbiz;
	}
	
}
