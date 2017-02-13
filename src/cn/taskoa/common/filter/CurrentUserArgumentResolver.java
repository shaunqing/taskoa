package cn.taskoa.common.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * 获取session中的CURRENT_USER
 * 
 * @author LIQing
 *
 */
public class CurrentUserArgumentResolver implements WebArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter arg0, NativeWebRequest arg1) throws Exception {
		if (arg0.getParameterType() != null && arg0.getParameterType().equals(CurrentUser.class)) {
			// 判断controller方法参数有没有写当前用户，如果有，这里返回即可，通常我们从session里面取出来
			HttpServletRequest request = arg1.getNativeRequest(HttpServletRequest.class);
			Object currentUser = request.getSession().getAttribute("CURRENT_USER");
			if (currentUser == null) {
				return UNRESOLVED;
			}
			return currentUser;
		}
		return UNRESOLVED;
	}

}
