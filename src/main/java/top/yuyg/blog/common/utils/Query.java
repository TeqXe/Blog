package top.yuyg.blog.common.utils;

import top.yuyg.blog.common.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	private int page;

	private int limit;

	public Query(Map<String, Object> params) {
		this.putAll(params);

		this.page = StringUtils.isNotBlank((String) params.get("page")) ? Integer.parseInt(params.get("page").toString()) : 1;
		this.limit = StringUtils.isNotBlank((String) params.get("limit")) ? Integer.parseInt(params.get("limit").toString()) : 999;
		this.put("offset", (page - 1) * limit);
		this.put("page", page);
		this.put("limit", limit);
		String sidx = (String) params.get("sidx");
		String order = (String) params.get("order");
		if (StringUtils.isNotBlank(sidx)) {
			this.put("sidx", SQLFilter.sqlInject(sidx));
		}
		if (StringUtils.isNotBlank(order)) {
			this.put("order", SQLFilter.sqlInject(order));
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
