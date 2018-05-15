package top.yuyg.blog.modules.master.service;

import top.yuyg.blog.modules.master.entity.TBlogEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yuyugang
 * @webSite ${webSite}
 * @date 2018-05-14 23:16:58
 */
public interface TBlogService {
	
	TBlogEntity queryObject(Integer id);
	
	List<TBlogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TBlogEntity tBlog);
	
	void update(TBlogEntity tBlog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
