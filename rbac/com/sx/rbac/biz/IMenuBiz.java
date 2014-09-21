/**
 * 
 */
package com.sx.rbac.biz;

import java.util.List;

import com.sx.util.exception.SXException;
import com.sx.rbac.entity.MenuEntity;

/**
 * @author Administrator
 *
 */
public interface IMenuBiz {
	/**
	 *按照权限编号IDS查询所拥有的FirstMenu菜单信息 
	 **/
	public List<MenuEntity> findFirstMenu(MenuEntity menu) throws SXException;
}
