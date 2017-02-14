package cn.taskoa.task.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.taskoa.common.filter.CurrentUser;
import cn.taskoa.common.properties.Global;
import cn.taskoa.common.service.BaseService;
import cn.taskoa.common.utils.FileOperateUtil;
import cn.taskoa.common.utils.PageModel;
import cn.taskoa.common.utils.TaskFileDetail;
import cn.taskoa.common.utils.exceptions.NotFoundResourceExceptions;
import cn.taskoa.task.dao.TaskDao;
import cn.taskoa.task.dao.TaskFileDao;
import cn.taskoa.task.entity.Task;
import cn.taskoa.task.entity.TaskFile;

@Service
@Transactional(readOnly = true)
public class TaskService extends BaseService {

	@Autowired
	private TaskDao dao;
	
	@Autowired
	private TaskFileDao taskFileDao;
	
	/**
	 * 查询登录者创建的任务
	 * @param creator_userid
	 * @param pageNo
	 * @return
	 */
	public PageModel<Task> listByCreator(int creator_userid, int pageNo) {
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
	public List<Task> listTaskAndFile(int creator_userid, int taskid) {
		return dao.listByTaskid(creator_userid, taskid, "all");
	}
	
	/**
	 * 下载文件，用户必须是创建人或执行人或管理员
	 * @param taskfileid
	 * @return
	 * @throws NotFoundResourceExceptions 
	 */
	public ResponseEntity<byte[]> downloadTaskFile(CurrentUser cUser, int taskfileid)
			throws NotFoundResourceExceptions {
		Task task = taskFileDao.getWithUser(taskfileid);
		// 判断用户身份，是创建人或执行人或管理员
		if (task != null && ("admin".equals(cUser.getRole()) || task.getCreator_userid() == cUser.getUserid()
				|| task.getOperator_userid() == cUser.getUserid())) {
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
	 * 给创建人显示任务信息和附件
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
			String taskFileUrl = taskFileDao.getByIdAndCreator(taskfileid, userid);
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
	
	/*------ 【更新文件】功能   开始 ------*/
	
	/**
	 * 更新任务附件，或更新任务结果文件
	 * 执行此方法时，文件至少已存在一个
	 * @param fileDetail
	 * @param taskFiles
	 * @return
	 */
	public String updateTaskFile(TaskFileDetail fileDetail, MultipartFile[] files) {
		try {
			// 根据taskid找出所有taskfile
			List<TaskFile> taskFiles = taskFileDao.getByTaskidAndType(fileDetail);
			
			//获取该任务的创建人、执行人id
			fileDetail.setCreator_userid(taskFiles.get(0).getCreator_userid());
			fileDetail.setOperator_userid(taskFiles.get(0).getOperator_userid());

			// 已存在的文件
			Map<String, TaskFile> taskFileMap = taskFiles2Map(taskFiles); // 数组变为以taskfilename为KEY的MAP
			// 要上传的文件
			Map<String, MultipartFile> multiFileMap = multiFiles2Map(files);

			// 初始化此次要更新或插入的文件
			List<TaskFile> newTaskFiles = initUpdateTaskFiles(fileDetail, taskFileMap, multiFileMap);

			// 选出需要被删除的文件路径
			List<String> deleteTaskFileUrl = initDeleteTaskFileUrl(newTaskFiles);

			// 上传【修改功能】中的文件
			upload4NewTaskFile(fileDetail.getcUser().getUserid(), newTaskFiles, multiFileMap);

			// 更新数据库
			updateTaskFile(newTaskFiles);

			// 删除应该被覆盖的文件
			deleteOverrideTaskFile(deleteTaskFileUrl);

		} catch (Exception e) {
			return e.getMessage();
		}
		return "文件更新成功！";
	}
	
	/**
	 * 将数据库中的任务文件List变为以文件名为KEY的MAP形式
	 * @param taskFiles
	 * @return
	 */
	private Map<String, TaskFile> taskFiles2Map(List<TaskFile> taskFiles) {
		Map<String, TaskFile> taskFileMap = new HashMap<>();
		for (TaskFile taskfile : taskFiles) {
			taskFileMap.put(taskfile.getTaskfilename(), taskfile);
		}
		return taskFileMap;
	}
	
	/**
	 * 将新上传的文件数组变为以文件名为KEY的MAP形式
	 * @param multiFiles
	 * @return
	 */
	private Map<String, MultipartFile> multiFiles2Map(MultipartFile[] multiFiles) {
		Map<String, MultipartFile> multiFileMap = new HashMap<>();
		for (MultipartFile multiFile : multiFiles) {
			multiFileMap.put(multiFile.getOriginalFilename(), multiFile);
		}
		return multiFileMap;
	}
	
	/**
	 * 初始化上传文件的List<TaskFile> 若是新文件，则创建新的TaskFile 若是覆盖原文件，则调出数据库中的记录（有taskid）
	 * @param fileDetail
	 * @param taskFileMap 已存在的文件
	 * @param multiFileMap 要上传的文件
	 * @return
	 * @throws Exception
	 */
	private List<TaskFile> initUpdateTaskFiles(TaskFileDetail fileDetail, Map<String, TaskFile> taskFileMap,
			Map<String, MultipartFile> multiFileMap) throws Exception {
		// 返回值
		List<TaskFile> newTaskFiles = new ArrayList<>();

		// taskid已存有多少个文件
		int filesNumber = taskFileMap.size();

		for (String multiFileName : multiFileMap.keySet()) {
			if (taskFileMap.containsKey(multiFileName)) {
				newTaskFiles.add(taskFileMap.get(multiFileName));
			} else {
				// 不存在，则叠加文件个数。并插入新增列表
				++filesNumber;
				newTaskFiles.add(new TaskFile(fileDetail, multiFileName));
			}
		}

		if (filesNumber > Integer.valueOf(Global.getConfig("FILE_NUMBER"))) {
			throw new Exception("文件个数不能超过" + Integer.valueOf(Global.getConfig("FILE_NUMBER")) + "个！");
		}
		return newTaskFiles;
	}
	
	/**
	 * 选出需要被删除的文件路径
	 * @param newTaskFiles
	 * @return
	 */
	private List<String> initDeleteTaskFileUrl(List<TaskFile> newTaskFiles) {
		List<String> deleteTaskFiles = new ArrayList<>();
		for (TaskFile taskFile : newTaskFiles) {
			if (taskFile.getTaskfileid() != 0) {
				deleteTaskFiles.add(taskFile.getTaskfileurl());
			}
		}
		return deleteTaskFiles;
	}
	
	/**
	 * 上传【更新功能】的文件，更新taskfileurl
	 * @param userid
	 * @param newTaskFiles
	 * @param multiMap
	 * @throws Exception
	 */
	private void upload4NewTaskFile(int userid, List<TaskFile> newTaskFiles, Map<String, MultipartFile> multiMap)
			throws Exception {
		try {
			for (TaskFile newTaskFile : newTaskFiles) {
				String taskfileurl = FileOperateUtil.uploadMultipartFile(Global.getConfig("TASKFILE_URL"),
						multiMap.get(newTaskFile.getTaskfilename()), userid);
				newTaskFile.setTaskfileurl(taskfileurl);
			}
		} catch (Exception e) {
			logger.error("userid: " + userid + "; " + e.getLocalizedMessage());
			throw new Exception("上传文件异常");
		}
	}
	
	/**
	 * 根据taskfileid判断，哪些更新，哪些插入
	 * @param newTaskFiles
	 * @throws Exception
	 */
	private void updateTaskFile(List<TaskFile> newTaskFiles) throws Exception {
		List<TaskFile> overrideTaskFile = new ArrayList<>();
		List<TaskFile> insertTaskFile = new ArrayList<>();

		for (TaskFile taskFile : newTaskFiles) {
			if (taskFile.getTaskfileid() == 0) {
				insertTaskFile.add(taskFile);
			} else {
				overrideTaskFile.add(taskFile);
			}
		}

		if (overrideTaskFile != null && overrideTaskFile.size() > 0) {
			taskFileDao.batchUpdateUrl(overrideTaskFile);
		}

		if (insertTaskFile != null && insertTaskFile.size() > 0) {
			taskFileDao.batchInsert(insertTaskFile);
		}
	}
	
	/**
	 * 删除用户需要覆盖的文件
	 * @param deleteTaskFileUrl
	 * @throws Exception
	 */
	private void deleteOverrideTaskFile(List<String> deleteTaskFileUrl) throws Exception {
		for (String taskFileUrl : deleteTaskFileUrl) {
			File file = new File(Global.getConfig("TASKFILE_URL") + taskFileUrl);
			if (file.exists() && file.isFile()) {
				file.delete();
			}
		}
	}

	/*------ 【更新文件】功能   结束 ------*/

}
