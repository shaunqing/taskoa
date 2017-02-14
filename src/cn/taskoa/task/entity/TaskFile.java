package cn.taskoa.task.entity;

import cn.taskoa.common.utils.TaskFileDetail;

public class TaskFile {

	private int taskfileid; // 文件表id
	private int taskid;// 任务表id
	private int creator_userid; // 创建人id
	private int operator_userid; // 执行人id
	private String taskfilename;// 文件名称
	private String taskfileurl;// 文件存储路径
	private String taskfiletype;// 文件类型：fj（创建人上传的附件）、jg（完成人上传的结果）
	private String operatime;

	public TaskFile() {

	}

	public TaskFile(TaskFileDetail detail, String taskfilename) {
		this.taskid = detail.getTaskid();
		this.taskfiletype = detail.getTaskfiletype();
		this.creator_userid = detail.getCreator_userid();
		this.operator_userid = detail.getOperator_userid();
		this.taskfilename = taskfilename;
	}

	@Override
	public String toString() {
		return "TaskFile [taskfileid=" + taskfileid + ", taskid=" + taskid + ", creator_userid=" + creator_userid
				+ ", operator_userid=" + operator_userid + ", taskfilename=" + taskfilename + ", taskfileurl="
				+ taskfileurl + ", taskfiletype=" + taskfiletype + ", operatime=" + operatime + "]";
	}

	public int getTaskfileid() {
		return taskfileid;
	}

	public void setTaskfileid(int taskfileid) {
		this.taskfileid = taskfileid;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public int getCreator_userid() {
		return creator_userid;
	}

	public void setCreator_userid(int creator_userid) {
		this.creator_userid = creator_userid;
	}

	public int getOperator_userid() {
		return operator_userid;
	}

	public void setOperator_userid(int operator_userid) {
		this.operator_userid = operator_userid;
	}

	public String getTaskfilename() {
		return taskfilename;
	}

	public void setTaskfilename(String taskfilename) {
		this.taskfilename = taskfilename;
	}

	public String getTaskfileurl() {
		return taskfileurl;
	}

	public void setTaskfileurl(String taskfileurl) {
		this.taskfileurl = taskfileurl;
	}

	public String getTaskfiletype() {
		return taskfiletype;
	}

	public void setTaskfiletype(String taskfiletype) {
		this.taskfiletype = taskfiletype;
	}

	public String getOperatime() {
		return operatime;
	}

	public void setOperatime(String operatime) {
		this.operatime = operatime;
	}

}
