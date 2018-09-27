package kgc.laki.recruitment.servlet

import kgc.laki.recruitment.base.BaseServlet
import kgc.laki.recruitment.repository.GetCompanyInfoRepository
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.exception.KGCException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "GetCompanyInfoServlet", urlPatterns = ["/getCompanyInfo"])
class GetCompanyInfoServlet : BaseServlet() {
	override fun doAction(request: HttpServletRequest, response: HttpServletResponse) {
		val companyID = request.getParameter("companyID")
		try {
			GetCompanyInfoRepository.getInfo(request, companyID)
		} catch (e: Exception) {
			SessionUtil.setException(request, KGCException(e.localizedMessage))
			response.sendRedirect("error.jsp")
			return
		}
		response.sendRedirect("companyInfo.jsp")
	}
}