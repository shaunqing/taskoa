package cn.taskoa.sys.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.taskoa.common.web.BaseController;
import cn.taskoa.task.service.TaskService;

@Controller
@RequestMapping("/a")
public class AdminController extends BaseController {

	@Autowired
	private TaskService taskService;

	/**
	 * 测试
	 */
	@RequestMapping("/loginout")
	public String loginout(HttpSession session) {
		return "redirect:sys";
	}
}
