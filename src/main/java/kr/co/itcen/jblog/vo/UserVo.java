package kr.co.itcen.jblog.vo;

import java.util.Date;

import org.springframework.validation.Errors;

import kr.co.itcen.jblog.type.ResponseCode;

public class UserVo {
	private String id;
	private String name;
	private String passwd;
	private Date regDate;

	// join validation
	public void joinValidCheck(Errors errors) {
		loginValidCheck(errors);

		// name null check
		if (name == null || "".equals(name.trim())) {
			errors.rejectValue("name", ResponseCode.VALID_NULL.getCode(), ResponseCode.VALID_NULL.getMessage());
		}
		// name 영문,한글, 글자수 check
		String nameRegex = "^[a-zA-Z가-힣]{2,20}$";
		if (!name.matches(nameRegex)) {
			errors.rejectValue("name", ResponseCode.VALID_ERROR.getCode(), ResponseCode.VALID_ERROR.getMessage());
		}
	}

	// login validation
	public void loginValidCheck(Errors errors) {
		// 아이디 null check
		if (id == null || "".equals(id.trim())) {
			errors.rejectValue("id", ResponseCode.VALID_NULL.getCode(), ResponseCode.VALID_NULL.getMessage());
		}

		// id 영숫자, 글자수 check
		String idRegex = "^[a-zA-Z0-9]{4,20}$";
		if (!id.matches(idRegex)) {
			errors.rejectValue("id", ResponseCode.VALID_ERROR.getCode(), ResponseCode.VALID_ERROR.getMessage());
		}

		// pw null check
		if (passwd == null || "".equals(passwd)) {
			errors.rejectValue("passwd", ResponseCode.VALID_NULL.getCode(), ResponseCode.VALID_NULL.getMessage());
		}
	}

	
	// getter & setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
