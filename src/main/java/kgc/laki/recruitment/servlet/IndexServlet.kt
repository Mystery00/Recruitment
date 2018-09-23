package kgc.laki.recruitment.servlet

import kgc.laki.recruitment.base.BaseServlet
import kgc.laki.recruitment.repository.IndexRepository
import kgc.laki.recruitment.utils.SessionUtil

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "IndexServlet", urlPatterns = ["/"])
class IndexServlet : BaseServlet() {
	override fun doAction(request: HttpServletRequest, response: HttpServletResponse) {
		SessionUtil.setKeyWord(request, IndexRepository.getKeyWord())
		SessionUtil.setHotSearch(request, IndexRepository.getHotSearch())
		response.sendRedirect("/main.jsp")
	}
}
