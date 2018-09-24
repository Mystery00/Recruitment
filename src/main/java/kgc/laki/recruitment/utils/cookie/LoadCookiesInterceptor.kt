package kgc.laki.recruitment.utils.cookie

import okhttp3.Interceptor
import okhttp3.Response

class LoadCookiesInterceptor : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		println("LoadCookiesInterceptor: url: ${request.url()}")
		println("LoadCookiesInterceptor: host: ${request.url().host()}")
		val builder = request.newBuilder()
		if (request.url().toString().contains("/jobs/positionAjax.json")) {
			val saveCookie = CookieManager.getCookieMap("a.lagou.com")
			if (saveCookie != null) {
				println("load cookie")
				val cookies = arrayListOf(
						saveCookie["user_trace_token"],
						"_ga=GA1.2.1287771199.1537693195",
						"index_location_city=%E5%85%A8%E5%9B%BD",
						saveCookie["JSESSIONID"],
						"_gid=GA1.2.1087692700.1537697960",
						"Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1537695677,1537698071,1537699393,1537699541",
						saveCookie["LGUID"],
						"fromsite=\"localhost:8080\"",
						"TG-TRACK-CODE=index_search",
						saveCookie["LGRID"],
						saveCookie["SEARCH_ID"],
						"isCloseNotice=0"
				)
				builder.addHeader("Cookie", encodeCookie(cookies))
				builder.addHeader("Origin", "https://www.lagou.com")
				builder.addHeader("Referer", "https://www.lagou.com/jobs/list_%E6%B5%8B%E8%AF%95?city=%E5%85%A8%E5%9B%BD&cl=false&fromSearch=true&labelWords=&suginput=")
				builder.addHeader("Host", "www.lagou.com")
				builder.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
				builder.addHeader("X-Anit-Forge-Code", "0")
				builder.addHeader("X-Anit-Forge-Token", "None")
				builder.addHeader("X-Requested-With", "XMLHttpRequest")
				builder.addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
				builder.addHeader("Accept-Encoding", "gzip, deflate, br")
				builder.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
				builder.addHeader("Connection", "keep-alive")
				builder.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				return chain.proceed(builder.build())
			}
		}
		val cookie = CookieManager.getCookie(request.url().host())
		if (cookie != null && cookie != "") builder.addHeader("Cookie", cookie)
		return chain.proceed(builder.build())
	}


	//整合cookie为唯一字符串
	private fun encodeCookie(cookies: ArrayList<String?>): String {
		val sb = StringBuilder()
		val set = HashSet<String>()
		cookies.map { cookie ->
			cookie?.split(";".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
		}.forEach { arr ->
			arr?.filterNot { set.contains(it) }?.forEach { set.add(it) }
		}

		val ite = set.iterator()
		while (ite.hasNext()) {
			val cookie = ite.next()
			sb.append(cookie).append(';')
		}

		val last = sb.lastIndexOf(';')
		if (sb.length - 1 == last) {
			sb.deleteCharAt(last)
		}

		return sb.toString()
	}
}