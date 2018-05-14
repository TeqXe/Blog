package top.yuyg.blog.modules.master.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import top.yuyg.blog.modules.master.dao.TCommentDao;
import top.yuyg.blog.modules.master.entity.TCommentEntity;
import top.yuyg.blog.modules.master.service.TCommentService;

@Service("tCommentService")
public class TCommentServiceImpl implements TCommentService {

	@Autowired
	private TCommentDao tCommentDao;
	
	@Override
	public TCommentEntity queryObject(Integer id) {
		return tCommentDao.queryObject(id);
	}
	
	@Override
	public List<TCommentEntity> queryList(Map<String, Object> map) {
		return tCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return tCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(TCommentEntity tComment) {
		tCommentDao.save(tComment);
	}
	
	@Override
	public void update(TCommentEntity tComment) {
		tCommentDao.update(tComment);
	}
	
	@Override
	public void delete(Integer id) {
		tCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids) {
		tCommentDao.deleteBatch(ids);
	}

	@Override
	public void verify(@Param("id") Integer id,@Param("state") Integer state) {
		tCommentDao.verify(id,state);
	}

	@Override
	public void verifyPassBatch(Integer[] ids) {
		tCommentDao.verifyPassBatch(ids);
	}

	@Override
	public void verifyDenyBatch(Integer[] ids) {
		tCommentDao.verifyDenyBatch(ids);
	}
}
