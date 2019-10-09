package kr.co.itcen.jblog.result;

import java.util.List;

import kr.co.itcen.jblog.type.ResponseCode;

public class ApiResult<T> {
	private String code;
	private boolean status;
	private String messageCode;
	private String message;

	private T data;
	private List<T> datas;

	public ApiResult() {
		this(ResponseCode.SUCCESS.getCode(), true, ResponseCode.SUCCESS.getMessageCode(), ResponseCode.SUCCESS.getMessage());
	}

	public ApiResult(ResponseCode responseCode) {
		this(responseCode.getCode(), false, responseCode.getMessageCode(), responseCode.getMessage());
	}

	public ApiResult(T data) {
		this();
		this.data = data;
	}

	public ApiResult(List<T> datas) {
		this();
		this.datas = datas;
	}

	public ApiResult(String code, boolean status, String messageCode, String message) {
		this.code = code;
		this.status = status;
		this.messageCode = messageCode;
		this.message = message;
	}

	
	// getter, setter
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
}
