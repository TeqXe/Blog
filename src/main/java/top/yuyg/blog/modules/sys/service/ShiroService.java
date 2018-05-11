package top.yuyg.blog.modules.sys.service;

import top.yuyg.blog.modules.sys.entity.SysUserEntity;
import top.yuyg.blog.modules.sys.entity.SysUserTokenEntity;

import java.util.Set;

public interface ShiroService {

	SysUserTokenEntity queryByToken(String token);

	SysUserEntity queryUser(Long userId);
}
