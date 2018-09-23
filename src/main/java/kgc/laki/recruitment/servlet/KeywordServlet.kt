package kgc.laki.recruitment.servlet

import com.google.gson.Gson
import kgc.laki.recruitment.base.BaseServlet
import kgc.laki.recruitment.model.Keyword
import kgc.laki.recruitment.model.KeywordGroup
import kgc.laki.recruitment.utils.PropertiesUtil
import org.jsoup.Jsoup
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "KeywordServlet", urlPatterns = ["/Keyword"])
class KeywordServlet : BaseServlet() {
	override fun doAction(request: HttpServletRequest, response: HttpServletResponse) {
		initResponseWriter(response)
		val doc = Jsoup.connect(PropertiesUtil.getUrl("KeywordRequestUrl")).post()
		val keywordGroupList = ArrayList<KeywordGroup>()
		doc.select(".menu_box").forEach { element ->
			val menu_main = element.select(".menu_main")[0]
			val menu_sub = element.select(".menu_sub")[0]
			val dls = menu_sub.select("dl")
			dls.forEach {
				val dt = it.select("dt")[0]
				val dd = it.select("dd")[0]
				val title = dt.text()
				val values = dd.select("a")
				val keywordGroup = KeywordGroup()
				keywordGroup.title = title
				keywordGroup.keywordList = values.mapIndexed { _, element ->
					val keyword = Keyword()
					keyword.href = element.attr("href")
					keyword.value = element.text()
					keyword
				}
				keywordGroupList.add(keywordGroup)
			}
		}
		response.writer.write(Gson().toJson(keywordGroupList))
	}
}
