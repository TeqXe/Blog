package top.yuyg.blog.modules.sys.controller;

import top.yuyg.blog.common.annotation.SysLog;
import top.yuyg.blog.modules.sys.entity.SysRoleEntity;
import top.yuyg.blog.modules.sys.service.SysRoleMenuService;
import top.yuyg.blog.modules.sys.service.SysRoleService;
import top.yuyg.blog.common.utils.Constant;
import top.yuyg.blog.common.utils.PageUtils;
import top.yuyg.blog.common.utils.Query;
import top.yuyg.blog.common.utils.R;
import top.yuyg.blog.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	@RequestMapping("/list")
	@RequiresPermissions("sys:role:list")
	public R list(@RequestParam Map<String, Object> params) {
		if (getUserId() != Constant.SUPER_ADMIN) {
			params.put("createUserId", getUserId());
		}
		Query query = new Query(params);
		List<SysRoleEntity> list = sysRoleService.queryList(query);
		int total = sysRoleService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}

	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public R select() {
		Map<String, Object> map = new HashMap<>();
		if (getUserId() != Constant.SUPER_ADMIN) {
			map.put("createUserId", getUserId());
		}
		List<SysRoleEntity> list = sysRoleService.queryList(map);
		return R.ok().put("list", list);
	}

	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public R info(@PathVariable("roleId") Long roleId) {
		SysRoleEntity role = sysRoleService.queryObject(roleId);
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		return R.ok().put("role", role);
	}

	@SysLog("saveSysRoleEntity")
	@RequestMapping("/save")
	@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleEntity role) {
		ValidatorUtils.validateEntity(role);
		role.setCreateUserId(getUserId());
		sysRoleService.save(role);
		return R.ok();
	}

	@SysLog("updateSysRoleEntity")
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public R update(@RequestBody SysRoleEntity role) {
		ValidatorUtils.validateEntity(role);
		role.setCreateUserId(getUserId());
		sysRoleService.update(role);
		return R.ok();
	}

	@SysLog("deleteSysRoleEntity")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds) {
		sysRoleService.deleteBatch(roleIds);
		return R.ok();
	}
}
