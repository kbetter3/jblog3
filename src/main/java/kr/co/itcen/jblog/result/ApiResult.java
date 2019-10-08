package kr.co.itcen.jblog.result;

import java.util.List;

import kr.co.itcen.jblog.type.ResponseCode;

public class ApiResult<T> {
	private int code;
	private boolean status;
	private String message;

	private T data;
	private List<T> datas;

	public ApiResult() {
		this(ResponseCode.SUCCESS.getCode(), true, ResponseCode.SUCCESS.getMessage());
	}

	public ApiResult(ResponseCode responseCode) {
		this(responseCode.getCode(), false, responseCode.getMessage());
	}

	public ApiResult(T data) {
		this();
		this.data = data;
	}

	public ApiResult(List<T> datas) {
		this();
		this.datas = datas;
	}

	public ApiResult(int code, boolean status, String message) {
		this.code = code;
		this.status = status;
		this.message = message;
	}

	
	// getter, setter
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
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
}
