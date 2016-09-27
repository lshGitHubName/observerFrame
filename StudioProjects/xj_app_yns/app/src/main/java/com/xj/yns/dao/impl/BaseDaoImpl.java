package com.xj.yns.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.xj.yns.GlobalParams;
import com.xj.yns.dao.DBUser;
import com.xj.yns.dao.IBaseDao;
import com.xj.yns.dao.annotation.Column;
import com.xj.yns.dao.annotation.ID;
import com.xj.yns.dao.annotation.TableName;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class BaseDaoImpl<M> implements IBaseDao<M> {
	// 问题一：表名的获取
	// 方案一：实体类的名称就是表名
	// 方案二：写一个注解@TableName(DBHelper.TABLE_NEWS_NAME),下在类的上面

	// 问题二：实体中得字段数据添加到表中对应列下面
	// 问题三：如何表中列的数据设置给实体的字段中
	// 问题四：如何获取到实体中得主键信息
	// 问题五：当前操作的实体对象的获取

	@SuppressWarnings("unused")
	private static final String TAG = "DAOSupport";
	protected Context context;
	protected DBUser dbUser;
	protected SQLiteDatabase database;

	public BaseDaoImpl() {
		super();
		this.context = GlobalParams.CONTEXT;
		dbUser = new DBUser(context);
		database = dbUser.getWritableDatabase();
	}

	@Override
	public long insert(M m) {
		ContentValues values = new ContentValues();
		// 问题：关于主键（自增的主键）
		fillColumn(m, values);// 数据元作为第一个参数、目标对象第二个参数
		return database.insert(getTableName(), null, values);
	}

	@Override
	public int update(M m) {
		ContentValues values = new ContentValues();
		fillColumn(m, values);// 数据元作为第一个参数、目标对象第二个参数
		return database.update(getTableName(), values, DBUser.TABLE_ID + "=?", new String[] { getId(m) });
	}

	@Override
	public int delete(Serializable id) {
		return database.delete(getTableName(), DBUser.TABLE_ID + "=?", new String[] { id.toString() });
	}

	@Override
	public List<M> findAll() {
		List<M> result = null;
		Cursor query = database.query(getTableName(), null, null, null, null, null, null);
		try {
			if (query != null) {
				result = new ArrayList<M>();// List<M> result=new ArrayList<M>();
				while (query.moveToNext()) {
					M m = getInstance();
					fillField(query, m);
					result.add(m);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (query != null)
				query.close();
		}

		return result;
	}

	public List<M> findByCondition(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
		List<M> result = null;
		Cursor query = database.query(getTableName(), columns, selection, selectionArgs, groupBy, having, orderBy);
		try {
			if (query != null) {
				result = new ArrayList<M>();// List<M> result=new ArrayList<M>();
				while (query.moveToNext()) {
					M m = getInstance();
					fillField(query, m);
					result.add(m);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (query != null)
				query.close();
		}

		return result;
	}

	public List<M> findByCondition(String selection, String[] selectionArgs, String orderBy) {
		return findByCondition(null, selection, selectionArgs, null, null, orderBy);
	}

	/**
	 * 问题一：表名的获取
	 * 
	 * @return
	 */
	private String getTableName() {
		M m = getInstance();
		TableName annotation = m.getClass().getAnnotation(TableName.class);
		return annotation.value();
	}

	/**
	 * 问题二：实体中得字段数据添加到表中对应列下面
	 * 
	 * @param values
	 * @param m
	 */
	private void fillColumn(M m, ContentValues values) {
		// 实体中得字段与数据库表中列信息的对应关系——策略同表名
		// 获取到M中的所有的字段信息，找到上面有@Column注解字段

		// Returns an array containing Field objects for all fields declared in the class represented by this Class.
		Field[] fields = m.getClass().getDeclaredFields();
		for (Field item : fields) {
			item.setAccessible(true);

			Column column = item.getAnnotation(Column.class);
			if (column != null) {
				ID id = item.getAnnotation(ID.class);
				if (id != null) {
					if (id.autoincrement()) {
						// 当主键是自增的时候不需要设置，主键信息到ContentValues
					} else {
						String key = column.value();// 需要设置到valuse的key的信息
						try {
							String value = item.get(m).toString();// Returns the value of the field in the specified object.
							// values.put(DBHelper.TABLE_NEWS_TITLE, news.getTitle());
							values.put(key, value);

						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				} else {
					String key = column.value();// 需要设置到valuse的key的信息
					try {
						if (item.get(m) != null) {
							String value = item.get(m).toString();// Returns the value of the field in the specified object.
							values.put(key, value);
						}
						// values.put(DBHelper.TABLE_NEWS_TITLE, news.getTitle());

					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}

			}

		}

	}

	/**
	 * 问题三：如何表中列的数据设置给实体的字段中
	 */
	private void fillField(Cursor query, M m) {

		// int id = query.getInt(columnIndex);
		// news.setId(id);

		// 此处省略n行代码

		Field[] fields = m.getClass().getDeclaredFields();
		for (Field item : fields) {
			item.setAccessible(true);

			Column column = item.getAnnotation(Column.class);
			if (column != null) {
				// int columnIndex = query.getColumnIndex(DBHelper.TABLE_ID);
				int columnIndex = query.getColumnIndex(column.value());
				// int id = query.getInt(columnIndex);
				// 如果是主键+自增（int or long）
				Object value;
				if (item.getType() == int.class) {
					value = query.getInt(columnIndex);
				} else if (item.getType() == long.class) {
					value = query.getLong(columnIndex);
				} else {
					value = query.getString(columnIndex);
				}

				try {
					item.set(m, value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			}

		}

	}

	/**
	 * 问题四：如何获取到实体中得主键信息
	 * 
	 * @param m
	 */
	private String getId(M m) {
		Field[] fields = m.getClass().getDeclaredFields();
		for (Field item : fields) {
			item.setAccessible(true);
			ID id = item.getAnnotation(ID.class);
			if (id != null) {
				try {
					return item.get(m).toString();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 问题五：当前操作的实体对象的获取--通用解决方案
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public M getInstance() {
		// 思路：M到底是什么时候被确定
		// 子类在继承DAOSuport时通过泛型传递了一个参数
		// ①获取到子类——运行时的子类
		// ②获取这个子类的父类——获取到支持泛型的父类
		// ③通过父类获取到泛型里面的参数

		// String name = super.getClass().getName();
		// Log.i(TAG, name);
		Class clazz = getClass();
		// Class superclass = clazz.getSuperclass();
		Type genericSuperclass = clazz.getGenericSuperclass();// 获取到支持泛型的父类
		// JDK让泛型实现一个接口（参数化的类型）
		// 与泛型有关的操作都在接口中（PlayGame）
		if (genericSuperclass instanceof ParameterizedType) {
			Type[] arguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();

			Class type = (Class) arguments[0];
			try {
				return (M) type.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
