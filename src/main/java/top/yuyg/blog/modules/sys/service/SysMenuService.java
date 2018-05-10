package top.yuyg.blog.modules.sys.service;

import top.yuyg.blog.modules.sys.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

public interface SysMenuService {

	List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

	List<SysMenuEntity> queryListParentId(Long parentId);

	List<SysMenuEntity> queryNotButtonList();

	List<SysMenuEntity> getUserMenuList(Long userId);

	SysMenuEntity queryObject(Long menuId);

	List<SysMenuEntity> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(SysMenuEntity menu);

	void update(SysMenuEntity menu);

	void deleteBatch(Long[] menuIds);

	List<SysMenuEntity> queryUserList(Long userId);
}
