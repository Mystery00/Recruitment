package kgc.laki.recruitment.servlet

import kgc.laki.recruitment.base.BaseServlet
import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.repository.GetJobInfoRepository
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.exception.KGCException
import java.net.UnknownHostException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "GetJobInfoServlet", urlPatterns = ["/getJobInfo"])
class GetJobInfoServlet : BaseServlet() {
	override fun doAction(request: HttpServletRequest, response: HttpServletResponse) {
		val positionID = request.getParameter("positionID")
		try {
			GetJobInfoRepository.getInfo(request, positionID)
		} catch (e: Exception) {
			when (e) {
				is UnknownHostException -> SessionUtil.setException(request, KGCException(ExceptionCodeConstant.J_ERROR_INTERNET))
				else -> SessionUtil.setException(request, KGCException(e.localizedMessage))
			}
			response.sendRedirect("error.jsp")
			return
		}
		response.sendRedirect("jobInfo.jsp")
	}
}