package kgc.laki.recruitment.servlet

import kgc.laki.recruitment.api.LaGouAPI
import kgc.laki.recruitment.base.BaseServlet
import kgc.laki.recruitment.factory.RetrofitFactory
import kgc.laki.recruitment.model.HotSearch
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "HotSearchServlet", urlPatterns = ["/HotSearch"])
class HotSearchServlet : BaseServlet() {
	override fun doAction(request: HttpServletRequest, response: HttpServletResponse) {
		initResponseWriter(response)
		val hotSearchList = ArrayList<HotSearch>()
		val apiResponse = RetrofitFactory.hotSearchRetrofit
				.create(LaGouAPI::class.java)
				.getHotSearch()
				.execute()
		if (apiResponse.isSuccessful) {
			val originMessage = apiResponse.body()!!.string()
			val message = originMessage.substring(originMessage.indexOf('(') + 1, originMessage.indexOf(')'))
			response.writer.write(message)
		}
//		response.writer.write(Gson().toJson(hotSearchList))
	}
}