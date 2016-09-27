package com.xj.yns.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 实体中主键信息
 * @author Administrator
 *
 */
public @interface ID {
	/**
	 * 是否自增
	 * 
	 * @return
	 */
	boolean autoincrement();

}
