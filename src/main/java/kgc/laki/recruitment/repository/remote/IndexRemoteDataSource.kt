package kgc.laki.recruitment.repository.remote

import kgc.laki.recruitment.api.LaGouAPI
import kgc.laki.recruitment.factory.GsonFactory
import kgc.laki.recruitment.factory.RetrofitFactory
import kgc.laki.recruitment.model.HotSearch
import kgc.laki.recruitment.model.Keyword
import kgc.laki.recruitment.model.KeywordCategory
import kgc.laki.recruitment.model.KeywordGroup
import kgc.laki.recruitment.model.response.HotSearchResponse
import kgc.laki.recruitment.repository.dataSource.IndexDataSource
import org.jsoup.Jsoup

object IndexRemoteDataSource : IndexDataSource {
	override fun getHotSearch(): List<HotSearch> {
		val hotSearchList = ArrayList<HotSearch>()
		val apiResponse = RetrofitFactory.hotSearchRetrofit
				.create(LaGouAPI::class.java)
				.getHotSearch()
				.execute()
		if (apiResponse.isSuccessful) {
			val originMessage = apiResponse.body()!!.string()
			val message = originMessage.substring(originMessage.indexOf('(') + 1, originMessage.indexOf(')'))
			val jsons = GsonFactory.gson.fromJson<HotSearchResponse>(message, HotSearchResponse::class.java)
			jsons.hotwords.forEach {
				val hotSearch = HotSearch()
				hotSearch.href = it.url
				hotSearch.value = it.text
				hotSearchList.add(hotSearch)
			}
		}
		return hotSearchList
	}

	override fun getKeyWord(): List<KeywordCategory> {
		val keywordCategoryList = ArrayList<KeywordCategory>()
		val apiResponse = RetrofitFactory.laGouRetrofit
				.create(LaGouAPI::class.java)
				.getKeyWord()
				.execute()
		if (apiResponse.isSuccessful) {
			val originResponse = apiResponse.body()!!.string()
			val doc = Jsoup.parse(originResponse)
			doc.select(".menu_box").forEach { element ->
				val menu_main = element.select(".menu_main")[0]
				val keywordCategory = KeywordCategory()
				keywordCategory.category = menu_main.select("h2")[0].text()
				val menu_sub = element.select(".menu_sub")[0]
				val dls = menu_sub.select("dl")
				val keywordGroupList=ArrayList<KeywordGroup>()
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
				keywordCategory.keywordGroupList=keywordGroupList
				keywordCategoryList.add(keywordCategory)
			}
		}
		return keywordCategoryList
	}
}