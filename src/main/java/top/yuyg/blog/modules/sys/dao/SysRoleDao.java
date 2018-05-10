package top.yuyg.blog.modules.sys.dao;

import top.yuyg.blog.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
	
	List<Long> queryRoleIdList(Long createUserId);
}
