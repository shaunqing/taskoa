package cn.taskoa.sys.dao;

import cn.taskoa.common.dao.CrudDao;
import cn.taskoa.common.dao.MyBatisDao;
import cn.taskoa.sys.entity.User;

@MyBatisDao
public interface UserDao extends CrudDao<User> {

	/**
	 * 根据用户名返回记录
	 * @param user
	 * @return
	 */
	public User getByLoginname(User user);

}
