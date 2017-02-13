package cn.taskoa.task.entity;

import cn.taskoa.sys.entity.User;

public class Task {

	private int taskid; // 任务表id
	private String taskno;// 任务编号
	private String taskname;// 任务名称
	private int creator_userid;// 发起人id
	private int operator_userid;// 执行人id
	private String taskdesc;// 任务描述
	private String time_start;// 任务开始时间
	private String time_end;// 任务结束时间
	private String time_created;// 任务创建时间
	private String resultdesc; // 任务结果
	private String taskstat;// 任务状态
	private char del_flag;// 任务是否被删除（1：删除，0：未删除）
	private String operatime;// 操作时间

	// 用于连表查询
	private User creator; // 发起人
	private User operator;// 执行人
	private TaskFile taskfile;// 任务的附件

	@Override
	public String toString() {
		return "Task [taskid=" + taskid + ", taskno=" + taskno + ", taskname=" + taskname + ", creator_userid="
				+ creator_userid + ", operator_userid=" + operator_userid + ", taskdesc=" + taskdesc + ", time_start="
				+ time_start + ", time_end=" + time_end + ", time_created=" + time_created + ", resultdesc="
				+ resultdesc + ", taskstat=" + taskstat + ", del_flag=" + del_flag + ", operatime=" + operatime + "]";
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public String getTaskno() {
		return taskno;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
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

	public String getTaskdesc() {
		return taskdesc;
	}

	public void setTaskdesc(String taskdesc) {
		this.taskdesc = taskdesc;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getTime_created() {
		return time_created;
	}

	public void setTime_created(String time_created) {
		this.time_created = time_created;
	}

	public String getResultdesc() {
		return resultdesc;
	}

	public void setResultdesc(String resultdesc) {
		this.resultdesc = resultdesc;
	}

	public String getTaskstat() {
		return taskstat;
	}

	public void setTaskstat(String taskstat) {
		this.taskstat = taskstat;
	}

	public char getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(char del_flag) {
		this.del_flag = del_flag;
	}

	public String getOperatime() {
		return operatime;
	}

	public void setOperatime(String operatime) {
		this.operatime = operatime;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public TaskFile getTaskfile() {
		return taskfile;
	}

	public void setTaskfile(TaskFile taskfile) {
		this.taskfile = taskfile;
	}

}
