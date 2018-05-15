package top.yuyg.blog.modules.master.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import top.yuyg.blog.modules.master.dao.TBlogDao;
import top.yuyg.blog.modules.master.entity.TBlogEntity;
import top.yuyg.blog.modules.master.service.TBlogService;

@Service("tBlogService")
public class TBlogServiceImpl implements TBlogService {

	@Autowired
	private TBlogDao tBlogDao;
	
	@Override
	public TBlogEntity queryObject(Integer id) {
		return tBlogDao.queryObject(id);
	}
	
	@Override
	public List<TBlogEntity> queryList(Map<String, Object> map) {
		return tBlogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return tBlogDao.queryTotal(map);
	}
	
	@Override
	public void save(TBlogEntity tBlog) {
		tBlogDao.save(tBlog);
	}
	
	@Override
	public void update(TBlogEntity tBlog) {
		tBlogDao.update(tBlog);
	}
	
	@Override
	public void delete(Integer id) {
		tBlogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids) {
		tBlogDao.deleteBatch(ids);
	}
}
