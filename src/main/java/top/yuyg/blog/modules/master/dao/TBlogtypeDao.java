package top.yuyg.blog.modules.master.dao;

import top.yuyg.blog.modules.master.entity.TBlogtypeEntity;
import top.yuyg.blog.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yuyugang
 * @webSite ${webSite}
 * @date 2018-05-12 23:36:10
 */
@Mapper
public interface TBlogtypeDao extends BaseDao<TBlogtypeEntity> {

    List<TBlogtypeEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(TBlogtypeEntity tBlogtype);

    int update(TBlogtypeEntity tBlogtype);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
