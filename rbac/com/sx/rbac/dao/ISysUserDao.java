/**
 * 
 */
package com.sx.rbac.dao;

import com.sx.util.exception.SXException;
import com.sx.rbac.entity.RolesAndUserGroupsEntity;
import com.sx.rbac.entity.SysUserEntity;

/**
 * @author Administrator
 * 系统用户Dao操作类
 */
public interface ISysUserDao {
	/*
	public boolean editUserRole(RolesAndUserGroupsEntity ru) throws SXException;
	public Integer addUser(SysUserEntity sysuser) throws SXException;*/
	public Integer editUser(SysUserEntity sysuser) throws SXException;
	public Integer addUserRole(RolesAndUserGroupsEntity ru) throws SXException;
	public Integer addUser(SysUserEntity sysuser) throws SXException;
	public SysUserEntity findSysUserOnlyOne(SysUserEntity sysuser);
}
