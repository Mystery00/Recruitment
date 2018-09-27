package kgc.laki.recruitment.repository

import kgc.laki.recruitment.api.LaGouAPI
import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.factory.RetrofitFactory
import kgc.laki.recruitment.repository.local.GetCompanyInfoLocalDataSource
import kgc.laki.recruitment.repository.remote.GetCompanyInfoRemoteDataSource
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.exception.KGCException
import java.net.UnknownHostException
import javax.servlet.http.HttpServletRequest

object GetCompanyInfoRepository {
	fun getInfo(request: HttpServletRequest, positionID: String) {
		val company = try {
			val html = doRequest(positionID)
			GetCompanyInfoRemoteDataSource.get(positionID, html)
		} catch (e: Exception) {
			when (e) {
				is KGCException -> SessionUtil.setException(request, e)
				is UnknownHostException -> SessionUtil.setException(request, KGCException(ExceptionCodeConstant.J_ERROR_INTERNET))
				else -> throw e
			}
			GetCompanyInfoLocalDataSource.get(positionID, "")
		}
		SessionUtil.setJobInfo(request, company)
	}

	private fun doRequest(positionID: String): String {
		val apiResponse = RetrofitFactory.laGouRetrofit
				.create(LaGouAPI::class.java)
				.getCompanyInfo(positionID)
				.execute()
		if (!apiResponse.isSuccessful)
			throw KGCException(ExceptionCodeConstant.J_ERROR_INTERNET)
		return apiResponse.body()?.string()
				?: throw KGCException(ExceptionCodeConstant.J_ERROR_EMPTY_RESPONSE)
	}
}