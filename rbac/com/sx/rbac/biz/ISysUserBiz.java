/**
 * 
 */
package com.sx.rbac.biz;

import com.sx.util.exception.SXException;
import com.sx.rbac.entity.SysUserEntity;

/**
 * @author Administrator
 *
 */
public interface ISysUserBiz {
	public boolean editUser(SysUserEntity sysuser) throws SXException;
	public Integer addUser(SysUserEntity sysuser) throws SXException;
	public SysUserEntity findSysUserOnlyOne(SysUserEntity sysuser) throws SXException;
}
