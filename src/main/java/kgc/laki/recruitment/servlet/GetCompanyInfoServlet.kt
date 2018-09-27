package kgc.laki.recruitment.servlet

import kgc.laki.recruitment.base.BaseServlet
import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.repository.GetCompanyInfoRepository
import kgc.laki.recruitment.repository.GetJobInfoRepository
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.exception.KGCException
import java.net.UnknownHostException
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
			when (e) {
				is KGCException -> SessionUtil.setException(request, e)
				is UnknownHostException -> SessionUtil.setException(request, KGCException(ExceptionCodeConstant.J_ERROR_INTERNET))
				else -> SessionUtil.setException(request, KGCException(ExceptionCodeConstant.DONE, e.message))
			}
			response.sendRedirect("error.jsp")
			return
		}
		response.sendRedirect("companyInfo.jsp")
	}
}