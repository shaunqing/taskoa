package cn.taskoa.junit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.taskoa.task.service.TaskService;

//spring的单元测试
@RunWith(SpringJUnit4ClassRunner.class)
// 上下文配置
@ContextConfiguration({ "classpath:config/spring-context.xml" })
@Transactional
public class TestService {

	@Autowired
	private TaskService taskService;

	@Test
	public void test() throws Exception {
		taskService.save();
	}
}
