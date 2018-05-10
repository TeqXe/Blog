package top.yuyg.blog.modules.sys.controller;

import top.yuyg.blog.common.annotation.SysLog;
import top.yuyg.blog.common.exception.RRException;
import top.yuyg.blog.common.utils.Constant.MenuType;
import top.yuyg.blog.common.utils.R;
import top.yuyg.blog.modules.sys.entity.SysMenuEntity;
import top.yuyg.blog.modules.sys.service.ShiroService;
import top.yuyg.blog.modules.sys.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private ShiroService shiroService;

	@RequestMapping("/nav")
	public R nav() {
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
		Set<String> permissions = shiroService.getUserPermissions(getUserId());
		return R.ok().put("menuList", menuList).put("permissions", permissions);
	}

	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public List<SysMenuEntity> list() {
		List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<String, Object>());
		return menuList;
	}

	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public R select() {
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("level1menu");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		return R.ok().put("menuList", menuList);
	}

	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public R info(@PathVariable("menuId") Long menuId) {
		SysMenuEntity menu = sysMenuService.queryObject(menuId);
		return R.ok().put("menu", menu);
	}

	@SysLog("saveSysMenuEntity")
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public R save(@RequestBody SysMenuEntity menu) {
		verifyForm(menu);
		sysMenuService.save(menu);
		return R.ok();
	}

	@SysLog("updateSysMenuEntity")
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public R update(@RequestBody SysMenuEntity menu) {
		verifyForm(menu);
		sysMenuService.update(menu);
		return R.ok();
	}

	@SysLog("deleteSysMenuEntity")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public R delete(long menuId) {
		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
		if (menuList.size() > 0) {
			return R.error("请先删除子菜单或按钮");
		}
		sysMenuService.deleteBatch(new Long[] { menuId });
		return R.ok();
	}

	private void verifyForm(SysMenuEntity menu) {
		if (StringUtils.isBlank(menu.getName())) {
			throw new RRException("name cannot be empty");
		}
		if (menu.getParentId() == null) {
			throw new RRException("Parentmenu cannot be empty");
		}
		if (menu.getType() == MenuType.MENU.getValue()) {
			if (StringUtils.isBlank(menu.getUrl())) {
				throw new RRException("URL cannot be empty");
			}
		}
		int parentType = MenuType.CATALOG.getValue();
		if (menu.getParentId() != 0) {
			SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentId());
			parentType = parentMenu.getType();
		}
		if (menu.getType() == MenuType.CATALOG.getValue() || menu.getType() == MenuType.MENU.getValue()) {
			if (parentType != MenuType.CATALOG.getValue()) {
				throw new RRException("is not CATALOG");
			}
			return;
		}
		if (menu.getType() == MenuType.BUTTON.getValue()) {
			if (parentType != MenuType.MENU.getValue()) {
				throw new RRException("is not BUTTON");
			}
			return;
		}
	}
}
