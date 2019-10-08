package kr.co.itcen.jblog.vo;

import java.util.Date;

public class CategoryVo {
	private int no;
	private String title;
	private String description;
	private boolean useYn;
	private Date regDate;
	private String blogId;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isUseYn() {
		return useYn;
	}
	public void setUseYn(boolean useYn) {
		this.useYn = useYn;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
}
