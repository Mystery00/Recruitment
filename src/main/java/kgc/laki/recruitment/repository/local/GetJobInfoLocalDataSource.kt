package kgc.laki.recruitment.repository.local

import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.model.JobInfo
import kgc.laki.recruitment.repository.dataSource.GetJobInfoDataSource
import kgc.laki.recruitment.repository.local.service.JobInfoService
import kgc.laki.recruitment.utils.exception.KGCException

object GetJobInfoLocalDataSource:GetJobInfoDataSource {
	override fun get(positionID: String, html: String): JobInfo {
		val companyInfo = JobInfoService.get(positionID)
		if (companyInfo == null)
			throw KGCException(ExceptionCodeConstant.J_ERROR_EMPTY_RESPONSE)
		else
			return companyInfo
	}
}