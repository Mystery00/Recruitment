package kgc.laki.recruitment.repository.remote

import kgc.laki.recruitment.api.LaGouAPI
import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.factory.GsonFactory
import kgc.laki.recruitment.factory.RetrofitFactory
import kgc.laki.recruitment.model.HotSearch
import kgc.laki.recruitment.model.KeywordCategory
import kgc.laki.recruitment.model.KeywordGroup
import kgc.laki.recruitment.model.response.HotSearchResponse
import kgc.laki.recruitment.repository.dataSource.IndexDataSource
import kgc.laki.recruitment.repository.local.service.HotSearchService
import kgc.laki.recruitment.repository.local.service.KeywordService
import kgc.laki.recruitment.utils.StringUtil
import kgc.laki.recruitment.utils.exception.KGCException
import org.jsoup.Jsoup
import java.util.*
import kotlin.Exception

object IndexRemoteDataSource : IndexDataSource {
	override fun getHotSearch(): List<HotSearch> {
		val hotSearchList = ArrayList<HotSearch>()
		val apiResponse = RetrofitFactory.hotSearchRetrofit
				.create(LaGouAPI::class.java)
				.getHotSearch()
				.execute()
		if (!apiResponse.isSuccessful)
			throw KGCException(ExceptionCodeConstant.J_ERROR_INTERNET)
		val originMessage = apiResponse.body()?.string()
				?: throw KGCException(ExceptionCodeConstant.J_ERROR_EMPTY_RESPONSE)
		try {
			val message = originMessage.substring(originMessage.indexOf('(') + 1, originMessage.indexOf(')'))
			GsonFactory.gson
					.fromJson<HotSearchResponse>(message, HotSearchResponse::class.java)
					.hotwords.forEach {
				val hotSearch = HotSearch()
				hotSearch.href = it.url
				hotSearch.value = it.text
				hotSearch.date = StringUtil.getTodayInfo()
				hotSearchList.add(hotSearch)
			}
			HotSearchService.set(hotSearchList)
		} catch (e: Exception) {
			e.printStackTrace()
			throw KGCException(ExceptionCodeConstant.J_ERROR_PARSE)
		}
		return hotSearchList
	}

	override fun getKeyWord(): List<KeywordCategory> {
		val keywordCategoryList = ArrayList<KeywordCategory>()
		val apiResponse = RetrofitFactory.laGouRetrofit
				.create(LaGouAPI::class.java)
				.getKeyWord()
				.execute()
		if (!apiResponse.isSuccessful)
			throw KGCException(ExceptionCodeConstant.J_ERROR_INTERNET)
		val originResponse = apiResponse.body()?.string()
				?: throw KGCException(ExceptionCodeConstant.J_ERROR_EMPTY_RESPONSE)
		try {
			val doc = Jsoup.parse(originResponse)
			doc.select(".menu_box").forEach { element ->
				val menu_main = element.select(".menu_main")[0]
				val keywordCategory = KeywordCategory()
				keywordCategory.category = menu_main.select("h2")[0].text()
				val menu_sub = element.select(".menu_sub")[0]
				val dls = menu_sub.select("dl")
				val keywordGroupList = ArrayList<KeywordGroup>()
				dls.forEach { it ->
					val dt = it.select("dt")[0]
					val dd = it.select("dd")[0]
					val title = dt.text()
					val values = dd.select("a")
					val keywordGroup = KeywordGroup()
					keywordGroup.title = title
					keywordGroup.keywordList = values.map { it.text() }
					keywordGroupList.add(keywordGroup)
				}
				keywordCategory.keywordGroupList = keywordGroupList
				keywordCategory.date = StringUtil.getTodayInfo()
				keywordCategoryList.add(keywordCategory)
			}
			KeywordService.set(keywordCategoryList)
		} catch (e: Exception) {
			e.printStackTrace()
			throw KGCException(ExceptionCodeConstant.J_ERROR_PARSE)
		}
		return keywordCategoryList
	}
}