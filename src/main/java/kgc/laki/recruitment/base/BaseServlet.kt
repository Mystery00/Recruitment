package kgc.laki.recruitment.base

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

abstract class BaseServlet(private val isAllowPost: Boolean = true,
						   private val isAllowGet: Boolean = true) : HttpServlet() {
	abstract fun doAction(request: HttpServletRequest, response: HttpServletResponse)

	fun initResponseWriter(response: HttpServletResponse) {
		response.characterEncoding = "UTF-8"//设置Response的编码方式为UTF-8
		response.setHeader("Content-type", "text/html;charset=UTF-8")
	}

	@Throws(ServletException::class, IOException::class)
	override
	fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
		if (isAllowPost)
			doAction(request, response)
	}

	@Throws(ServletException::class, IOException::class)
	override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
		if (isAllowGet)
			doAction(request, response)
	}
}