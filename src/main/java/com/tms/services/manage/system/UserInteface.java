package com.tms.services.manage.system;

import com.tms.core.Services;
import com.tms.core.system.bean.User;

public interface UserInteface extends Services<User> {
	/**
	 * @param e
	 * @return
	 */
	public User login(User e);
}
