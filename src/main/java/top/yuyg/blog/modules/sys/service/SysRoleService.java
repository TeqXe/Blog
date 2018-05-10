package top.yuyg.blog.modules.sys.service;

import top.yuyg.blog.modules.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

public interface SysRoleService {

	SysRoleEntity queryObject(Long roleId);

	List<SysRoleEntity> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(SysRoleEntity role);

	void update(SysRoleEntity role);

	void deleteBatch(Long[] roleIds);

	List<Long> queryRoleIdList(Long createUserId);
}
