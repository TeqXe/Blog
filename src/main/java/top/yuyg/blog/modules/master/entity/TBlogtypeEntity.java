package top.yuyg.blog.modules.master.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author yuyugang
 * @webSite ${webSite}
 * @date 2018-05-12 23:36:10
 */
public class TBlogtypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;

	// 
	private String typename;

	// 
	private Integer orderno;

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
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	/**
	 * GET：
	 */
	public String getTypename() {
		return typename;
	}

	/**
	 * SET：
	 */
	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}
	
	/**
	 * GET：
	 */
	public Integer getOrderno() {
		return orderno;
	}
}
