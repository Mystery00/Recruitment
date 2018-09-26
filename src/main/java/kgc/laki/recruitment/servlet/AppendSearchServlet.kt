package kgc.laki.recruitment.servlet

import kgc.laki.recruitment.base.BaseServlet
import kgc.laki.recruitment.repository.SearchRepository
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.StringUtil
import kgc.laki.recruitment.utils.exception.KGCException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "AppendSearchServlet", urlPatterns = ["/appendSearch"])
class AppendSearchServlet : BaseServlet() {
	override fun doAction(request: HttpServletRequest, response: HttpServletResponse) {
		val query = request.getParameter("query")
		val city = request.getParameter("city")
		val gx = request.getParameter("gx")
		val gj = request.getParameter("gj")
		val searchBean = SessionUtil.getSearchChoose(request).searchBean
		if (query != null) searchBean.query = query
		if (city != null) searchBean.city = city
		if (gx != null) {
			searchBean.gx = gx
			searchBean.isSchoolJob = if (gx.contains("应届")) 1 else 0
			searchBean.gj = ""
		}
		if (gj != null) {
			val list = StringUtil.stringToList(searchBean.gj, ",").toMutableList()
			if (list.contains(gj))
				list.remove(gj)
			else
				list.add(gj)
			searchBean.gj = StringUtil.listToString(list, ",")
			searchBean.isSchoolJob = if (searchBean.gj.contains("应届")) 1 else 0
			searchBean.gx = ""
		}
		try {
			SearchRepository.doSearch(request, searchBean)
		} catch (e: KGCException) {
			SessionUtil.setException(request, e)
			response.sendRedirect("error.jsp")
			return
		}
		response.sendRedirect("search.jsp")
	}
}