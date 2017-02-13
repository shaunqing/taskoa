package cn.taskoa.sys.entity;

public class UserLog {

	private int loginid; // 登录日志表id
	private String loginname; // 登录用户名
	private String loginip; // 登录ip
	private char login_result; // 登录结果
	private String logintime; // 登录时间

	@Override
	public String toString() {
		return "UserLog [loginid=" + loginid + ", loginname=" + loginname + ", loginip=" + loginip + ", login_result="
				+ login_result + ", logintime=" + logintime + "]";
	}

	public int getLoginid() {
		return loginid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public char getLogin_result() {
		return login_result;
	}

	public void setLogin_result(char login_result) {
		this.login_result = login_result;
	}

	public String getLogintime() {
		return logintime;
	}

}
