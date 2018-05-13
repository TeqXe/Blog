package top.yuyg.blog.modules.master.service;

import top.yuyg.blog.modules.master.entity.TBlogtypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yuyugang
 * @webSite ${webSite}
 * @date 2018-05-12 23:36:10
 */
public interface TBlogtypeService {
	
	TBlogtypeEntity queryObject(Integer id);
	
	List<TBlogtypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TBlogtypeEntity tBlogtype);
	
	void update(TBlogtypeEntity tBlogtype);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
