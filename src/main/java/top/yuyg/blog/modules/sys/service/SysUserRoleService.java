package top.yuyg.blog.modules.sys.service;

import java.util.List;

public interface SysUserRoleService {

	void saveOrUpdate(Long userId, List<Long> roleIdList);

	List<Long> queryRoleIdList(Long userId);

	void delete(Long userId);
}
