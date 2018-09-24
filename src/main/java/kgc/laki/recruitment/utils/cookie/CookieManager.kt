package kgc.laki.recruitment.utils.cookie

object CookieManager {
	private val cookiesMap = HashMap<String, HashMap<String, String>>()

	fun putCookie(domain: String, cookies: String) {
		val map = HashMap<String, String>()
		cookies.split(';').forEach {
			val cookie = it.split('=')
			map[cookie[0]] = it
		}
		cookiesMap[domain] = map
	}

	fun getCookieMap(domain: String): HashMap<String, String>? = cookiesMap[domain]

	fun getCookie(domain: String): String? {
		val stringBuilder = StringBuilder()
		val map = getCookieMap(domain) ?: return null
		map.forEach {
			stringBuilder.append(it.value).append(';')
		}
		return stringBuilder.toString()
	}
}