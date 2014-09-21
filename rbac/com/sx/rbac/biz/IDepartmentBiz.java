package com.sx.rbac.biz;

import java.util.List;

import com.sx.rbac.entity.DepartmentEntity;
import com.sx.util.exception.SXException;

public interface IDepartmentBiz {	
	public List<DepartmentEntity> findDepartmentEntity(DepartmentEntity dept) throws SXException;
	public Integer addDepartmentEntity(DepartmentEntity dept)  throws SXException;
	public boolean updateDepartmentEntity(DepartmentEntity dept)  throws SXException;
	public boolean deleteDepartmentEntity(DepartmentEntity dept)  throws SXException;
	public DepartmentEntity findOneDepartmentEntity(DepartmentEntity dept)  throws SXException;
}
