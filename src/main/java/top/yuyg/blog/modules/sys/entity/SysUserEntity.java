package top.yuyg.blog.modules.sys.entity;

import top.yuyg.blog.common.validator.group.AddGroup;
import top.yuyg.blog.common.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;

	@NotBlank(message = "username can not be empty", groups = { AddGroup.class, UpdateGroup.class })
	private String username;

	@NotBlank(message = "password can not be empty", groups = AddGroup.class)
	private String password;

	private String salt;

	@NotBlank(message = "email can not be empty", groups = { AddGroup.class, UpdateGroup.class })
	@Email(message = "email error", groups = { AddGroup.class, UpdateGroup.class })
	private String email;

	private String mobile;

	private Integer status;

	private List<Long> roleIdList;

	private Long createUserId;

	private Date createTime;

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
