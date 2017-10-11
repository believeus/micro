package cn.believeus.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.believeus.PaginationUtil.Page;
import cn.believeus.PaginationUtil.Pageable;
import cn.believeus.dao.MySQLDao;

@Service(value="mysqlService")
public class MySQLService{
	@Resource
	private MySQLDao mysqlDao;
	
	
	
	public void saveOrUpdate(Object object) {
		mysqlDao.saveOrUpdate(object);
	}
	
	
	public void delete(Class<?> clazz, Integer id) {
		((MySQLDao)mysqlDao).delete(clazz, id);
	}

	
	
	public void delete(Object entity) {
		((MySQLDao)mysqlDao).delete(entity);
	}
	
	
	public void delete(Class<?> clazz,List<?> ids){
		((MySQLDao)mysqlDao).delete(clazz, ids);
	}

	
	public void delete(Class<?> clazz, String property, Object value) {
		((MySQLDao)mysqlDao).delete(clazz, property, value);
	}

	public Object findObject(String hql) {
		return ((MySQLDao)mysqlDao).findObject(hql);
	}

	
	public Object findObject(Class<?> clazz, Object property, Object value) {
		return ((MySQLDao)mysqlDao).findObject(clazz, property, value);
	}

	
	public Object findObject(Class<?> clazz, Integer id) {
		return ((MySQLDao)mysqlDao).findObject(clazz, id);
	}

	
	public List<?> findObjectList(Class<?> clazz, Object property, Object value) {
		return ((MySQLDao)mysqlDao).findObjectList(clazz, property, value);
	}


	
	public List<?> findObjectList(Class<?> clazz, Object property,
			Object value1, Object property2, Object value2) {
		return ((MySQLDao)mysqlDao).findObjectList(clazz, property, value1, property2, value2);
	}

	
	public List<?> findObjectList(Class<?> clazz) {
		return ((MySQLDao)mysqlDao).findObjectList(clazz);
	}

	
	
	public List<?> findObjectList(Class<?> clazz, Integer num) {
		return ((MySQLDao)mysqlDao).findObjectList(clazz, num);
	}

	
	
	public List<?> findObjectList(Class<?> clazz, String property,
			Object value, int num) {
		return ((MySQLDao)mysqlDao).findObjectList(clazz, property, value, num);
	}

	public List<?> findObjectList(String hql, Integer num) {
		return ((MySQLDao)mysqlDao).findObjectList(hql,num);
	}

	public Page<?> findObjectList(String hql, Pageable pageable) {
		return (Page<?>) ((MySQLDao)mysqlDao).getPageDateList(hql,pageable );
	}

}
