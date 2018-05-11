package top.yuyg.blog.modules.sys.oauth;

import top.yuyg.blog.modules.sys.entity.SysUserEntity;
import top.yuyg.blog.modules.sys.entity.SysUserTokenEntity;
import top.yuyg.blog.modules.sys.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OAuthRealm extends AuthorizingRealm {
	
	@Autowired
	private ShiroService shiroService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof OAuthToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String accessToken = (String) token.getPrincipal();
		SysUserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
		if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
			throw new IncorrectCredentialsException("token Expire");
		}
		SysUserEntity user = shiroService.queryUser(tokenEntity.getUserId());
		if (user.getStatus() == 0) {
			throw new LockedAccountException("LockedAccount");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
		return info;
	}
}
