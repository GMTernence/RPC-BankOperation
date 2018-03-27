package com.liuyan.call;

import java.io.Serializable;

public class RpcResponse implements Serializable {
	private Object result;
	private Throwable error;
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public Throwable getError() {
		return error;
	}
	public void setError(Throwable error) {
		this.error = error;
	}
	

}
