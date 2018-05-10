package top.yuyg.blog.modules.sys.service;

import top.yuyg.blog.modules.sys.entity.SysUserTokenEntity;
import top.yuyg.blog.common.utils.R;

public interface SysUserTokenService {

	SysUserTokenEntity queryByUserId(Long userId);

	void save(SysUserTokenEntity token);

	void update(SysUserTokenEntity token);

	R createToken(long userId);

	void logout(long userId);
}
