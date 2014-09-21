/**
 * 
 */
package com.sx.rbac.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import com.sx.core.action.CoreAction;
import com.sx.rbac.biz.IDepartmentBiz;
import com.sx.rbac.entity.DepartmentEntity;
import com.sx.util.exception.SXException;

/**
 * @author Administrator
 *单位管理实体类
 */
@Namespace(value="/back/organise")  
@ParentPackage(value="coreaction")
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class DepartmentAction extends CoreAction implements ModelDriven<DepartmentEntity> {
	private Log log = LogFactory.getLog(this.getClass());
	private DepartmentEntity department = new DepartmentEntity();
	@Autowired
	private IDepartmentBiz departmentbiz;
	@Action(value="deletedepartment")
	public void deleteDepartment() throws SXException{
		boolean flag = false;
		String result = "{success:false}";
		try {
			department.setParentPath(department.getParentPath()+"/"+department.getDepid().intValue());
			flag = departmentbiz.deleteDepartmentEntity(department);
			if(flag){
				result = "{success:true}";
			}
		} catch (Exception e) {
			log.error("删除单位",e);
			throw new SXException("删除单位",e);
		}
		outJsonString(result);
	}

	@Action(value="updatedepartment")
	public void updateDepartment() throws SXException{
		boolean flag = false;
		String result = "{success:false}";
		try {
			flag = departmentbiz.updateDepartmentEntity(department);
			if(flag){
				result = "{success:true}";
			}
		} catch (Exception e) {
			log.error("修改单位",e);
			throw new SXException("修改单位",e);
		}
		outJsonString(result);
	}
	@Action(value="adddepartment")
	public void addDepartment() throws SXException{
		Integer id = null;
		HashMap<String,Object> map = new HashMap<String,Object>();
		String result = "{success:false,depid:0}";
		try {
			id = departmentbiz.addDepartmentEntity(department);
			if(id==null){
				id=0;
			}
			map.put("id", id);
			map.put("success", true);
			map.put("parentPath", department.getParentPath());
			result = JSONObject.toJSONString(map);
		} catch (Exception e) {
			log.error("新增单位",e);
			throw new SXException("新增单位",e);
		}
		outJsonString(result);
	}
	@Action(value="findalldepartment")
	public void findAllDepartment() throws SXException{
		List<DepartmentEntity> list = departmentbiz.findDepartmentEntity(department);
		try {
			String str = JSONArray.toJSONString(list);
			outJsonString(str);
		} catch (Exception e) {
			outJsonString("[]");
			log.error("查询单位信息",e);
			throw new SXException("查询单位信息",e);
		}
	}
	public DepartmentEntity getModel() {
		return department;
	}
	public void setDepartmentbiz(IDepartmentBiz departmentbiz) {
		this.departmentbiz = departmentbiz;
	}
	
}
