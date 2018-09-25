package kgc.laki.recruitment.repository.remote

import kgc.laki.recruitment.api.LaGouAPI
import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.factory.GsonFactory
import kgc.laki.recruitment.factory.RetrofitFactory
import kgc.laki.recruitment.model.CompanyJob
import kgc.laki.recruitment.model.SearchBean
import kgc.laki.recruitment.model.response.CompanyJobResponse
import kgc.laki.recruitment.model.response.SearchChoose
import kgc.laki.recruitment.repository.dataSource.SearchDataSource
import kgc.laki.recruitment.utils.exception.KGCException
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.lang.Exception
import java.util.ArrayList

object SearchRemoteDataSource : SearchDataSource {
	override fun getSearchChoose(query: String?, searchBean: SearchBean, html: String?): SearchChoose {
		val searchChoose = SearchChoose()
		try {
			val doc = Jsoup.parse(html)
			val positionHead = doc.select("#positionHead")
			val filterBox = positionHead.select("#filterBox")
			val filterCollapse = filterBox.select("#filterCollapse")
			val city = filterCollapse.select(".has-more")[0]
			val multi_chosen = filterCollapse.select(".multi-chosen")
			val hy_area = filterCollapse.select(".hy-area")[0]
			val order = positionHead.select("#order")
			val item_order = order.select(".wrapper")[0]
			val order_px = item_order.select(".order")[0]
			val salary = item_order.select(".salary")[0]
			searchChoose.city = getCity(city)
			searchChoose.isSchoolJob = true
			searchChoose.gx = getChoose(multi_chosen[0])
			searchChoose.xl = getChoose(multi_chosen[1])
			searchChoose.jd = getChoose(multi_chosen[2])
			searchChoose.gm = getChoose(multi_chosen[3])
			searchChoose.hy = getHyArea(hy_area)
			searchChoose.px = getOrderType(order_px)
			searchChoose.yx = getSalary(salary)
			searchChoose.searchBean = searchBean
		} catch (e: Exception) {
			e.printStackTrace()
			throw KGCException(ExceptionCodeConstant.J_ERROR_PARSE)
		}
		return searchChoose
	}

	override fun getCompanyJob(query: String?, searchBean: SearchBean, html: String?): List<CompanyJob> {
		val companyJobList = ArrayList<CompanyJob>()
		val firstResponse = RetrofitFactory.laGouGetResultFirstRetrofit
				.create(LaGouAPI::class.java)
				.getSearchResultFirst()
				.execute()
		if (!firstResponse.isSuccessful)
			throw KGCException(ExceptionCodeConstant.J_ERROR_INTERNET)
		val apiResponse = RetrofitFactory.laGouRetrofit
				.create(LaGouAPI::class.java)
				.getSearchResult(2, query!!)
				.execute()
		if (!apiResponse.isSuccessful)
			throw KGCException(ExceptionCodeConstant.J_ERROR_INTERNET)
		val jsonString = apiResponse.body()?.string()
				?: throw KGCException(ExceptionCodeConstant.J_ERROR_EMPTY_RESPONSE)
		println(jsonString)
		try {
			val companyJobResponse = GsonFactory.gson.fromJson<CompanyJobResponse>(jsonString, CompanyJobResponse::class.java)
			companyJobResponse.content.positionResult.result.forEach {
				val companyJob = CompanyJob()
				companyJob.jobName = it.positionName
				companyJob.location = "${it.city}·${it.district}"
				companyJob.publishTime = it.formatCreateTime
				companyJob.money = it.salary
				companyJob.exp = it.workYear
				companyJob.grade = it.education
				companyJob.tag = it.positionLables
				companyJob.companyName = it.companyShortName
				companyJob.hy = it.industryField
				companyJob.jd = it.financeStage
				companyJob.personNum = it.companySize
				companyJob.temptation = it.positionAdvantage
				companyJob.companyImgUrl = "https://www.lgstatic.com/thumbnail_120x120/${it.companyLogo}"
				companyJobList.add(companyJob)
			}
		} catch (e: Exception) {
			e.printStackTrace()
			throw KGCException(ExceptionCodeConstant.J_ERROR_PARSE)
		}
		return companyJobList
	}

	private fun getCity(element: Element): List<String> {
		val more = element.select(".workPosition")[0]
		val lis = more.select("li")
		val cities = ArrayList<String>()
		lis.forEach { c ->
			val a = c.select("a")
			a.forEach { label ->
				cities.add(label.text().trim())
			}
		}
		cities.remove("全部城市 >")
		return cities
	}

	private fun getChoose(element: Element): List<String> {
		val a = element.select("a")
		val aValues = ArrayList<String>()
		a.forEach { aValues.add(it.text()) }
		return aValues
	}

	private fun getHyArea(element: Element): List<String> {
		val lis = element.select(".more-hy")[0].select("li")
		val hys = ArrayList<String>()
		lis.forEach { h ->
			val a = h.select("a")
			a.forEach { label ->
				hys.add(label.text())
			}
		}
		return hys
	}

	private fun getOrderType(element: Element): List<String> {
		val orders = ArrayList<String>()
		element.select("a")
				.forEach { orders.add(it.text()) }
		return orders
	}

	private fun getSalary(element: Element): List<String> {
		val salaryList = ArrayList<String>()
		val selector = element.select(".selectUI-text").select("ul").select("li")
		selector.forEach { salaryList.add(it.select("a").text()) }
		return salaryList
	}
}