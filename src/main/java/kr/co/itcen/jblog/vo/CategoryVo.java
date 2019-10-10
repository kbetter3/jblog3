package kr.co.itcen.jblog.vo;

import java.util.Date;

import org.springframework.validation.Errors;

import kr.co.itcen.jblog.type.ResponseCode;

public class CategoryVo {
	private int no;
	private String title;
	private String description;
	private int postCnt;
	private boolean useYn;
	private Date regDate;
	private String blogId;
	
	// insert validation
	public void createValidCheck(Errors errors) {
		if (title == null || "".equals(title.trim())) {
			errors.rejectValue("title", ResponseCode.CATEGORY_TITLE_NULL.getCode(), ResponseCode.CATEGORY_TITLE_NULL.getMessage());
		} else {
			title = title.trim();
			if (title.length() > 30) {
				errors.rejectValue("title", ResponseCode.CATEGORY_TITLE_TOO_LONG.getCode(), ResponseCode.CATEGORY_TITLE_TOO_LONG.getMessage());
			}
		}
	}
	
	// delete validation
	public void deleteValidCheck(Errors errors) {
		if (no < 1) {
			errors.rejectValue("no", ResponseCode.INVALID_CATEGORY_NO.getCode(), ResponseCode.INVALID_CATEGORY_NO.getMessage());
		}
	}
	
	// getter & setter
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
	public int getPostCnt() {
		return postCnt;
	}
	public void setPostCnt(int postCnt) {
		this.postCnt = postCnt;
	}
}
