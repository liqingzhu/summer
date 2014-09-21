/**
 * 
 */
package com.sx.rbac.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sx.util.exception.SXException;
import com.sx.rbac.biz.IMenuBiz;
import com.sx.rbac.dao.IMenuDao;
import com.sx.rbac.entity.MenuEntity;

/**
 * @author Administrator
 *
 */
@Service(value="menubiz")
public class MenuBiz implements IMenuBiz {
	@Autowired
	private IMenuDao imenudao;
	private Log log = LogFactory.getLog(this.getClass());
	/* (non-Javadoc)
	 * @see com.sx.rbac.biz.IMenuBiz#findFirstMenu(com.sx.rbac.entity.MenuEntity)
	 */
	public List<MenuEntity> findFirstMenu(MenuEntity menu) throws SXException{
		// TODO Auto-generated method stub
		List<MenuEntity> list = new ArrayList<MenuEntity>();
		try {
			list = imenudao.findFirstMenu(menu);
		} catch (Exception e) {
			log.error("BIZ层 按照权限编号IDS查询所拥有的FirstMenu菜单信息",e);
			throw new SXException(e);
		}
		return list;
	}
	/**
	 * @return the imenudao
	 */
	public IMenuDao getImenudao() {
		return imenudao;
	}
	/**
	 * @param imenudao the imenudao to set
	 */
	public void setImenudao(IMenuDao imenudao) {
		this.imenudao = imenudao;
	}
	
}
