package com.xj.yns.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 实体中得字段与数据库表中列信息的对应关系
 * @author Administrator
 *
 */
public @interface Column {
	String value();
}
