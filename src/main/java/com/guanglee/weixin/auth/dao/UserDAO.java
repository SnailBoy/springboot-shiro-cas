package com.guanglee.weixin.auth.dao;

import com.guanglee.weixin.auth.model.User;

public interface UserDAO {
	public User findByName(String loginName);
}
