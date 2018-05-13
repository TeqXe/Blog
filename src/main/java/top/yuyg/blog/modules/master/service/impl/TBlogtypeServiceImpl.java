package top.yuyg.blog.modules.master.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import top.yuyg.blog.modules.master.dao.TBlogtypeDao;
import top.yuyg.blog.modules.master.entity.TBlogtypeEntity;
import top.yuyg.blog.modules.master.service.TBlogtypeService;

@Service("tBlogtypeService")
public class TBlogtypeServiceImpl implements TBlogtypeService {

	@Autowired
	private TBlogtypeDao tBlogtypeDao;
	
	@Override
	public TBlogtypeEntity queryObject(Integer id) {
		return tBlogtypeDao.queryObject(id);
	}
	
	@Override
	public List<TBlogtypeEntity> queryList(Map<String, Object> map) {
		return tBlogtypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return tBlogtypeDao.queryTotal(map);
	}
	
	@Override
	public void save(TBlogtypeEntity tBlogtype) {
		tBlogtypeDao.save(tBlogtype);
	}
	
	@Override
	public void update(TBlogtypeEntity tBlogtype) {
		tBlogtypeDao.update(tBlogtype);
	}
	
	@Override
	public void delete(Integer id) {
		tBlogtypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids) {
		tBlogtypeDao.deleteBatch(ids);
	}
}
