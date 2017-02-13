package cn.taskoa.junit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.taskoa.task.dao.TaskFileDao;

//spring的单元测试
@RunWith(SpringJUnit4ClassRunner.class)
// 上下文配置
@ContextConfiguration({ "classpath:config/spring-context.xml" })
public class TestDao {


	@Autowired
	private TaskFileDao fDao;
	
	@Test
	public void test() throws Exception {
		String s = fDao.getByTaskFileIdAndCrearot(59, 1);
		System.out.println(s);
	}
}
