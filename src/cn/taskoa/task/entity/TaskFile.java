package cn.taskoa.task.entity;

public class TaskFile {

	private int taskfileid; // 文件表id
	private int taskid;// 任务表id
	private String taskfilename;// 文件名称
	private String taskfileurl;// 文件存储路径
	private String taskfiletype;// 文件类型：fj（创建人上传的附件）、jg（完成人上传的结果）
	private String operatime;

	public TaskFile() {

	}

	public TaskFile(int taskid, String taskfilename, String taskfiletype) {
		this.taskid = taskid;
		this.taskfilename = taskfilename;
		this.taskfiletype = taskfiletype;
	}

	@Override
	public String toString() {
		return "TaskFile [taskfileid=" + taskfileid + ", taskid=" + taskid + ", taskfilename=" + taskfilename
				+ ", taskfileurl=" + taskfileurl + ", taskfiletype=" + taskfiletype + ", operatime=" + operatime + "]";
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
