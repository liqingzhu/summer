package com.sx.core.services.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sx.core.dao.IAreaDao;
import com.sx.core.dao.ICoreDao;
import com.sx.core.entity.CoreEntity;
import com.sx.core.services.ICoreBiz;
@Service
public class CoreBiz implements ICoreBiz {
//	@Autowired
//	private IAreaDao areadao;
//	@Autowired
	private ICoreDao coredao;
	/* (non-Javadoc)
	 * @see com.sx.core.services.ICoreBiz#saveInfo(com.sx.core.entity.CoreEntity)
	 */
	public boolean saveInfo(CoreEntity core) {
		// TODO Auto-generated method stub
		/*areadao.addInfo(core);
		core.setTitle("BBBBBB");*/
		Object obj = core;
//		areadao.addInfo(obj);
		core.setTitle("CCCCCCCCCCC");
		obj = core;
//		areadao.addInfo(obj);
		core.setTitle("DDDDDDDDDDDDDDD");
		obj = core;
//		areadao.addInfo(obj);
		return false;
	}
}
