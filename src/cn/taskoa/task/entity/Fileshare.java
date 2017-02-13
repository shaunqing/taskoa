package cn.taskoa.task.entity;

public class Fileshare {

	private int shareid;// 分享表id
	private int taskid;// 分享的任务表id
	private int share_userid;// 分享任务对象的用户表id
	private String del_flag;// 是否删除

	public int getShareid() {
		return shareid;
	}

	public void setShareid(int shareid) {
		this.shareid = shareid;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public int getShare_userid() {
		return share_userid;
	}

	public void setShare_userid(int share_userid) {
		this.share_userid = share_userid;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

}
