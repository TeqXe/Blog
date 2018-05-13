package top.yuyg.blog.modules.master.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.yuyg.blog.modules.master.entity.TBlogtypeEntity;
import top.yuyg.blog.modules.master.service.TBlogtypeService;
import top.yuyg.blog.common.utils.PageUtils;
import top.yuyg.blog.common.utils.Query;
import top.yuyg.blog.common.utils.R;

/**
 * 
 * 
 * @author yuyugang
 * @webSite ${webSite}
 * @date 2018-05-12 23:36:10
 */
@RestController
@RequestMapping("/master/tblogtype")
public class TBlogtypeController {

	@Autowired
	private TBlogtypeService tBlogtypeService;
	
	/**
	 * LIST
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
		List<TBlogtypeEntity> tBlogtypeList = tBlogtypeService.queryList(query);
		int total = tBlogtypeService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tBlogtypeList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * INFO
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id) {
		TBlogtypeEntity tBlogtype = tBlogtypeService.queryObject(id);
		return R.ok().put("tBlogtype", tBlogtype);
	}
	
	/**
	 * SAVE
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TBlogtypeEntity tBlogtype) {
		tBlogtypeService.save(tBlogtype);
		return R.ok();
	}
	
	/**
	 * UPDATE
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TBlogtypeEntity tBlogtype) {
		tBlogtypeService.update(tBlogtype);
		return R.ok();
	}
	
	/**
	 * DELETE
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids) {
		tBlogtypeService.deleteBatch(ids);
		return R.ok();
	}
}
