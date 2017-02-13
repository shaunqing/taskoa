package cn.taskoa.task.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.taskoa.common.properties.Global;
import cn.taskoa.common.service.BaseService;
import cn.taskoa.common.utils.FileOperateUtil;
import cn.taskoa.common.utils.PageModel;
import cn.taskoa.common.utils.exceptions.NotFoundResourceExceptions;
import cn.taskoa.task.dao.TaskDao;
import cn.taskoa.task.dao.TaskFileDao;
import cn.taskoa.task.entity.Task;

@Service
@Transactional(readOnly = true)
public class TaskService extends BaseService {

	@Autowired
	private TaskDao dao;
	
	@Autowired
	private TaskFileDao taskFileDao;

	// 回滚
	@Transactional(readOnly = false)
	public void save() throws Exception {
		

	}

	public void list() {

	}

	/*----- 以上是测试内容 -------*/
	
	
	/**
	 * 查询登录者创建的任务
	 * @param creator_userid
	 * @param pageNo
	 * @return
	 */
	public PageModel<Task> find(int creator_userid, int pageNo) {
		PageModel<Task> pageModel = new PageModel<>();
		int rowIndex = (pageNo - 1) * pageModel.getPageSize();

		List<Task> tasks = dao.listByCreator(creator_userid, rowIndex, pageModel.getPageSize());
		pageModel.setPageList(tasks);
		pageModel.setTotalRecords(dao.countByCreator(creator_userid));

		return pageModel;
	}
	
	/**
	 * 显示任务、附件、任务结果
	 * @param creator_userid 发起人id
	 * @param taskid 任务id
	 * @return
	 */
	public List<Task> getTaskAndResultFile(int creator_userid, int taskid) {
		return dao.findTaskAndResultByTaskid(creator_userid, taskid);
	}
	
	/**
	 * 下载文件
	 * @param taskfileid
	 * @return
	 * @throws NotFoundResourceExceptions 
	 */
	public ResponseEntity<byte[]> downloadTaskFile(int cuser_id, int taskfileid) throws NotFoundResourceExceptions {
		Task task = taskFileDao.getWithUser(taskfileid);
		if (task != null && (task.getCreator_userid() == cuser_id || task.getOperator_userid() == cuser_id)) {
			File file = new File(Global.getConfig("TASKFILE_URL") + task.getTaskfile().getTaskfileurl());
			return FileOperateUtil.downloadFile(task.getTaskfile().getTaskfilename(), file);
		} else {
			throw new NotFoundResourceExceptions("无权下载该文件！");
		}
	}
	
	/**
	 * 将任务删除位置为1
	 * @param cuser_id
	 * @param taskid
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public String deleteTask(int cuser_id , int taskid) throws Exception {
		if(dao.deleteTask(cuser_id, taskid) == 1) {
			return "success";
		} else {
			return "无权删除该项目！";
		}
	}
	
	/**
	 * 给发起人显示任务信息和附件
	 * @param userid
	 * @param taskid
	 * @param taskfiletype
	 * @return
	 */
	public List<Task> listByTaskidAndFiletype(int userid, int taskid, String taskfiletype) {
		return dao.listByTaskid(userid, taskid, taskfiletype);
	}
	
	/**
	 * 发起人修改任务信息
	 * @param userid
	 * @param taskNew
	 * @return
	 */
	@Transactional(readOnly = false)
	public String updateTaskInfo(int userid, Task taskNew) {
		int result = -1;
		try {
			result = dao.updateTaksInfo(userid, taskNew);

			if (1 == result) {
				return "success";
			}
			return "参数错误，请重新输入！";
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return "更新异常，请重试！";
		}
	}
	
	/**
	 * 删除任务文件
	 * @param userid
	 * @param taskfileid
	 * @return
	 */
	@Transactional(readOnly = false)
	public String deleteTaskFile(int userid, int taskfileid) {
		String result = "";
		try {
			// 用文件id和用户id，查询该文件是否存在
			String taskFileUrl = taskFileDao.getByTaskFileIdAndCrearot(taskfileid, userid);
			if (taskFileUrl == null || ("").equals(taskFileUrl)) {
				return "操作的文件不存在！";
			}

			// 删除文件记录
			taskFileDao.delete(taskfileid);

			// 从磁盘中删除该文件
			File file = new File(Global.getConfig("TASKFILE_URL") + taskFileUrl);
			if (file.exists() && file.isFile()) {
				file.delete();
			}

			result = "success";
		} catch (Exception e) {
			logger.error("userid: " + userid + ":" + e.getLocalizedMessage());
			result = "文件已被删除！";
		}
		return result;
	}
}
