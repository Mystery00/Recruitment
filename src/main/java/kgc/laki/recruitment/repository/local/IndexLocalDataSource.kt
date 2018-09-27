package kgc.laki.recruitment.repository.local

import kgc.laki.recruitment.model.HotSearch
import kgc.laki.recruitment.model.KeywordCategory
import kgc.laki.recruitment.repository.dataSource.IndexDataSource
import kgc.laki.recruitment.repository.local.service.HotSearchService
import kgc.laki.recruitment.repository.local.service.KeywordService

object IndexLocalDataSource : IndexDataSource {
	override fun getHotSearch(): List<HotSearch> = HotSearchService.get(null)

	override fun getKeyWord(): List<KeywordCategory> = KeywordService.get(null)
}