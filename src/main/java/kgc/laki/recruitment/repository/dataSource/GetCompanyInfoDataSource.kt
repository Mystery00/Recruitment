package kgc.laki.recruitment.repository.dataSource

import kgc.laki.recruitment.model.CompanyInfo

interface GetCompanyInfoDataSource {
	fun get(positionID: String, html: String): CompanyInfo
}