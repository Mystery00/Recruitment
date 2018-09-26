package kgc.laki.recruitment.servlet

import kgc.laki.recruitment.base.BaseServlet
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "GetCompanyInfoServlet", urlPatterns = ["/getCompanyInfo"])
class GetCompanyInfoServlet : BaseServlet() {
	override fun doAction(request: HttpServletRequest, response: HttpServletResponse) {
		val companyID = request.getParameter("companyID")
	}
}