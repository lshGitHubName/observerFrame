package com.xj.yns.engine;

import java.util.List;

import com.xj.yns.entity.UserInfo;

public interface IUserEngine {
	public boolean userLogin(String name,String password);
	
	//获取项目列表信息
	//public List<UserInfo> getProjectList();

}
