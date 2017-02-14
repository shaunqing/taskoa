package cn.taskoa.sys.entity;

public class User {

	private int userid; // 用户表id
	private String userno;// 用户编号
	private String loginname; // 用户登录名
	private String username;// 用户姓名
	private String password;// 登录密码
	private int usergroupid;// 用户组表id
	private String role; // 角色

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
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

	public int getUsergroupid() {
		return usergroupid;
	}

	public void setUsergroupid(int usergroupid) {
		this.usergroupid = usergroupid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
