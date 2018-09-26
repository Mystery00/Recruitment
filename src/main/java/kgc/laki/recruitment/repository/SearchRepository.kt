package kgc.laki.recruitment.repository

import kgc.laki.recruitment.api.LaGouAPI
import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.factory.RetrofitFactory
import kgc.laki.recruitment.model.SearchBean
import kgc.laki.recruitment.repository.remote.SearchRemoteDataSource
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.StringUtil
import kgc.laki.recruitment.utils.exception.KGCException
import javax.servlet.http.HttpServletRequest

object SearchRepository {
	fun doSearch(request: HttpServletRequest,
				 searchBean: SearchBean) {
		println(searchBean.toString())
		val html = doSearch(searchBean)
		val searchChoose = SearchRemoteDataSource.getSearchChoose(searchBean.query, searchBean, html)
		val companyJob = SearchRemoteDataSource.getCompanyJob(searchBean.query, searchBean)
		SessionUtil.setSearchChoose(request, searchChoose)
		SessionUtil.setCompanyJob(request, companyJob)
	}

	fun getSearchBean(query: String,
					  city: String = "全国",//城市
					  isSchoolJob: Boolean = false,//是否是校招
					  gm: Array<String> = emptyArray(),//公司规模
					  hy: Array<String> = emptyArray(),//行业领域
					  jd: Array<String> = emptyArray(),//融资阶段
					  xl: Array<String> = emptyArray(),//学历要求
					  gx: String = "不限",//工作性质
					  gj: String = "不限",//工作经验
					  yx: String = "不限",//月薪
					  px: String = "默认"//排序
	): SearchBean {
		val searchBean = SearchBean()
		searchBean.query = query
		searchBean.city = city
		searchBean.isSchoolJob = if (isSchoolJob) 1 else 0
		searchBean.gm = StringUtil.arrayToString(gm, ",")
		searchBean.hy = StringUtil.arrayToString(hy, ",")
		searchBean.jd = StringUtil.arrayToString(jd, ",")
		searchBean.xl = StringUtil.arrayToString(xl, ",")
		searchBean.gx = gx
		searchBean.gj = gj
		searchBean.yx = yx
		searchBean.px = px
		searchBean.toDoSearch()
		return searchBean
	}

	private fun doSearch(searchBean: SearchBean): String {
		val apiResponse = RetrofitFactory.laGouRetrofit
				.create(LaGouAPI::class.java)
				.search(searchBean.query,
						searchBean.city,
						searchBean.isSchoolJob,
						searchBean.gm,
						searchBean.hy,
						searchBean.jd,
						searchBean.xl,
						searchBean.gx,
						searchBean.gj,
						searchBean.yx,
						searchBean.px)
				.execute()
		if (!apiResponse.isSuccessful)
			throw KGCException(ExceptionCodeConstant.J_ERROR_INTERNET)
		return apiResponse.body()?.string()
				?: throw KGCException(ExceptionCodeConstant.J_ERROR_EMPTY_RESPONSE)
	}
}