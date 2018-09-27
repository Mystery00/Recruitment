package kgc.laki.recruitment.utils.exception

import kgc.laki.recruitment.constant.ExceptionCodeConstant

class KGCException(val msg: String?) : Exception() {
	override fun getLocalizedMessage(): String? = msg

	constructor(code: Int) : this(ExceptionCodeConstant.getMsg(code))
}