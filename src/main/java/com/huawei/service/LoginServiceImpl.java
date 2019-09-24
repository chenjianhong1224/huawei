package com.huawei.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huawei.bean.ReturnResultBean;
import com.huawei.session.HuaweiSession;
import com.huawei.utils.HuaweiHttpHeaderUtil;

public class LoginServiceImpl {

	public ReturnResultBean chkRisk(String pageToken, String phoneNo, HuaweiSession session) {
		ReturnResultBean returnBean = new ReturnResultBean();
		returnBean.setResultCode(-1);
		returnBean.setReturnMsg("chkRisk失败");
		Map<String, String> headMap = Maps.newHashMap();
		headMap.put("Page-Token", pageToken);
		headMap.put("Referer", "https://id1.cloud.huawei.com/CAS/mobile/standard/wapLogin.html");
		headMap.put(HttpHeaders.HOST, "id1.cloud.huawei.com");
		List<Header> headers = HuaweiHttpHeaderUtil.getPostHeader(headMap);
		CookieStore cookieStore = session.getCookieStore();

		HttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
				.setDefaultHeaders(headers).setDefaultCookieStore(cookieStore).build();
		String url = "https://id1.cloud.huawei.com/CAS/IDM_W/ajaxHandler/chkRisk?reflushCode=" + Math.random();
		URI uri = null;
		try {
			uri = new URIBuilder(url).build();
		} catch (URISyntaxException e) {
			returnBean.setResultCode(-1);
			returnBean.setReturnMsg("chkRisk失败 " + e.getMessage());
			return returnBean;
		}
		List<NameValuePair> params = Lists.newArrayList();
		params.add(new BasicNameValuePair("userAccount", "0086" + phoneNo));
		params.add(new BasicNameValuePair("operType", "11"));
		params.add(new BasicNameValuePair("service", "https://id1.cloud.huawei.com/AMW/portal/userCenter/index.html"));
		params.add(new BasicNameValuePair("reqClientType", "7"));
		params.add(new BasicNameValuePair("languageCode", "zh-cn"));
		try {
			HttpUriRequest httpUriRequest;
			httpUriRequest = RequestBuilder.post().setEntity(new UrlEncodedFormEntity(params, "UTF-8")).setUri(uri)
					.build();
			HttpClientContext httpClientContext = HttpClientContext.create();
			HttpResponse response = httpClient.execute(httpUriRequest, httpClientContext);
			session.setCookieStore(cookieStore);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String content = EntityUtils.toString(entity);
					JSONObject jsonObject = JSON.parseObject(content);
					if (jsonObject.getInteger("isSuccess") == 1) {
						returnBean.setResultCode(0);
						returnBean.setReturnObj(1);
					}
				}
			} else {
				returnBean.setResultCode(-1);
				returnBean.setReturnMsg("chkRisk失败 返回http状态码" + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			returnBean.setResultCode(-1);
			returnBean.setReturnMsg("chkRisk失败 " + e.getMessage());
		}
		return returnBean;
	}

	public ReturnResultBean analysisHealth(String pageToken, HuaweiSession session) {
		ReturnResultBean returnBean = new ReturnResultBean();
		returnBean.setResultCode(-1);
		returnBean.setReturnMsg("获取heath失败");
		Map<String, String> headMap = Maps.newHashMap();
		headMap.put("Page-Token", pageToken);
		headMap.put("Referer", "https://id1.cloud.huawei.com/CAS/mobile/standard/wapLogin.html");
		headMap.put(HttpHeaders.HOST, "id1.cloud.huawei.com");
		List<Header> headers = HuaweiHttpHeaderUtil.getPostHeader(headMap);
		CookieStore cookieStore = session.getCookieStore();

		HttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
				.setDefaultHeaders(headers).setDefaultCookieStore(cookieStore).build();
		String url = "https://id1.cloud.huawei.com/CAS/IDM_W/ajaxHandler/analysisHealth?reflushCode=" + Math.random();
		URI uri = null;
		try {
			uri = new URIBuilder(url).build();
		} catch (URISyntaxException e) {
			returnBean.setResultCode(-1);
			returnBean.setReturnMsg("获取heath失败 " + e.getMessage());
			return returnBean;
		}
		List<NameValuePair> params = Lists.newArrayList();
		params.add(new BasicNameValuePair("reqClientType", "7"));
		params.add(new BasicNameValuePair("operType", "20"));
		params.add(new BasicNameValuePair("languageCode", "zh-cn"));
		params.add(new BasicNameValuePair("loginChannel", "LOGIN_CLICK_SMS_LOGIN"));
		params.add(new BasicNameValuePair("event", "7"));
		try {
			HttpUriRequest httpUriRequest;
			httpUriRequest = RequestBuilder.post().setEntity(new UrlEncodedFormEntity(params, "UTF-8")).setUri(uri)
					.build();
			HttpClientContext httpClientContext = HttpClientContext.create();
			HttpResponse response = httpClient.execute(httpUriRequest, httpClientContext);
			session.setCookieStore(cookieStore);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String content = EntityUtils.toString(entity);
					JSONObject jsonObject = JSON.parseObject(content);
					if (jsonObject.getInteger("isSuccess") == 1) {
						returnBean.setResultCode(0);
						returnBean.setReturnObj(1);
					}
				}
			} else {
				returnBean.setResultCode(-1);
				returnBean.setReturnMsg("获取heath失败 返回http状态码" + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			returnBean.setResultCode(-1);
			returnBean.setReturnMsg("获取heath失败 " + e.getMessage());
		}
		return returnBean;
	}

	public ReturnResultBean getSid(String pageToken, HuaweiSession session) {
		ReturnResultBean returnBean = new ReturnResultBean();
		Map<String, String> headMap = Maps.newHashMap();
		headMap.put("Page-Token", pageToken);
		headMap.put("Referer", "https://id1.cloud.huawei.com/CAS/mobile/standard/wapLogin.html");
		headMap.put(HttpHeaders.HOST, "id1.cloud.huawei.com");
		List<Header> headers = HuaweiHttpHeaderUtil.getPostHeader(headMap);
		CookieStore cookieStore = session.getCookieStore();
		HttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
				.setDefaultHeaders(headers).setDefaultCookieStore(cookieStore).build();
		String url = "https://id1.cloud.huawei.com/CAS/IDM_W/ajaxHandler/dev";
		URI uri = null;
		try {
			uri = new URIBuilder(url).build();
		} catch (URISyntaxException e) {
			returnBean.setResultCode(-1);
			returnBean.setReturnMsg("获取sid失败 " + e.getMessage());
			return returnBean;
		}
		List<NameValuePair> params = Lists.newArrayList();
		params.add(new BasicNameValuePair("fp",
				"sNyzj7mPuJHygvbI9sbxye6O7IXykPzGp5enkvfE9Maml/fFppamxKD+n6fDpJaix/XD8sDZvdnhguDr2b3Y4Pmc/ov5xeKE88//mq6f/J+mxPPE8cCOutq4grLXtdXm3LiBuYHl1ezY597v2buDsJn73L7NoKKQqc2816WZy/ueqpv4k6rI98CNvIq+3ryOvtu52erQrJ2lnfnJ8Nzr0uPVt4+8je/IotWgopC6j7eEu42/j7iHv4+owrXO8ML1zPvcvdO8z72BsJeuzKmQvY27jb7b6dHh0bXXtYO2gE11QXREIkInFHVGdhF2R3RDc1Q6STsHNwYrGS8AzfjG69Pqz6D+nvPPg+2W/Jf6mLHevNXppsDLudu7yqyN4oLk3qnarM7rhO+Jtc6licuEpcq53uCI7oOxgqfVtdzm0uPa/43tm6eXpZKngAxjX2leZUIyRnhGdkN0VSNHJEQvE3BZalB7TngUJBp8HSpIfkR6SHFGd0NzRCIYdRFxRXxPex4sGnpJekhhA3FNf0orSHVGckgmEyRGdEJxRHALPA9rU2JUMQc0BmNRY1NiUTECMlJjAw=="));
		try {
			HttpUriRequest httpUriRequest;
			httpUriRequest = RequestBuilder.post().setEntity(new UrlEncodedFormEntity(params, "UTF-8")).setUri(uri)
					.build();
			HttpClientContext httpClientContext = HttpClientContext.create();
			HttpResponse response = httpClient.execute(httpUriRequest, httpClientContext);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String content = EntityUtils.toString(entity);
					JSONObject jsonObject = JSON.parseObject(content);
					returnBean.setResultCode(0);
					String sid = jsonObject.getString("sid");
					returnBean.setReturnObj(sid);
					BasicClientCookie cookie1 = new BasicClientCookie("hwid_cas_sid", sid);
					cookie1.setDomain("id1.cloud.huawei.com");
					cookie1.setPath("/");
					cookie1.setAttribute(ClientCookie.DOMAIN_ATTR, "id1.cloud.huawei.com");
					cookie1.setAttribute(ClientCookie.PATH_ATTR, "/");
					cookieStore.addCookie(cookie1);

					BasicClientCookie cookie2 = new BasicClientCookie("sid", sid);
					cookie2.setDomain("id1.cloud.huawei.com");
					cookie2.setPath("/");
					cookie2.setAttribute(ClientCookie.DOMAIN_ATTR, "id1.cloud.huawei.com");
					cookie2.setAttribute(ClientCookie.PATH_ATTR, "/");
					cookieStore.addCookie(cookie2);
				}
			} else {
				returnBean.setResultCode(-1);
				returnBean.setReturnMsg("获取sid失败 返回http状态码" + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			returnBean.setResultCode(-1);
			returnBean.setReturnMsg("获取sid失败 " + e.getMessage());
		}
		session.setCookieStore(cookieStore);
		return returnBean;
	}

	public ReturnResultBean getLoginPageToken(HuaweiSession session) {
		ReturnResultBean returnBean = new ReturnResultBean();
		returnBean.setResultCode(-1);
		returnBean.setReturnMsg("获取login pageToken失败");
		List<Header> headers = HuaweiHttpHeaderUtil.getGetHeader(null);
		CookieStore cookieStore = new BasicCookieStore();
		HttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
				.setDefaultHeaders(headers).setDefaultCookieStore(cookieStore).build();
		String url = "https://id1.cloud.huawei.com/CAS/mobile/standard/wapLogin.html";
		URI uri = null;
		try {
			uri = new URIBuilder(url).build();
		} catch (URISyntaxException e) {
			returnBean.setResultCode(-1);
			returnBean.setReturnMsg("获取login pageToken失败 " + e.getMessage());
			return returnBean;
		}
		HttpUriRequest httpUriRequest = RequestBuilder.get().setUri(uri).build();
		HttpClientContext httpClientContext = HttpClientContext.create();
		try {
			HttpResponse response = httpClient.execute(httpUriRequest, httpClientContext);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String content = EntityUtils.toString(entity);
					String pageTokenStr = "var pageToken";
					int index = content.indexOf(pageTokenStr);
					if (index != -1) {
						String var = content.substring(index);
						int beginIndex = var.indexOf("\"");
						if (beginIndex != -1 && beginIndex != var.length() - 1) {
							String value = var.substring(beginIndex + 1);
							int endIndex = value.indexOf("\"");
							if (endIndex != -1 && endIndex != 0) {
								String pageToken = value.substring(0, endIndex);
								returnBean.setResultCode(0);
								returnBean.setReturnObj(pageToken);
								session.setCookieStore(cookieStore);
								return returnBean;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			returnBean.setReturnMsg("获取login pageToken失败 " + e.getMessage());
		}
		return returnBean;
	}

	public ReturnResultBean getSMSCodeV3(String pageToken, String phoneNo, HuaweiSession session) {
		ReturnResultBean returnBean = new ReturnResultBean();
		Map<String, String> headMap = Maps.newHashMap();
		headMap.put("Page-Token", pageToken);
		headMap.put("Referer", "https://id1.cloud.huawei.com/CAS/mobile/standard/wapLogin.html");
		headMap.put(HttpHeaders.HOST, "id1.cloud.huawei.com");
		List<Header> headers = HuaweiHttpHeaderUtil.getPostHeader(headMap);
		CookieStore cookieStore = session.getCookieStore();
		HttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
				.setDefaultHeaders(headers).setDefaultCookieStore(cookieStore).build();
		String url = "https://id1.cloud.huawei.com/CAS/IDM_W/ajaxHandler/getSMSCodeV3?reflushCode=" + Math.random();
		URI uri = null;
		try {
			uri = new URIBuilder(url).build();
		} catch (URISyntaxException e) {
			returnBean.setResultCode(-1);
			returnBean.setReturnMsg("获取验证码失败 " + e.getMessage());
			return returnBean;
		}
		List<NameValuePair> params = Lists.newArrayList();
		params.add(new BasicNameValuePair("accountType", "2"));
		params.add(new BasicNameValuePair("userAccount", "0086" + phoneNo));
		params.add(new BasicNameValuePair("reqClientType", "7"));
		params.add(new BasicNameValuePair("mobilePhone", "0086" + phoneNo));
		params.add(new BasicNameValuePair("operType", "20"));
		params.add(new BasicNameValuePair("smsReqType", "2"));
		params.add(new BasicNameValuePair("siteID", "1"));
		params.add(new BasicNameValuePair("languageCode", "zh-cn"));
		params.add(new BasicNameValuePair("service", "https://id1.cloud.huawei.com/AMW/portal/userCenter/index.html"));
		params.add(new BasicNameValuePair("loginChannel", "7000000"));
		try {
			HttpUriRequest httpUriRequest;
			httpUriRequest = RequestBuilder.post().setEntity(new UrlEncodedFormEntity(params, "UTF-8")).setUri(uri)
					.build();
			HttpClientContext httpClientContext = HttpClientContext.create();
			HttpResponse response = httpClient.execute(httpUriRequest, httpClientContext);
			session.setCookieStore(cookieStore);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String content = EntityUtils.toString(entity);
					System.out.println(content);
				}
			} else {
				returnBean.setResultCode(-1);
				returnBean.setReturnMsg("获取验证码失败 返回http状态码" + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			returnBean.setResultCode(-1);
			returnBean.setReturnMsg("获取验证码失败 " + e.getMessage());
		}
		return returnBean;
	}
}
