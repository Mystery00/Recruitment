package kgc.laki.recruitment.repository.local

import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.model.CompanyInfo
import kgc.laki.recruitment.repository.dataSource.GetCompanyInfoDataSource
import kgc.laki.recruitment.repository.local.service.CompanyService
import kgc.laki.recruitment.utils.exception.KGCException

object GetCompanyInfoLocalDataSource : GetCompanyInfoDataSource {
	override fun get(positionID: String, html: String): CompanyInfo {
		val companyInfo = CompanyService.get(positionID)
		if (companyInfo == null)
			throw KGCException(ExceptionCodeConstant.J_ERROR_EMPTY_RESPONSE)
		else
			return companyInfo
	}
}