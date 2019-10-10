package kr.co.itcen.jblog.vo;

import java.util.Date;

import org.springframework.validation.Errors;

import kr.co.itcen.jblog.type.ResponseCode;

public class PostVo {
	private int no;
	private String title;
	private String contents;
	private Date regDate;
	private boolean useYn;
	private int categoryNo;
	
	// createValidCheck
	public void createValidCheck(Errors errors) {
		if (title == null || "".equals(title)) {
			errors.rejectValue("title", ResponseCode.POST_TITLE_NULL.getCode(), ResponseCode.POST_TITLE_NULL.getMessage());
		} else if (title.length() > 60) {
			errors.rejectValue("title", ResponseCode.POST_TITLE_TOO_LONG.getCode(), ResponseCode.POST_TITLE_TOO_LONG.getMessage());
		}
		
		if (contents == null || "".equals(contents)) {
			errors.rejectValue("contents", ResponseCode.POST_CONTENTS_NULL.getCode(), ResponseCode.POST_CONTENTS_NULL.getMessage());
		}
		
		if (categoryNo < 1) {
			errors.rejectValue("categoryNo", ResponseCode.INVALID_CATEGORY_NO.getCode(), ResponseCode.INVALID_CATEGORY_NO.getMessage());
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public boolean isUseYn() {
		return useYn;
	}
	public void setUseYn(boolean useYn) {
		this.useYn = useYn;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
}
