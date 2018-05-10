package top.yuyg.blog.modules.sys.service;

import java.util.List;

public interface SysRoleMenuService {

	void saveOrUpdate(Long roleId, List<Long> menuIdList);

	List<Long> queryMenuIdList(Long roleId);
}
