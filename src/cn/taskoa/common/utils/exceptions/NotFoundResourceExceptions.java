package cn.taskoa.common.utils.exceptions;

/**
 * 用于控制层捕获，输出404
 * @author LIQing
 *
 */
public class NotFoundResourceExceptions extends Exception {

	private static final long serialVersionUID = 1L;

	public NotFoundResourceExceptions() {

	}

	public NotFoundResourceExceptions(String msg) {
		super(msg);
	}

}
