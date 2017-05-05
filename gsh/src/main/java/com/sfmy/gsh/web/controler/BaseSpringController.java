package com.sfmy.gsh.web.controler;

import com.sfmy.gsh.web.base.JsonResult;

public class BaseSpringController {

	public <T> JsonResult<T> success() {
		JsonResult<T> result = new JsonResult<T>();
		return result;
	}

	public <T> JsonResult<T> success(T t) {
		JsonResult<T> result = new JsonResult<T>();
		result.setData(t);
		return result;
	}

	public <T> JsonResult<T> success(String message, T t) {
		JsonResult<T> result = new JsonResult<T>();
		result.setData(t);
		result.setMessage(message);
		return result;
	}

	public <T> JsonResult<T> fail(String errorMessage) {
		JsonResult<T> result = new JsonResult<T>();
		result.setCode(9999);
		result.setMessage(errorMessage);
		return result;
	}
	
	public <T> JsonResult<T> fail(int code, String errorMessage) {
		JsonResult<T> result = new JsonResult<T>();
		result.setCode(code);
		result.setMessage(errorMessage);
		return result;
	}
}
