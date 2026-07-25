package com.techtalkathon.security.enums;

import java.util.Set;

public enum Role {

	ROLE_ADMIN(Set.of(Permissions.USER_READ, Permissions.USER_WRITE, Permissions.USER_DELETE)),
	ROLE_USER(Set.of(Permissions.USER_READ));

	private final Set<Permissions> permissions;

	Role(Set<Permissions> permissions) {
		this.permissions = permissions;
	}

	public Set<Permissions> getPermissions() {
		return permissions;
	}

}
