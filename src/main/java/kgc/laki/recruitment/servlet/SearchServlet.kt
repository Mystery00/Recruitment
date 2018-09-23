package kgc.laki.recruitment.servlet

import kgc.laki.recruitment.base.BaseServlet
import kgc.laki.recruitment.repository.SearchRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "SearchServlet", urlPatterns = ["/doSearch"])
class SearchServlet : BaseServlet() {
	override fun doAction(request: HttpServletRequest, response: HttpServletResponse) {
		val query = request.getParameter("query")
		SearchRepository.doSearch(request, query)
		response.sendRedirect("/search.jsp")
	}
}