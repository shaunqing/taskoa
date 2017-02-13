package cn.taskoa.common.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.taskoa.common.filter.CurrentUser;

public class LoginInterceptor implements HandlerInterceptor {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView modelAndView)
			throws Exception {
		if (modelAndView != null) {
			logger.info("ViewName: " + modelAndView.getViewName());
		}
	}

	// 拦截未登录的用户
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg0) throws Exception {
		return isLogin(request, response);
	}

	/**
	 * 判断用户是否登录
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private boolean isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CurrentUser cUser = (CurrentUser) request.getSession().getAttribute("CURRENT_USER");
		if (cUser == null) {
			logger.debug("user not login");
			response.sendRedirect("/taskoa/sys");
			return false;
		}
		return true;
	}

}
