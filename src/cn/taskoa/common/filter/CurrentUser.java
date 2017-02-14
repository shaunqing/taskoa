package cn.taskoa.common.filter;

import java.io.Serializable;

public class CurrentUser implements Serializable {

	private static final long serialVersionUID = -2331332687362665600L;

	private int userid; // 用户id
	private String loginname; // 用户登录名
	private String username; // 用户姓名
	private String password; // 密码
	private boolean mobileLogin; // 是否手机登录
	private String role; // 角色

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}

	public void setMobileLogin(boolean mobileLogin) {
		this.mobileLogin = mobileLogin;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
