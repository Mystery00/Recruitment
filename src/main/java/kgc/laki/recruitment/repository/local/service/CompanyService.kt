package kgc.laki.recruitment.repository.local.service

import kgc.laki.recruitment.model.CompanyInfo
import kgc.laki.recruitment.repository.local.dao.CompanyDao

object CompanyService {
	fun set(companyInfo: CompanyInfo) {
		CompanyDao.delete(companyInfo.companyID.toString())
		CompanyDao.insert(companyInfo)
	}

	fun get(id: String): CompanyInfo? = CompanyDao.query(id)
}