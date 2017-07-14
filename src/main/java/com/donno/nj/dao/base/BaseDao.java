package com.donno.nj.dao.base;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

	T findById(Long id);

	List<T> getList(Map m);

	Integer count(Map m);

	Long insert(T record);

	void delete(Long id);

	void update(T object);

}
