package com.guanglee.weixin.auth.model;

import java.util.List;

public class Role {
	private List<String> permissionsName;

	public List<String> getPermissionsName() {
		return permissionsName;
	}

	public void setPermissionsName(List<String> permissionsName) {
		this.permissionsName = permissionsName;
	}
}
