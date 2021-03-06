package com.rui.pro1.modules.sys.exception;

import com.rui.pro1.common.exception.SysRuntimeException;

/**
 * 用户已存在
 * 
 * @author ruiliang
 * @qq 1067165280
 */
public class UserExistException extends SysRuntimeException {
	private static final long serialVersionUID = 1L;

	public UserExistException(String message) {
		super(message);
	}

}
