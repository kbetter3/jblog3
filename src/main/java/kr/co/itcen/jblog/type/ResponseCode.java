package kr.co.itcen.jblog.type;

public enum ResponseCode {

	SUCCESS(200, "response.code.ok", "success")	// 성공
	
	// validation 관련 에러 1000
	, VALID_ERROR(1000, "response.code.valid_error", "validation error")		// 유효성 검사 에러
	, VALID_NULL(1001, "response.code.valid_null", "value can not be null")		// 데이터 null 에러
	
	// db 관련 에러 5000
	, DB_ERROR(5000, "response.code.db_error", "db error occurred")				// DB 에러
	
	// user 관련 에러 10000
	, DUPLICATED_USER(10001, "response.code.duplicated_user", "duplicated user")	// 중복된 유저
	;
	
	private int code;
	private String messageCode;
	private String message;
	
	ResponseCode(int code, String messageCode, String message) {
		this.code = code;
		this.messageCode = messageCode;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public String getMessage() {
		return message;
	}
}
