package com.sx.rbac.dao;

import java.util.List;

import com.sx.rbac.entity.DepartmentEntity;

public interface IDepartmentDao {	
	public List<DepartmentEntity> findDepartmentEntity(DepartmentEntity dept);
	public Integer addDepartmentEntity(DepartmentEntity dept);
	public Integer updateDepartmentEntity(DepartmentEntity dept);
	public Integer deleteDepartmentEntity(DepartmentEntity dept);
	public DepartmentEntity findOneDepartmentEntity(DepartmentEntity dept);
}
