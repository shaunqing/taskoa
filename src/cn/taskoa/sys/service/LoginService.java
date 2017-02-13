package cn.taskoa.sys.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.taskoa.common.filter.CurrentUser;
import cn.taskoa.common.service.BaseService;
import cn.taskoa.common.utils.IPv4Util;
import cn.taskoa.sys.dao.UserDao;
import cn.taskoa.sys.dao.UserLogDao;
import cn.taskoa.sys.entity.User;
import cn.taskoa.sys.entity.UserLog;

@Service
@Transactional(readOnly = true)
public class LoginService extends BaseService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserLogDao logDao;

	public boolean login(CurrentUser cUser) {
		User user = new User();
		user.setLoginname(cUser.getLoginname());
		// 按用户名查询的结果
		user = userDao.getByLoginname(user);
		// 用户存在，则记录到CurrentUser中
		if (user != null && cUser.getPassword().equals(user.getPassword())) {
			cUser.setPassword("");
			cUser.setUserid(user.getUserid());
			cUser.setUsername(user.getUsername());

			return true;
		} else {
			return false;
		}
	}

	@Transactional(readOnly = false)
	public void saveLoginUser(String loginname, char result, HttpServletRequest request) {
		UserLog userLog = new UserLog();
		userLog.setLoginname(loginname);
		userLog.setLoginip(IPv4Util.getIpAddress(request));
		userLog.setLogin_result(result);

		try {
			logDao.insert(userLog);
		} catch (Exception e) {
			logger.error("保存登录信息错误：登录名" + userLog.getLoginname() + "--" + e.getLocalizedMessage());
		}
	}

}
