/**
 * 
 */
package com.sx.rbac.dao;

import java.util.List;

import com.sx.rbac.entity.MenuEntity;

/**
 * @author Administrator
 *
 */
public interface IMenuDao {
	/**
	 *按照权限编号IDS查询所拥有的FirstMenu菜单信息 
	 **/
	public List<MenuEntity> findFirstMenu(MenuEntity menu);
}
