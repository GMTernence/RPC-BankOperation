package com.liuyan.call;

import java.io.Serializable;

public class RpcRequest implements Serializable {
	private String className;
	private String methodName;
	private Class<?> [] parasType;
	private Object [] paras;
	public RpcRequest(String className,String methodName,Class<?> [] parasType,Object [] paras) {
		this.className=className;
		this.methodName=methodName;
		this.parasType=parasType;
		this.paras=paras;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class<?>[] getParasType() {
		return parasType;
	}
	public void setParasType(Class<?>[] parasType) {
		this.parasType = parasType;
	}
	public Object[] getParas() {
		return paras;
	}
	public void setParas(Object[] paras) {
		this.paras = paras;
	}
	

}
