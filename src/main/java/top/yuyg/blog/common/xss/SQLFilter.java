package top.yuyg.blog.common.xss;

import top.yuyg.blog.common.exception.RRException;
import top.yuyg.blog.common.utils.CamelUtils;

import org.apache.commons.lang.StringUtils;

public class SQLFilter {

	public static String sqlInject(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		str = StringUtils.replace(str, "'", "");
		str = StringUtils.replace(str, "\"", "");
		str = StringUtils.replace(str, ";", "");
		str = StringUtils.replace(str, "\\", "");
		String strLower = str.toLowerCase();
		String[] keywords = { "master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop" };
		for (String keyword : keywords) {
			if (strLower.indexOf(keyword) != -1) {
				throw new RRException("keywords error");
			}
		}
		return CamelUtils.camelToUnderline(str);
	}
}
