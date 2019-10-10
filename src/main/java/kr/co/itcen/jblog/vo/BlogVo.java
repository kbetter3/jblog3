package kr.co.itcen.jblog.vo;

import org.springframework.validation.Errors;

import kr.co.itcen.jblog.type.ResponseCode;

public class BlogVo {
	private String id;
	private String title;
	private String logo;
	
	// 블로그 수정 유효성 검사
	public void updateValidCheck(Errors errors) {		
		if (title == null || "".equals(title.trim())) {
			errors.rejectValue("title", ResponseCode.BLOG_TITLE_NULL.getCode(), ResponseCode.BLOG_TITLE_NULL.getMessage());
		} else if (title.length() > 40) {
			errors.rejectValue("title", ResponseCode.BLOG_TITLE_TOO_LONG.getCode(), ResponseCode.BLOG_TITLE_TOO_LONG.getMessage());
		}
	}
	
	// getter & setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
}
