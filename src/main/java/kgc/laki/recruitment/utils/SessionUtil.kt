@file:Suppress("UNCHECKED_CAST")

package kgc.laki.recruitment.utils

import kgc.laki.recruitment.constant.SessionConstant
import kgc.laki.recruitment.model.CompanyJob
import kgc.laki.recruitment.model.HotSearch
import kgc.laki.recruitment.model.JobInfo
import kgc.laki.recruitment.model.KeywordCategory
import kgc.laki.recruitment.model.response.SearchChoose
import kgc.laki.recruitment.utils.exception.KGCException
import java.util.ArrayList
import javax.servlet.http.HttpServletRequest

object SessionUtil {
	fun <T> get(request: HttpServletRequest, keyName: String): T? = request.session.getAttribute(keyName) as T?

	fun put(request: HttpServletRequest, keyName: String, value: Any?) = request.session.setAttribute(keyName, value)

	fun getExceptionThenDestory(request: HttpServletRequest): KGCException? {
		val exception = get<KGCException>(request, SessionConstant.KGC_EXCEPTION)
		request.removeAttribute(SessionConstant.KGC_EXCEPTION)
		return exception
	}

	fun setException(request: HttpServletRequest, exception: KGCException?) = put(request, SessionConstant.KGC_EXCEPTION, exception)

	fun getHotSearch(request: HttpServletRequest): List<HotSearch> = get<List<HotSearch>>(request, SessionConstant.HOT_SEARCH)
			?: ArrayList()

	fun setHotSearch(request: HttpServletRequest, value: List<HotSearch>) = put(request, SessionConstant.HOT_SEARCH, value)

	fun getKeyWord(request: HttpServletRequest): List<KeywordCategory> = get<List<KeywordCategory>>(request, SessionConstant.KEY_WORD)
			?: ArrayList()

	fun setKeyWord(request: HttpServletRequest, value: List<KeywordCategory>) = put(request, SessionConstant.KEY_WORD, value)

	fun getSearchChoose(request: HttpServletRequest): SearchChoose = get<SearchChoose>(request, SessionConstant.SEARCH_CHOOSE)
			?: SearchChoose()

	fun setSearchChoose(request: HttpServletRequest, value: SearchChoose) = put(request, SessionConstant.SEARCH_CHOOSE, value)

	fun getCompanyJob(request: HttpServletRequest): List<CompanyJob> = get<List<CompanyJob>>(request, SessionConstant.COMPANY_JOB)
			?: ArrayList()

	fun setCompanyJob(request: HttpServletRequest, value: List<CompanyJob>) = put(request, SessionConstant.COMPANY_JOB, value)

	fun getJobInfo(request: HttpServletRequest): JobInfo = get<JobInfo>(request, SessionConstant.JOB_INFO)
			?: JobInfo()

	fun setJobInfo(request: HttpServletRequest, value: JobInfo) = put(request, SessionConstant.JOB_INFO, value)

//	fun getCompanyInfo(request: HttpServletRequest): JobInfo = get<JobInfo>(request, SessionConstant.JOB_INFO)
//			?: CompanyInfo()
//
//	fun setJobInfo(request: HttpServletRequest, value: JobInfo) = put(request, SessionConstant.JOB_INFO, value)
}