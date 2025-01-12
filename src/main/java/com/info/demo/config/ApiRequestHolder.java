package com.info.demo.config;

public abstract class ApiRequestHolder {

	private ApiRequestHolder() {
	}
	private static final ThreadLocal<Boolean> threadToken = new ThreadLocal<Boolean>();

	public static boolean isLocal() {
		return threadToken.get();
	}

	public static void setToken(boolean local) {
		threadToken.set(local);
	}

	public static void removeToken() {
		threadToken.remove();
	}

}