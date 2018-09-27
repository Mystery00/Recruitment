package kgc.laki.recruitment.servlet

import kgc.laki.recruitment.base.BaseServlet
import kgc.laki.recruitment.repository.IndexRepository
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.exception.KGCException

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "IndexServlet", urlPatterns = ["/index"])
class IndexServlet : BaseServlet() {
	override fun doAction(request: HttpServletRequest, response: HttpServletResponse) {
		try {
			SessionUtil.setKeyWord(request, IndexRepository.getKeyWord(request))
			SessionUtil.setHotSearch(request, IndexRepository.getHotSearch(request))
		} catch (e: Exception) {
			SessionUtil.setException(request, KGCException(e.localizedMessage))
			response.sendRedirect("error.jsp")
			return
		}
		response.sendRedirect("main.jsp")
	}
}
