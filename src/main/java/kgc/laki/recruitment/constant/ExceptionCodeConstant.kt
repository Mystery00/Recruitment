package kgc.laki.recruitment.constant

object ExceptionCodeConstant {
	const val DONE = 0
	const val J_ERROR_INTERNET = 101
	const val J_ERROR_EMPTY_RESPONSE = 102
	const val J_ERROR_PARSE = 103

	fun getMsg(code: Int): String = when (code) {
		DONE -> "成功"
		J_ERROR_INTERNET -> "网络连接异常"
		J_ERROR_EMPTY_RESPONSE -> "返回数据为空"
		J_ERROR_PARSE -> "数据解析出错"
		else -> "未知错误"
	}
}