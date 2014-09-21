/**
 * 
 */
package com.sx.rbac.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sx.rbac.dao.IMenuDao;
import com.sx.rbac.entity.MenuEntity;

/**
 * @author Administrator
 *
 */
public class MenuDao implements IMenuDao {
	private Log log = LogFactory.getLog(this.getClass());
	/* (non-Javadoc)
	 * @see com.sx.rbac.dao.IMenuDao#findFirstMenu(com.sx.rbac.entity.MenuEntity)
	 */
	public List<MenuEntity> findFirstMenu(MenuEntity menu) {
		// TODO Auto-generated method stub
		List<MenuEntity> list = new ArrayList<MenuEntity>();
		try {
//			list =  getSqlMapClientTemplate().queryForList("Menu.findFirstMenu",menu);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("按照权限编号IDS查询所拥有的FirstMenu菜单信息",e);
		}
		return list;
	}

}
