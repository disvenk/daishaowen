package com.daishaowen.test.yunshengerp.javacommon.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
@Transactional
public abstract class BaseService <E,PK extends Serializable>{
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	protected abstract EntityDao getEntityDao();

	@Transactional(readOnly=true)
	public E getById(PK id) throws DataAccessException {
		return (E)getEntityDao().getById(id);
	}
	
	@Transactional(readOnly=true)
	public List<E> findAll() throws DataAccessException {
		return getEntityDao().findAll();
	}
	
	@Transactional(readOnly=true)
	public List<E> findAll(E entity) throws DataAccessException {
		return getEntityDao().findAll(entity);
	}
	
	/** 根据id检查是否插入或是更新数据 */
	public void saveOrUpdate(E entity) throws DataAccessException {
		getEntityDao().saveOrUpdate(entity);
	}
	
	/** 插入数据 */
	public void save(E entity) throws DataAccessException {
		getEntityDao().save(entity);
	}
	
	public void removeById(PK id) throws DataAccessException {
		getEntityDao().deleteById(id);
	}
	
	public void update(E entity) throws DataAccessException {
		getEntityDao().update(entity);
	}
	
	@Transactional(readOnly=true)
	public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException {
		return getEntityDao().isUnique(entity, uniquePropertyNames);
	}
	
}
