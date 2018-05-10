package top.yuyg.blog.common.aspect;

import com.google.gson.Gson;
import top.yuyg.blog.common.annotation.SysLog;
import top.yuyg.blog.common.utils.HttpContextUtils;
import top.yuyg.blog.common.utils.IPUtils;
import top.yuyg.blog.modules.sys.entity.SysLogEntity;
import top.yuyg.blog.modules.sys.entity.SysUserEntity;
import top.yuyg.blog.modules.sys.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLogAspect {

	@Autowired
	private SysLogService sysLogService;

	@Pointcut("@annotation(top.yuyg.blog.common.annotation.SysLog)")
	public void logPointCut() {

	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		Object result = point.proceed();
		long time = System.currentTimeMillis() - beginTime;
		saveSysLog(point, time);
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if (syslog != null) {
			sysLog.setOperation(syslog.value());
		}
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		Object[] args = joinPoint.getArgs();
		try {
			String params = new Gson().toJson(args[0]);
			sysLog.setParams(params);
		} catch (Exception e) {

		}
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		sysLog.setIp(IPUtils.getIpAddr(request));
		String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
		sysLog.setUsername(username);
		sysLog.setTime(time);
		sysLog.setCreateDate(new Date());
		sysLogService.save(sysLog);
	}
}
