package cn.taskoa.task.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.taskoa.common.filter.CurrentUser;
import cn.taskoa.common.utils.FileTypeUtil;
import cn.taskoa.common.utils.Message;
import cn.taskoa.common.utils.PageModel;
import cn.taskoa.common.utils.TaskFileDetail;
import cn.taskoa.common.utils.exceptions.NotFoundResourceExceptions;
import cn.taskoa.common.key.TasKey;
import cn.taskoa.common.web.BaseController;
import cn.taskoa.task.entity.Task;
import cn.taskoa.task.entity.TaskFile;
import cn.taskoa.task.service.TaskService;

@Controller
@RequestMapping(value = "/task")
public class TaskController extends BaseController {

	@Autowired
	private TaskService taskService;

	/**
	 * 显示当前用户创建的任务（分页显示）
	 */
	@RequestMapping(value = "/mylist/{pageNo}", method = RequestMethod.GET)
	public String list(CurrentUser cUser, @PathVariable("pageNo") int pageNo, HttpSession session, Model model) {
		PageModel<Task> pageModel = taskService.listByCreator(cUser.getUserid(), pageNo);
		pageModel.setCurrentPage(pageNo);
		model.addAttribute("pageModel", pageModel);
		return "modules/task/taskMyList";
	}

	/**
	 * 创建人查看任务
	 */
	@RequestMapping(value = "/myview/{taskid}", method = RequestMethod.GET)
	public String view(CurrentUser cUser, @PathVariable("taskid") int taskid, Model model) {
		// 获取当前用户的taskid的任务、附件和结果
		List<Task> tasks = taskService.listTaskAndFile(cUser.getUserid(), taskid);

		// 任务附件文件
		List<TaskFile> fjFiles = new ArrayList<>();
		// 任务结果文件
		List<TaskFile> jgFiles = new ArrayList<>();
		// 按类型分开
		splitTaskFileByType(tasks, fjFiles, jgFiles);

		model.addAttribute("file0", fjFiles);
		model.addAttribute("file1", jgFiles);
		// 简化任务基本信息
		tasks.get(0).setTaskfile(null);
		tasks.get(0).setTaskid(0);
		model.addAttribute("mytask", tasks.get(0));
		return "modules/task/taskMyView";
	}
	
	/**
	 * 将taskfile按附件（fj）、结果（jg）分类
	 * @param tasks
	 * @param taskFiles_FJ 附件文件集合
	 * @param taskFiles_JG 结果文件集合
	 */
	private void splitTaskFileByType(List<Task> tasks, List<TaskFile> taskFiles_FJ, List<TaskFile> taskFiles_JG) {
		for (Task task : tasks) {
			TaskFile taskFile = task.getTaskfile();
			if (taskFile != null) {
				// 不向外暴露数据库中文件类型的关键字
				String fileType = task.getTaskfile().getTaskfiletype();
				taskFile.setTaskfiletype("");
				switch (fileType) {
				case TasKey.TASKFILE_FJ:
					taskFiles_FJ.add(taskFile);
					break;
				case TasKey.TASKFILE_JG:
					taskFiles_JG.add(taskFile);
					break;
				}
			}
		}
	}
	
	/**
	 * 下载任务文件（附件或结果）
	 * @throws NotFoundResourceExceptions
	 */
	@RequestMapping("/mydown/{taskfileid}")
	public ResponseEntity<byte[]> downloadTaskFile(CurrentUser cUser, @PathVariable("taskfileid") int taskfileid) throws NotFoundResourceExceptions {
		return taskService.downloadTaskFile(cUser,taskfileid);
	}
	
	/**
	 * 删除用户创建的任务（标记位置为1）
	 */
	@ResponseBody
	@RequestMapping(value = "/mydelete", method = RequestMethod.POST)
	public String delete(CurrentUser cUser, HttpServletRequest req, Model model) {
		String result = "";
		try {
			int taskid = Integer.valueOf(req.getParameter("taskid"));
			result = taskService.deleteTask(cUser.getUserid(), taskid);
		} catch (Exception e) {
			logger.error("loginname: " + cUser.getLoginname() + "; " + e.getLocalizedMessage());
			result = "删除出现异常！";
		}
		return result;
	}
	
	/*----- 创建人修改任务 -----*/
	
	/**
	 * 给创建人显示要修改的任务
	 */
	@RequestMapping(value = "/mytask/{taskid}")
	public String preUpdate(CurrentUser cUser, @PathVariable("taskid") int taskid, Model model) {
		List<Task> list = taskService.listByTaskidAndFiletype(cUser.getUserid(), taskid, TasKey.TASKFILE_FJ);
		model.addAttribute("myTask", list);
		return "modules/task/taskMyTask";
	}
	
	/**
	 * 创建人修改任务信息，不包括附件
	 */
	@RequestMapping(value = "/myupdateTask", method = RequestMethod.POST)
	public String update(CurrentUser cUser, Task taskNew, RedirectAttributes redirectAttributes) {
		String result = "success";
		// 参数有效性验证
		if (beanValidator(redirectAttributes, taskNew)) {
			// 执行修改功能
			result = taskService.updateTaskInfo(cUser.getUserid(), taskNew);
			if ("success".equals(result)) {
				addMessage(redirectAttributes, Message.SUCCESS, "修改成功！");
			} else {
				addMessage(redirectAttributes, Message.DANGER, result);
			}
		}
		return "redirect:/task/mytask/" + taskNew.getTaskid();
	}
	
	/**
	 * 创建人删除任务附件
	 */
	@ResponseBody
	@RequestMapping(value = "/mydeletefile0/{taskfileid}", produces = "text/html;charset=UTF-8")
	public String deleteFJ(CurrentUser cUser, @PathVariable("taskfileid") int taskfileid) {
		return taskService.deleteTaskFile(cUser.getUserid(), taskfileid);
	}
	
	/**
	 * 创建人更新任务附件（未测试）
	 */
	@ResponseBody
	@RequestMapping(value = "/myupdatefile0", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public Message updateFJ(CurrentUser cUser, @RequestParam("taskFile") MultipartFile[] taskFiles,
			HttpServletRequest request) {
		try {
			int taskid = Integer.valueOf(request.getParameter("taskid"));
			return updateFjOrJg(cUser, taskid, TasKey.TASKFILE_FJ, taskFiles);
		} catch (Exception e) {
			logger.error("loginname: " + cUser.getLoginname() + "; " + e.getLocalizedMessage());
			Message message = new Message("不存在该任务！", Message.DANGER);
			return message;
		}
	}
	
	/**
	 * 用于创建人更新任务附件文件，或执行人更新任务结果文件
	 * @param cUser
	 * @param taskid
	 * @param taskfiletype
	 * @param taskFiles
	 * @return
	 */
	private Message updateFjOrJg(CurrentUser cUser, int taskid, String taskfiletype, MultipartFile[] taskFiles) {
		Message message = new Message();

		// 判断文件个数、文件头、文件后缀是否正确
		String result = FileTypeUtil.checkFilesNumberAndType(taskFiles);

		switch (result) {
		case "EMPTYFILE":
			result = "文件为空！";
			message.setCtype(Message.DANGER);
			break;
		case "SUCCESS":
			try {
				TaskFileDetail fileDetail = new TaskFileDetail(cUser, taskid, taskfiletype);
				result = taskService.updateTaskFile(fileDetail, taskFiles);
				message.setCtype(Message.SUCCESS);
			} catch (Exception e) {
				logger.error("loginname: " + cUser.getLoginname() + "; " + e.getLocalizedMessage());
				result = "上传文件异常！";
				message.setCtype(Message.DANGER);
			}
			break;
		}

		message.setContent(result);
		return message;
	}

}
