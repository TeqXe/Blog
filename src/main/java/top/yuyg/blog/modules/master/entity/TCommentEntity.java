package top.yuyg.blog.modules.master.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author yuyugang
 * @webSite ${webSite}
 * @date 2018-05-13 00:22:07
 */
public class TCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;

	// 
	private String userip;

	// 
	private Integer blogid;

	// 
	private String content;

	// 
	private Date commentdate;

	// 
	private Integer state;

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
	public void setUserip(String userip) {
		this.userip = userip;
	}
	
	/**
	 * GET：
	 */
	public String getUserip() {
		return userip;
	}

	/**
	 * SET：
	 */
	public void setBlogid(Integer blogid) {
		this.blogid = blogid;
	}
	
	/**
	 * GET：
	 */
	public Integer getBlogid() {
		return blogid;
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
	public void setCommentdate(Date commentdate) {
		this.commentdate = commentdate;
	}
	
	/**
	 * GET：
	 */
	public Date getCommentdate() {
		return commentdate;
	}

	/**
	 * SET：
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * GET：
	 */
	public Integer getState() {
		return state;
	}
}
