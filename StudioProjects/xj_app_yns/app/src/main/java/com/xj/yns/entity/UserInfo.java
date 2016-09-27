package com.xj.yns.entity;


public class UserInfo implements java.io.Serializable{

	/**
	 * 实时数据-雨量数据实体
        
	 */
	private static final long serialVersionUID = 1L;
	
	private int logid;
	private String u_name;
	private String u_pwd;
	public int getLogid() {
		return logid;
	}
	public void setLogid(int logid) {
		this.logid = logid;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_pwd() {
		return u_pwd;
	}
	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}
	
}
