package top.yuyg.blog.modules.master.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.yuyg.blog.modules.master.entity.TBlogEntity;
import top.yuyg.blog.modules.master.service.TBlogService;
import top.yuyg.blog.common.utils.PageUtils;
import top.yuyg.blog.common.utils.Query;
import top.yuyg.blog.common.utils.R;

/**
 * 
 * 
 * @author yuyugang
 * @webSite ${webSite}
 * @date 2018-05-14 23:16:58
 */
@RestController
@RequestMapping("/master/tblog")
public class TBlogController {

	@Autowired
	private TBlogService tBlogService;
	
	/**
	 * LIST
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
		List<TBlogEntity> tBlogList = tBlogService.queryList(query);
		int total = tBlogService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tBlogList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * INFO
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id) {
		TBlogEntity tBlog = tBlogService.queryObject(id);
		return R.ok().put("tBlog", tBlog);
	}
	
	/**
	 * SAVE
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TBlogEntity tBlog) {
		tBlogService.save(tBlog);
		return R.ok();
	}
	
	/**
	 * UPDATE
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TBlogEntity tBlog) {
		tBlogService.update(tBlog);
		return R.ok();
	}
	
	/**
	 * DELETE
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids) {
		tBlogService.deleteBatch(ids);
		return R.ok();
	}
}
