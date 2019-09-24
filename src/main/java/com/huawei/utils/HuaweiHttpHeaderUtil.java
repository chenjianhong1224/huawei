package com.huawei.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import com.google.common.collect.Lists;

public class HuaweiHttpHeaderUtil {

	public static List<Header> getPostHeader(Map<String, String> head) {
		List<Header> headerList = Lists.newArrayList();
		if (head == null || !head.containsKey(HttpHeaders.ACCEPT)) {
			headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "application/json, text/javascript, */*; q=0.01"));
		}
		if (head == null || !head.containsKey(HttpHeaders.ACCEPT_ENCODING)) {
			headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br"));
		}
		if (head == null || !head.containsKey(HttpHeaders.ACCEPT_LANGUAGE)) {
			headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE,
					"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"));
		}
		if (head == null || !head.containsKey(HttpHeaders.CONNECTION)) {
			headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
		}
		if (head == null || !head.containsKey(HttpHeaders.HOST)) {
			headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "id1.cloud.huawei.com"));
		}
		if (head == null || !head.containsKey(HttpHeaders.USER_AGENT)) {
			headerList.add(new BasicHeader(HttpHeaders.USER_AGENT,
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0"));
		}
		if (head == null || !head.containsKey("X-Requested-With")) {
			headerList.add(new BasicHeader("X-Requested-With", "XMLHttpRequest"));
		}

		if (head != null && head.size() > 0) {
			for (String key : head.keySet()) {
				headerList.add(new BasicHeader(key, head.get(key)));
			}
		}
		return headerList;
	};

	public static List<Header> getGetHeader(Map<String, String> head) {
		List<Header> headerList = Lists.newArrayList();
		if (head == null || !head.containsKey(HttpHeaders.ACCEPT)) {
			headerList.add(new BasicHeader(HttpHeaders.ACCEPT,
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
		}
		if (head == null || !head.containsKey(HttpHeaders.ACCEPT_ENCODING)) {
			headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br"));
		}
		if (head == null || !head.containsKey(HttpHeaders.ACCEPT_LANGUAGE)) {
			headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE,
					"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"));
		}
		if (head == null || !head.containsKey(HttpHeaders.CONNECTION)) {
			headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
		}
		if (head == null || !head.containsKey(HttpHeaders.HOST)) {
			headerList.add(new BasicHeader(HttpHeaders.HOST, "id1.cloud.huawei.com"));
		}
		if (head == null || !head.containsKey(HttpHeaders.USER_AGENT)) {
			headerList.add(new BasicHeader(HttpHeaders.USER_AGENT,
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0"));
		}
		if (head == null || !head.containsKey("Upgrade-Insecure-Request")) {
			headerList.add(new BasicHeader("Upgrade-Insecure-Request", "1"));
		}
		if (head != null && head.size() > 0) {
			for (String key : head.keySet()) {
				headerList.add(new BasicHeader(key, head.get(key)));
			}
		}
		return headerList;
	};
}
