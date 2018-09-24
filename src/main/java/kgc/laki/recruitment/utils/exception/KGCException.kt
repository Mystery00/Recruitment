package kgc.laki.recruitment.utils.exception

import kgc.laki.recruitment.constant.ExceptionCodeConstant

class KGCException(val code: Int, val msg: String) : Exception() {
	constructor(code: Int) : this(code, ExceptionCodeConstant.getMsg(code))
}