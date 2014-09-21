package com.sx.rbac.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sx.rbac.dao.IDepartmentDao;
import com.sx.rbac.biz.IDepartmentBiz;
import com.sx.rbac.dao.IMenuDao;
import com.sx.rbac.entity.DepartmentEntity;
import com.sx.util.exception.SXException;
//@Service(value="departmentbiz")
public class DepartmentBiz implements IDepartmentBiz {
	@Autowired
	private IDepartmentDao departmentdao;
	private Log log = LogFactory.getLog(this.getClass());
	public Integer addDepartmentEntity(DepartmentEntity dept) throws SXException{
		Integer id = null;
		try {
			Integer count = departmentdao.addDepartmentEntity(dept);
			if(count>0){
				id = dept.getDepid();
			}
		} catch (Exception e) {
			log.error("新增单位信息",e);
			throw new SXException("新增单位信息",e);
		}
		return id;
	}

	public boolean deleteDepartmentEntity(DepartmentEntity dept) throws SXException{
		boolean flag = false;
		try {
			Integer count  = departmentdao.deleteDepartmentEntity(dept);
			if(count>0){
				flag = true;
			}
		} catch (Exception e) {
			log.error("更新单位信息",e);
			throw new SXException("更新单位信息",e);
		}
		return flag;
	}

	public List<DepartmentEntity> findDepartmentEntity(DepartmentEntity dept)  throws SXException{
		List<DepartmentEntity> list = new ArrayList<DepartmentEntity>();
		try {
			list = departmentdao.findDepartmentEntity(dept);
		} catch (Exception e) {
			log.error("查找单位信息",e);
			throw new SXException("查询单位信息",e);
		}
		return list;
	}

	public DepartmentEntity findOneDepartmentEntity(DepartmentEntity dept) throws SXException{
		DepartmentEntity department = null;
		try {
			department = departmentdao.findOneDepartmentEntity(dept);
		} catch (Exception e) {
			log.error("查找一个单位信息",e);
			throw new SXException("查询一个单位信息",e);
		}
		return department;
	}

	public boolean updateDepartmentEntity(DepartmentEntity dept) throws SXException{
		boolean flag = false;
		try {
			Integer count = departmentdao.updateDepartmentEntity(dept);
			if(count>0){
				flag = true;
			}
		} catch (Exception e) {
			log.error("更新单位信息",e);
			throw new SXException("更新单位信息",e);
		}
		return flag;
	}

	public void setDepartmentdao(IDepartmentDao departmentdao) {
		this.departmentdao = departmentdao;
	}
	
}
