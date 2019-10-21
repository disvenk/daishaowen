package com.daishaowen.test.yunshengerp.javacommon.base;

import cn.org.rapid_framework.beanutils.PropertyUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings( { "unchecked", "unused" })
public abstract class BaseIbatis3Dao<E, PK extends Serializable> extends DaoSupport implements EntityDao<E, PK> {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	private SqlSessionFactory sqlSessionFactory;
	private SqlSessionTemplate sqlSessionTemplate;

	protected void checkDaoConfig() throws IllegalArgumentException {
		Assert.notNull(sqlSessionFactory, "sqlSessionFactory must be not null");
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
		this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public Object getById(PK primaryKey) {
		Object object = getSqlSessionTemplate().selectOne(getFindByPrimaryKeyStatement(), primaryKey);
		return object;
	}

	public void deleteById(PK id) {
		int affectCount = getSqlSessionTemplate().delete(getDeleteStatement(), id);
	}

	public void save(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		int affectCount = getSqlSessionTemplate().insert(getInsertStatement(), entity);
	}

	public void update(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		int affectCount = getSqlSessionTemplate().update(getUpdateStatement(), entity);
	}

	/**
	 * 批量删除(获取属性不为空的值作为删除条件) Add by Chris li 2016/12/20
	 * 
	 * @param entity
	 */
	public void deleteBatch(E entity) {
		Map map = PropertyUtils.describe(entity);
		int affectCount = getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteBatch", map);
	}

	/**
	 * 查询全部(entity为参数)
	 * 
	 * @param entity
	 * @return
	 */
	public List<E> findAll(E entity) {
		Map map = PropertyUtils.describe(entity);
		List<E> list = sqlSessionTemplate.selectList(getFindPageStatement(), map);
		return list;
	}

	/**
	 * 查询全部(entity,sortColumns为参数,其中sortColumns需要注意,格式必须为:
	 * "数据库列名 排序方式,数据库列名 排序方式,...") Add by Chris li 2016/7/10
	 * 
	 * @param entity
	 * @return
	 */
	public List<E> findAll(E entity, String sortColumns) {
		Map map = PropertyUtils.describe(entity);
		map.put("sortColumns", sortColumns);
		List<E> list = sqlSessionTemplate.selectList(getFindPageStatement(), map);
		return list;
	}

	/**
	 * 分页查询全部结果
	 * 
	 * @param entity
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<E> findList(E entity, int offset, int limit) {
		Map map = PropertyUtils.describe(entity);
		List<E> list = sqlSessionTemplate.selectList(getFindPageStatement(), map, offset, limit);
		return list;
	}

	/**
	 * 用于子类覆盖,在insert,update之前调用
	 * 
	 * @param o
	 */
	protected void prepareObjectForSaveOrUpdate(E o) {
	}

	public String getIbatisMapperNamesapce() {
		throw new RuntimeException("not yet implement");
	}

	public String getFindByPrimaryKeyStatement() {
		return getIbatisMapperNamesapce() + ".getById";
	}

	public String getInsertStatement() {
		return getIbatisMapperNamesapce() + ".insert";
	}

	public String getUpdateStatement() {
		return getIbatisMapperNamesapce() + ".update";
	}

	public String getDeleteStatement() {
		return getIbatisMapperNamesapce() + ".delete";
	}

	public String getCountStatementForPaging(String statementName) {
		return statementName + ".count";
	}

	public String getFindPageStatement() {
		return getIbatisMapperNamesapce() + ".findPage";
	}

	protected Page pageQuery(String statementName, PageRequest pageRequest) {
		return pageQuery(getSqlSessionTemplate(), statementName, getCountStatementForPaging(statementName), pageRequest);
	}

	public static Page pageQuery(SqlSessionTemplate sqlSessionTemplate, String statementName, String countStatementName, PageRequest pageRequest) {

		Number totalCount = (Number) sqlSessionTemplate.selectOne(countStatementName, pageRequest);
		if (totalCount == null || totalCount.longValue() <= 0) {
			return new Page(pageRequest, 0);
		}
		Page page = new Page(pageRequest, totalCount.intValue());
		
		if (pageRequest.getPageNumber() > page.getLastPageNumber()) {
			// 如果请求页数超出最大页数,返回空
			page.setResult(new ArrayList());
		} else {
			/**
			 * 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.<br>
			 * 与getSqlMapClientTemplate().queryForList(statementName,parameterObject)配合使用
			 */
			Map filters = new HashMap();
			filters.put("offset", page.getFirstResult());
			filters.put("pageSize", page.getPageSize());
			filters.put("lastRows", page.getFirstResult() + page.getPageSize());
			filters.put("sortColumns", pageRequest.getSortColumns());

			Map parameterObject = PropertyUtils.describe(pageRequest);
			filters.putAll(parameterObject);

			List list = sqlSessionTemplate.selectList(statementName, filters, page.getFirstResult(), page.getPageSize());
			page.setResult(list);
		}
		return page;
	}

	public List findAll() {
		List<E> list = sqlSessionTemplate.selectList(getFindPageStatement(), null);
		return list;
	}

	public boolean isUnique(E entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}

	public void flush() {
		// ignore
	}

	public static class SqlSessionTemplate {
		SqlSessionFactory sqlSessionFactory;

		public SqlSessionFactory getSqlSessionFactory() {
			return sqlSessionFactory;
		}

		public SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
			this.sqlSessionFactory = sqlSessionFactory;
		}

		public Object execute(SqlSessionCallback action) {
			SqlSession session = null;
			try {
				session = sqlSessionFactory.openSession();
				Object result = action.doInSession(session);
				return result;
			} finally {
				if (session != null)
					session.close();
			}
		}

		public Object selectOne(final String statement, final Object parameter) {
			return execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.selectOne(statement, parameter);
				}
			});
		}

		public List selectList(final String statement, final Object parameter, final int offset, final int limit) {
			return (List) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.selectList(statement, parameter, new RowBounds(offset, limit));
				}
			});
		}

		public List selectList(final String statement, final Object parameter) {
			return (List) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.selectList(statement, parameter);
				}
			});
		}

		public int delete(final String statement, final Object parameter) {
			return (Integer) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.delete(statement, parameter);
				}
			});
		}

		public int update(final String statement, final Object parameter) {
			return (Integer) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.update(statement, parameter);
				}
			});
		}

		public int insert(final String statement, final Object parameter) {
			return (Integer) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.insert(statement, parameter);
				}
			});
		}
	}

	public static interface SqlSessionCallback {

		public Object doInSession(SqlSession session);

	}

}
