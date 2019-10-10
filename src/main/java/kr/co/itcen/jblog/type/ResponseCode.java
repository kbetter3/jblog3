package kr.co.itcen.jblog.type;

import java.util.Arrays;

public enum ResponseCode {

	SUCCESS("200", "response.code.ok", "success")	// 성공
	
	, UNAUTHORIZED("401", "response.code.unauthorized", "unauthorized")		// 인증되지 않은 요청
	, FORBIDDEN("403", "response.code.forbidden", "forbidden request")		// 권한이 없는 요청
	
	// validation 관련 에러 1000
	, VALID_ERROR("1000", "response.code.valid_error", "validation error")		// 유효성 부적합
	, VALID_NULL("1001", "response.code.valid_null", "value can not be null")		// 데이터 null 에러
	
	// db 관련 에러 5000
	, DB_ERROR("5000", "response.code.db_error", "db error occurred")				// DB 에러
	
	
	// 알 수 없는 에러
	, UNKNOWN_ERROR("9000", "response.code.unknown_error", "unknown error")			// 알 수 없는 에러
	
	// user 관련 에러 10000
	, DUPLICATED_USER("10001", "response.code.duplicated_user", "duplicated user")	// 중복된 유저
	, USER_NOT_FOUND("10002", "response.code.user_not_found", "user not found")		// 유저를 찾을 수 없음
	, LOGIN_ERROR("10003", "response.code.login_error", "check your id or password")	// 로그인 실패
	
	// blog 관련 에러 20000
	, BLOG_NOT_FOUND("20000", "response.code.blog_not_found", "blog not found")					// 블로그를 찾을 수 없음
	, BLOG_ID_NULL("20001", "response.code.blog_id_null", "blog id can not be null")				// 블로그 ID가 null인 경우
	, BLOG_ID_TOO_LONG("20002", "response.code.blog_id_too_long", "blog id too long") 			// 블로그 ID의 글자수 초과
	, BLOG_TITLE_NULL("20003", "response.code.blog_title_null", "blog title can not be null")		// 블로그 TITLE이 null인 경우
	, BLOG_TITLE_TOO_LONG("20004", "response.code.blog_title_too_long", "blog title too long")	// 블로그 TITLE의 글자수 초과
	
	// category 관련 에러 30000
	, CATEGORY_TITLE_NULL("30001", "response.code.category_title_null", "category title can not be null")		// 카테고리 TITLE이 null인 경우
	, CATEGORY_TITLE_TOO_LONG("30002", "response.code.category_title_too_long", "category title too long")		// 카테고리 TITLE의 글자수 초과
	, INVALID_CATEGORY_NO("30003", "response.code.invalid_category_no", "invalid category no")					// 잘못된 카테고리 no
	, CATEGORY_CAN_NOT_BE_LESS_THAN_ONE("30004", "response.code.category_can_not_be_less_than_one", "category can not be less than one")	// 카테고리가 1개 미만일 경우
	;
	
	private String code;
	private String messageCode;
	private String message;
	
	ResponseCode(String code, String messageCode, String message) {
		this.code = code;
		this.messageCode = messageCode;
		this.message = message;
	}

	
	// search
	public static ResponseCode findByCode(String code) {
		return Arrays.stream(ResponseCode.values())
				.filter(statusCode -> statusCode.getCode().equals(code))
				.findAny()
				.orElse(null)
		;
	}
	
	
	// getter
	public String getCode() {
		return code;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public String getMessage() {
		return message;
	}
}
