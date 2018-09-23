package kgc.laki.recruitment.repository.dataSource

import kgc.laki.recruitment.model.HotSearch
import kgc.laki.recruitment.model.KeywordCategory

interface IndexDataSource {
	fun getHotSearch(): List<HotSearch>

	fun getKeyWord(): List<KeywordCategory>
}