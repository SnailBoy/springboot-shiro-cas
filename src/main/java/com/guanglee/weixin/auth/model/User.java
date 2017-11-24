package com.guanglee.weixin.auth.model;

import java.util.List;
import java.util.Set;

public class User {
	private Set<String> rolesName;
	private List<Role> roleList;

	public Set<String> getRolesName() {
		return rolesName;
	}

	public void setRolesName(Set<String> rolesName) {
		this.rolesName = rolesName;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}
