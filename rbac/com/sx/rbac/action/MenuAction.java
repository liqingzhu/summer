/**
 * 
 */
package com.sx.rbac.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.sx.core.action.CoreAction;
import com.sx.rbac.biz.IMenuBiz;
import com.sx.rbac.entity.MenuEntity;
import com.sx.util.JSONUtils;
import com.sx.util.exception.SXException;

/**
 * @author Administrator
 * 所有和Menu菜单信息
 */
@Namespace(value="/back/role")
@ParentPackage(value="coreaction")
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class MenuAction extends CoreAction implements ModelDriven<MenuEntity>{
	@Autowired
	private IMenuBiz menubiz;
	private Log log = LogFactory.getLog(this.getClass());
	private MenuEntity menu = new MenuEntity();
	/**
	 *获取树形结构菜单数据信息 
	 **/
	@Action(value="gettreemenuinfo")
	public void getTreeMenuInfo() throws SXException{
		String str = "[]";
		try {
//			menu.setIds("2,3,4");
			List<MenuEntity> list = menubiz.findFirstMenu(menu);
			for (MenuEntity menu : list) {
				if(menu.getMcode()!=null&&!("").equals(menu)){
					menu.setLeaf(true);
				}
			}
			str=JSONUtils.convertListToJson(list);
		} catch (Exception e) {
			log.error("根据session中的权限数据信息",e);
			throw new SXException(e);
		}
		outJsonString(str);
	}
	/**
	 *按照session中获取的改
	 *权限中拥有的菜单数据信息 
	 **/
	@Action(value="getfirstmenuinfo")
	public void getFirstMenuInfo() throws SXException{
		String str = "{success:false,data:[]}";
		try {
//			menu.setIds("2,3,4");
			menu.setType(menu.getType());
			log.error("=========="+menu.getIds()+"=========");
			List<MenuEntity> list = menubiz.findFirstMenu(menu);
			log.error(list.size()+"======");
			String data=JSONUtils.convertListToJson(list);
			str = "{success:true,data:"+data+"}";
		} catch (Exception e) {
			log.error("根据session中的权限数据信息",e);
			throw new SXException(e);
		}
		outJsonString(str);
		
	}
	/**
	 * @return the menubiz
	 */
	public IMenuBiz getMenubiz() {
		return menubiz;
	}
	/**
	 * @param menubiz the menubiz to set
	 */
	public void setMenubiz(IMenuBiz menubiz) {
		this.menubiz = menubiz;
	}
	public MenuEntity getModel() {
		// TODO Auto-generated method stub
		return menu;
	}
	
}
