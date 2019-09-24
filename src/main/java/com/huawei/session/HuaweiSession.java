package com.huawei.session;

import org.apache.http.client.CookieStore;

public class HuaweiSession {

	private CookieStore cookieStore;

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}
}
