/**
 * 
 */
package com.sx.rbac.biz.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sx.util.exception.SXException;
import com.sx.rbac.biz.ISysUserBiz;
import com.sx.rbac.dao.ISysUserDao;
import com.sx.rbac.entity.RolesAndUserGroupsEntity;
import com.sx.rbac.entity.SysUserAndCityInfoEntity;
import com.sx.rbac.entity.SysUserEntity;

/**
 * @author Administrator
 *
 */
@Service(value="sysuserbiz")
public class SysUserBiz implements ISysUserBiz {
	@Autowired
	private ISysUserDao sysuserdao;
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/* (non-Javadoc)
	 * @see com.sx.rbac.biz.ISysUserBiz#addUser(com.sx.rbac.entity.SysUserEntity)
	 */
	public Integer addUser(SysUserEntity sysuser) throws SXException {
		// TODO Auto-generated method stub
		Integer id = null;
		try {
			//1.添加用户
			Integer count = sysuserdao.addUser(sysuser);
			if(count>0){
				id = sysuser.getUid();
			}
			//2.添加用户权限
			if(id!=null){
				RolesAndUserGroupsEntity ru = new RolesAndUserGroupsEntity();
				ru.setUid(id);
				ru.setUserroleid(sysuser.getRoleid());
				sysuserdao.addUserRole(ru);
			}
			//3.若城市编号不为空添加用户与城市关联信息
			if(id!=null&&sysuser.getCityid()!=null){
				SysUserAndCityInfoEntity sc = new SysUserAndCityInfoEntity();
				sc.setCityid(sysuser.getCityid());
				sc.setUid(id);
			}
		} catch (Exception e) {
			log.error("添加用户",e);
			throw new SXException("添加用户",e);
		}
		return id;
	}
	/* (non-Javadoc)
	 * @see com.sx.rbac.biz.ISysUserBiz#editUser(com.sx.rbac.entity.SysUserEntity)
	 */
	public boolean editUser(SysUserEntity sysuser) throws SXException {
		boolean flag = false;
		try {
			Integer count = sysuserdao.editUser(sysuser);
			if (count>0) {
				flag = true;
			}
		} catch (Exception e) {
			log.error("编辑用户",e);
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.sx.rbac.biz.ISysUserBiz#findSysUserOnlyOne(com.sx.rbac.entity.SysUserEntity)
	 */
	public SysUserEntity findSysUserOnlyOne(SysUserEntity sysuser) throws SXException{
		// TODO Auto-generated method stub
		SysUserEntity user = null;
		try {
			user =  sysuserdao.findSysUserOnlyOne(sysuser);
		} catch (Exception e) {
			log.error("获取用户信息",e);
			throw new SXException(e);
		}
		return user;
	}
	/**
	 * @return the sysuserdao
	 */
	public ISysUserDao getSysuserdao() {
		return sysuserdao;
	}
	/**
	 * @param sysuserdao the sysuserdao to set
	 */
	public void setSysuserdao(ISysUserDao sysuserdao) {
		this.sysuserdao = sysuserdao;
	}
}
