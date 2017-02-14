package cn.taskoa.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.taskoa.common.utils.Message;
import cn.taskoa.common.web.BaseController;

@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController {

	/**
	 * 显示403错误界面
	 */
	@RequestMapping("/403")
	public String show(Model model) {
		Message message = new Message("无权操作", Message.DANGER);
		model.addAttribute("message", message);
		return "modules/error/403";
	}
}
