package cn.taskoa.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.taskoa.common.filter.CurrentUser;
import cn.taskoa.common.utils.Message;
import cn.taskoa.common.web.BaseController;
import cn.taskoa.sys.service.LoginService;

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;

	/**
	 * 显示登录界面
	 */
	@RequestMapping("/sys")
	public String show() {
		return "modules/sys/sysLogin";
	}

	/**
	 * 判断登录信息
	 */
	@RequestMapping("/login")
	public String login(CurrentUser cUser, Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttributes) {
		if (loginService.login(cUser)) {
			// 登录成功，保存到session
			session.setAttribute("CURRENT_USER", cUser);
			// 保存登录信息
			loginService.saveLoginUser(cUser.getLoginname(), '1', request);
			return "redirect:task/mylist/1";
		} else {
			// 保存登录信息
			loginService.saveLoginUser(cUser.getLoginname(), '0', request);
			addMessage(redirectAttributes, Message.DANGER, "用户不存在或密码错误！");
			return "redirect:sys";
		}
	}

	/**
	 * 退出
	 */
	@RequestMapping("/loginout")
	public String loginout(HttpSession session) {
		session.setAttribute("CURRENT_USER", null);
		return "redirect:sys";
	}
}
