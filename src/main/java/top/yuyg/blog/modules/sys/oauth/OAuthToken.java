package top.yuyg.blog.modules.sys.oauth;

import org.apache.shiro.authc.AuthenticationToken;

public class OAuthToken implements AuthenticationToken {
	private static final long serialVersionUID = 1L;
	
	private String token;

	public OAuthToken(String token) {
		this.token = token;
	}

	@Override
	public String getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}
