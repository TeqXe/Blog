package top.yuyg.blog.modules.master.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.yuyg.blog.modules.master.entity.TCommentEntity;
import top.yuyg.blog.modules.master.service.TCommentService;
import top.yuyg.blog.common.utils.PageUtils;
import top.yuyg.blog.common.utils.Query;
import top.yuyg.blog.common.utils.R;

/**
 * 
 * 
 * @author yuyugang
 * @webSite ${webSite}
 * @date 2018-05-13 00:22:07
 */
@RestController
@RequestMapping("/master/tcomment")
public class TCommentController {

	@Autowired
	private TCommentService tCommentService;
	
	/**
	 * LIST
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
		List<TCommentEntity> tCommentList = tCommentService.queryList(query);
		int total = tCommentService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tCommentList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * INFO
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id) {
		TCommentEntity tComment = tCommentService.queryObject(id);
		return R.ok().put("tComment", tComment);
	}
	
	/**
	 * SAVE
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TCommentEntity tComment) {
		tCommentService.save(tComment);
		return R.ok();
	}
	
	/**
	 * UPDATE
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TCommentEntity tComment) {
		tCommentService.update(tComment);
		return R.ok();
	}
	
	/**
	 * DELETE
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids) {
		tCommentService.deleteBatch(ids);
		return R.ok();
	}

	/**
	 * verify
	 */
	@RequestMapping("/verify")
	public R verify(@RequestBody Integer[] data) {
		int state = data[0];
		int id = data[1];
		tCommentService.verify(id,state);
		return R.ok();
	}
}
