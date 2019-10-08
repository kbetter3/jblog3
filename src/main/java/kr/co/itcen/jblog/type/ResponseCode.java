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
	, USER_NOT_FOUND(10002, "response.code.user_not_found", "user not found")		// 유저를 찾을 수 없음
	, LOGIN_ERROR(10003, "response.code.login_error", "check your id or password")	// 로그인 실패
	
	// blog 관련 에러 20000
	, BLOG_NOT_FOUND(20000, "response.code.blog_not_found", "blog not found")					// 블로그를 찾을 수 없음
	, BLOG_ID_NULL(20001, "response.code.blog_id_null", "blog id can not be null")				// 블로그 ID가 null인 경우
	, BLOG_ID_TOO_LONG(20002, "response.code.blog_id_too_long", "blog id too long") 			// 블로그 ID의 글자수 초과
	, BLOG_TITLE_NULL(20003, "response.code.blog_title_null", "blog title can not be null")		// 블로그 TITLE이 null인 경우
	, BLOG_TITLE_TOO_LONG(20004, "response.code.blog_title_too_long", "blog title too long")	// 블로그 TITLE의 글자수 초과
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
