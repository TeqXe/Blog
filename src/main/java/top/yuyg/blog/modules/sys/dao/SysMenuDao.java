package top.yuyg.blog.modules.sys.dao;

import top.yuyg.blog.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
	
	List<SysMenuEntity> queryListParentId(Long parentId);
	
	List<SysMenuEntity> queryNotButtonList();
	
}
