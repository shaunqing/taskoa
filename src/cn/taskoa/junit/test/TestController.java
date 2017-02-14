package cn.taskoa.junit.test;

import javax.servlet.http.HttpServletRequest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.taskoa.common.utils.Message;
import cn.taskoa.common.web.BaseController;
import cn.taskoa.task.entity.Task;
import cn.taskoa.task.service.TaskService;

//spring的单元测试
@RunWith(SpringJUnit4ClassRunner.class)
//上下文配置
@ContextConfiguration({ "classpath:config/spring-context.xml" })
@Controller
@RequestMapping(value = "/")
public class TestController extends BaseController {

	@Autowired
	private TaskService taskService;

	@RequestMapping("/test")
	public String show() {
		return "test/index";
	}
	
	@RequestMapping("/demo")
	public String demo() {
		return "test/demo";
	}
	
	@RequestMapping("/de")
	public String deo() {
		return "test/decorator";
	}

	@RequestMapping("/list")
	public String list(TestEntity t, Model model) {
		if(beanValidator(model, t)) {
			System.out.println(t.getId());
			System.out.println(t.getName());

			Message m = new Message();
			m.setCtype("success");
			m.setContent("dddddd");
			model.addAttribute("message", m);
			return "test/index";
		} else {
			return "task/index";
		}
		
		
	}
	
	@RequestMapping("/list2")
	public void havij(Model model,HttpServletRequest req) {
		Task taskNew = new Task();
		// 获取修改信息
		taskNew.setTaskname("'or 1 = 1'");
		boolean a = beanValidator(model, taskNew);
		System.out.println(a);
		
	}
}
