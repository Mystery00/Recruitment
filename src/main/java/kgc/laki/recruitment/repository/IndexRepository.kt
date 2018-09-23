package kgc.laki.recruitment.repository

import kgc.laki.recruitment.model.HotSearch
import kgc.laki.recruitment.model.KeywordCategory
import kgc.laki.recruitment.repository.remote.IndexRemoteDataSource

object IndexRepository {
	fun getKeyWord(): List<KeywordCategory> {
		val remoteData = IndexRemoteDataSource.getKeyWord()
		return remoteData
	}

	fun getHotSearch(): List<HotSearch> {
		val remoteData = IndexRemoteDataSource.getHotSearch()
		return remoteData
	}
}