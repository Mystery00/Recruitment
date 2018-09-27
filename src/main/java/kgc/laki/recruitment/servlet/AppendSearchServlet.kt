package kgc.laki.recruitment.servlet

import kgc.laki.recruitment.base.BaseServlet
import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.repository.SearchRepository
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.StringUtil
import kgc.laki.recruitment.utils.exception.KGCException
import java.net.UnknownHostException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "AppendSearchServlet", urlPatterns = ["/appendSearch"])
class AppendSearchServlet : BaseServlet() {
	override fun doAction(request: HttpServletRequest, response: HttpServletResponse) {
		val page = request.getParameter("page")
		val query = request.getParameter("query")
		val city = request.getParameter("city")
		val gx = request.getParameter("gx")
		val gj = request.getParameter("gj")
		val xl = request.getParameter("xl")
		val jd = request.getParameter("jd")
		val gm = request.getParameter("gm")
		val hy = request.getParameter("hy")
		val searchBean = SessionUtil.getSearchChoose(request).searchBean
		searchBean.page = page?.toInt() ?: 1
		if (query != null) searchBean.query = query
		if (city != null) searchBean.city = city
		if (gx != null) {
			searchBean.gx = gx
			searchBean.gj = "不限"
		}
		if (gj != null) {
			when (gj) {
				"应届毕业生" -> {
					searchBean.gx = "应届"
					searchBean.isSchoolJob = 1
					searchBean.gj = "不限"
				}
				"不限" -> {
					searchBean.gj = gj
				}
				else -> {
					val list = StringUtil.stringToList(searchBean.gj, ",").toMutableList()
					if (list.contains(gj))
						list.remove(gj)
					else
						list.add(gj)
					searchBean.gj = StringUtil.listToString(list, ",")
					searchBean.isSchoolJob = if (searchBean.gj.contains("应届")) 1 else 0
					searchBean.gx = "不限"
				}
			}
		}
		if (xl != null) {
			when (xl) {
				"不限" -> {
					searchBean.xl = xl
				}
				else -> {
					val list = StringUtil.stringToList(searchBean.xl, ",").toMutableList()
					if (list.contains(xl))
						list.remove(xl)
					else
						list.add(xl)
					searchBean.xl = StringUtil.listToString(list, ",")
				}
			}
		}
		if (jd != null) {
			when (jd) {
				"不限" -> {
					searchBean.jd = jd
				}
				else -> {
					val list = StringUtil.stringToList(searchBean.jd, ",").toMutableList()
					if (list.contains(jd))
						list.remove(jd)
					else
						list.add(jd)
					searchBean.jd = StringUtil.listToString(list, ",")
				}
			}
		}
		if (gm != null) {
			when (gm) {
				"不限" -> {
					searchBean.gm = gm
				}
				else -> {
					val list = StringUtil.stringToList(searchBean.gm, ",").toMutableList()
					if (list.contains(gm))
						list.remove(gm)
					else
						list.add(gm)
					searchBean.gm = StringUtil.listToString(list, ",")
				}
			}
		}
		if (hy != null) {
			when (hy) {
				"不限" -> {
					searchBean.hy = hy
				}
				else -> {
					val list = StringUtil.stringToList(searchBean.hy, ",").toMutableList()
					if (list.contains(hy))
						list.remove(hy)
					else
						list.add(hy)
					searchBean.hy = StringUtil.listToString(list, ",")
				}
			}
		}
		try {
			SearchRepository.doSearch(request, searchBean)
		} catch (e: Exception) {
			when (e) {
				is KGCException -> SessionUtil.setException(request, e)
				is UnknownHostException -> SessionUtil.setException(request, KGCException(ExceptionCodeConstant.J_ERROR_INTERNET))
				else -> SessionUtil.setException(request, KGCException(ExceptionCodeConstant.DONE, e.message))
			}
			response.sendRedirect("error.jsp")
			return
		}
		response.sendRedirect("search.jsp")
	}
}