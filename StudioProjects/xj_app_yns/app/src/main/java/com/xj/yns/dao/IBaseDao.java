package com.xj.yns.dao;

import java.io.Serializable;
import java.util.List;
/**
 * 公共的实体操作接口
 * @author Administrator
 *
 * @param <M>
 */
public interface IBaseDao<M> {
	/**
	 * 增加
	 * @param m
	 * @return
	 */
	long insert(M m);
	/**
	 * 修改
	 * @param m
	 * @return
	 */
	int update(M m);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(Serializable id);//int long String---JAP
	/**
	 * 查询
	 * @return
	 */
	List<M> findAll();
	
}
