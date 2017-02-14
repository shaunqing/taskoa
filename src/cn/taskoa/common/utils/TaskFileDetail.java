package cn.taskoa.common.utils;

import cn.taskoa.common.filter.CurrentUser;

/**
 * 封装与任务文件（TaskFile）有关的信息currentuser,taskid,taskfiletype
 * 
 * @author LIQing
 *
 */
public class TaskFileDetail {

	private CurrentUser cUser;
	private int taskid;
	private String taskfiletype;
	private int creator_userid;
	private int operator_userid;

	public TaskFileDetail() {

	}

	public TaskFileDetail(CurrentUser cUser, int taskid, String taskfiletype) {
		this.cUser = cUser;
		this.taskid = taskid;
		this.taskfiletype = taskfiletype;
	}

	@Override
	public String toString() {
		return "TaskFileDetail [taskid=" + taskid + ", taskfiletype=" + taskfiletype + ", creator_userid="
				+ creator_userid + ", operator_userid=" + operator_userid + "]";
	}

	public CurrentUser getcUser() {
		return cUser;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public String getTaskfiletype() {
		return taskfiletype;
	}

	public void setTaskfiletype(String taskfiletype) {
		this.taskfiletype = taskfiletype;
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

}
