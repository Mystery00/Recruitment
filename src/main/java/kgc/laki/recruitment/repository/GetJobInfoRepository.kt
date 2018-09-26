package kgc.laki.recruitment.repository

import kgc.laki.recruitment.api.LaGouAPI
import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.factory.RetrofitFactory
import kgc.laki.recruitment.repository.remote.GetJobInfoRemoteDataSource
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.exception.KGCException
import javax.servlet.http.HttpServletRequest

object GetJobInfoRepository {
	fun getInfo(request: HttpServletRequest,positionID: String) {
		val html = doRequest(positionID)
		val jobInfo = GetJobInfoRemoteDataSource.get(positionID, html)
		SessionUtil.setJobInfo(request, jobInfo)
	}

	private fun doRequest(positionID: String): String {
		val apiResponse = RetrofitFactory.laGouRetrofit
				.create(LaGouAPI::class.java)
				.getJobInfo(positionID)
				.execute()
		if (!apiResponse.isSuccessful)
			throw KGCException(ExceptionCodeConstant.J_ERROR_INTERNET)
		return apiResponse.body()?.string()
				?: throw KGCException(ExceptionCodeConstant.J_ERROR_EMPTY_RESPONSE)
	}
}