package top.yuyg.blog.modules.master.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author yuyugang
 * @webSite ${webSite}
 * @date 2018-05-14 23:16:58
 */
public class TBlogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;

	// 
	private String title;

	// 
	private String summary;

	// 
	private Date releasedate;

	// 
	private Integer clickhit;

	// 
	private Integer replyhit;

	// 
	private String content;

	// 
	private Integer typeid;

	// 
	private String keyword;

	/**
	 * SET：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * GET：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * SET：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * GET：
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * SET：
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/**
	 * GET：
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * SET：
	 */
	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}
	
	/**
	 * GET：
	 */
	public Date getReleasedate() {
		return releasedate;
	}

	/**
	 * SET：
	 */
	public void setClickhit(Integer clickhit) {
		this.clickhit = clickhit;
	}
	
	/**
	 * GET：
	 */
	public Integer getClickhit() {
		return clickhit;
	}

	/**
	 * SET：
	 */
	public void setReplyhit(Integer replyhit) {
		this.replyhit = replyhit;
	}
	
	/**
	 * GET：
	 */
	public Integer getReplyhit() {
		return replyhit;
	}

	/**
	 * SET：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * GET：
	 */
	public String getContent() {
		return content;
	}

	/**
	 * SET：
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	
	/**
	 * GET：
	 */
	public Integer getTypeid() {
		return typeid;
	}

	/**
	 * SET：
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * GET：
	 */
	public String getKeyword() {
		return keyword;
	}
}
