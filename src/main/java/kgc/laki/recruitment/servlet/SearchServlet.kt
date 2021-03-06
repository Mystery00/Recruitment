package kgc.laki.recruitment.servlet

import kgc.laki.recruitment.base.BaseServlet
import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.repository.SearchRepository
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.exception.KGCException
import java.lang.Exception
import java.net.UnknownHostException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "SearchServlet", urlPatterns = ["/doSearch"])
class SearchServlet : BaseServlet() {
	override fun doAction(request: HttpServletRequest, response: HttpServletResponse) {
		val query = request.getParameter("query")
		val city = request.getParameter("city") ?: "全国"
		try {
			SearchRepository.doSearch(request, SearchRepository.getSearchBean(query, city = city))
		} catch (e: Exception) {
			when (e) {
				is UnknownHostException -> SessionUtil.setException(request, KGCException(ExceptionCodeConstant.J_ERROR_INTERNET))
				else -> SessionUtil.setException(request, KGCException(e.localizedMessage))
			}
			response.sendRedirect("error.jsp")
			return
		}
		response.sendRedirect("search.jsp")
	}
}