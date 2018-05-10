package top.yuyg.blog.modules.sys.entity;

import java.io.Serializable;

public class SysUserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long userId;

	private Long roleId;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRoleId() {
		return roleId;
	}

}
