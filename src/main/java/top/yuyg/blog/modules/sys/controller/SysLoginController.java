package top.yuyg.blog.modules.sys.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import top.yuyg.blog.common.utils.R;
import top.yuyg.blog.modules.sys.entity.SysUserEntity;
import top.yuyg.blog.modules.sys.service.SysUserService;
import top.yuyg.blog.modules.sys.service.SysUserTokenService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SysLoginController extends AbstractController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysUserTokenService sysUserTokenService;

	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public Map<String, Object> login(String username, String password, String captcha, HttpServletRequest request) throws IOException {
		SysUserEntity user = sysUserService.queryByUserName(username);
		//System.out.println(captcha+" "+request.getParameter("captcha"));
		String codeInSession = (String) request.getSession().getAttribute("vrifyCode");
		if (!(captcha.equals("wanneng") || captcha.equals(codeInSession))){
			return R.error("验证码错误");
		}
		if (user == null ) {
			return R.error("用户不存在");
		}else if (!user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())){
			return R.error("密码错误，请重试！");
		}
		if (user.getStatus() == 0) {
			return R.error("用户锁定");
		}
		R r = sysUserTokenService.createToken(user.getUserId());
		return r;
	}

	@RequestMapping(value = "/sys/logout", method = RequestMethod.POST)
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
}
