package kgc.laki.recruitment.repository.dataSource

import kgc.laki.recruitment.model.CompanyJob
import kgc.laki.recruitment.model.SearchBean
import kgc.laki.recruitment.model.response.SearchChoose

interface SearchDataSource {
	fun getSearchChoose(query: String, searchBean: SearchBean, html: String?): SearchChoose

	fun getCompanyJob(query: String, searchBean: SearchBean): List<CompanyJob>
}