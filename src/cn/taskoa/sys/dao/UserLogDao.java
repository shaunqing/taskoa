package cn.taskoa.sys.dao;

import cn.taskoa.common.dao.BaseDao;
import cn.taskoa.common.dao.MyBatisDao;
import cn.taskoa.sys.entity.UserLog;

@MyBatisDao
public interface UserLogDao extends BaseDao {

	/**
	 * 保存登录记录
	 * @param userLog
	 * @return
	 * @throws Exception
	 */
	public int insert(UserLog userLog) throws Exception;
}
