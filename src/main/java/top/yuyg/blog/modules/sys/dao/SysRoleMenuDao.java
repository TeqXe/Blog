package top.yuyg.blog.modules.sys.dao;

import top.yuyg.blog.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
	
	List<Long> queryMenuIdList(Long roleId);
}
