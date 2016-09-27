package com.xj.yns.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUser extends SQLiteOpenHelper {
	private static final String NAME = "hxyg.db";//数据库名：欢喜云购
	private static final int VERSION = 1;
	public static final String TABLE_ID = "_id";// 通用的主键
	public static final String TABLE_USER_USERID = "userId";// 用户ID
	public static final String TABLE_USER = "user";// 用户表名
	public static final String TABLE_USER_USERNAME = "username";// 用户名
	public static final String TABLE_USER_PHONE = "phone";// 手机号码
	public static final String TABLE_USER_AVATAR = "avatar";//头像
	public static final String TABLE_USER_NICKNAME = "nickName";// 昵称
	public static final String TABLE_USER_SCORE = "score";// 积分
	public static final String TABLE_USER_BALANCE = "balance";// 抢币
	public static final String TABLE_USER_SEX = "sex";// 性别 1 女 2男 3保密
	public static final String TABLE_USER_BIRTHDATE = "birthdate";//出生日期

	public DBUser(Context context) {
		super(context, NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建一个表
		//主键、标题、摘要
		
		/*db.execSQL("CREATE TABLE " + TABLE_NEWS_NAME + " (" + //
				TABLE_ID + " integer primary key autoincrement, " + //
				TABLE_NEWS_TITLE + " varchar(50), " + //
				TABLE_NEWS_SUMMARY + " VARCHAR(200))"//
				);
		db.execSQL("CREATE TABLE " + TABLE_BOOK_NAME + " (" + //
				TABLE_ID + " integer primary key autoincrement, " + //
				TABLE_NEWS_TITLE + " varchar(50), " + //
				TABLE_NEWS_SUMMARY + " VARCHAR(200))"//
		);*/
		String sqlStr = "CREATE TABLE " + TABLE_USER + " (" + //
				TABLE_ID + " integer primary key autoincrement, " + //
				TABLE_USER_USERID + " varchar(50), " + //
				TABLE_USER_USERNAME + " varchar(50), " + //
				TABLE_USER_PHONE + " varchar(50), " + //
				TABLE_USER_AVATAR + " varchar(50), " + //
				TABLE_USER_NICKNAME + " varchar(50), " + //
				TABLE_USER_SCORE + " varchar(50), " + //
				TABLE_USER_BALANCE + " varchar(50), " + //
				TABLE_USER_SEX + " varchar(50), " + //
				TABLE_USER_BIRTHDATE + " varchar(50))";//
		db.execSQL(sqlStr);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
