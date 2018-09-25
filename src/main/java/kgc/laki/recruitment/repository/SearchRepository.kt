package kgc.laki.recruitment.repository

import kgc.laki.recruitment.api.LaGouAPI
import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.factory.RetrofitFactory
import kgc.laki.recruitment.model.SearchBean
import kgc.laki.recruitment.repository.remote.SearchRemoteDataSource
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.exception.KGCException
import javax.servlet.http.HttpServletRequest

object SearchRepository {
	fun doSearch(request: HttpServletRequest,
				 query: String,
				 city: String,//城市
				 isSchoolJob: Boolean = true,//是否是校招
				 gm: Array<String> = arrayOf("不限"),//公司规模
				 hy: Array<String> = arrayOf("不限"),//行业领域
				 jd: Array<String> = arrayOf("不限"),//融资阶段
				 xl: Array<String> = arrayOf("不限"),//学历要求
				 gx: String = "不限",//工作性质
				 yx: String = "不限",//月薪
				 px: String = "default"//排序
	) {
		val searchBean = getSearchBean(query, city, isSchoolJob, gm, hy, jd, xl, gx, yx, px)
		val html = doSearch(searchBean)
		val searchChoose = SearchRemoteDataSource.getSearchChoose(query, searchBean, html)
		val companyJob = SearchRemoteDataSource.getCompanyJob(query, searchBean)
		SessionUtil.setSearchChoose(request, searchChoose)
		SessionUtil.setCompanyJob(request, companyJob)
	}

	private fun getSearchBean(query: String,
							  city: String,//城市
							  isSchoolJob: Boolean,//是否是校招
							  gm: Array<String>,//公司规模
							  hy: Array<String>,//行业领域
							  jd: Array<String>,//融资阶段
							  xl: Array<String>,//学历要求
							  gx: String,//工作性质
							  yx: String,//月薪
							  px: String//排序
	): SearchBean {
		val searchBean = SearchBean()
		searchBean.query = query
		searchBean.city = city
		searchBean.isSchoolJob = if (isSchoolJob) 1 else 0
		val gmString = StringBuilder()
		gm.forEachIndexed { index, s ->
			if (index != 0)
				gmString.append(',')
			gmString.append(s)
		}
		searchBean.gm = gmString.toString()
		val hyString = StringBuilder()
		hy.forEachIndexed { index, s ->
			if (index != 0)
				hyString.append(',')
			hyString.append(s)
		}
		searchBean.hy = hyString.toString()
		val jdString = StringBuilder()
		jd.forEachIndexed { index, s ->
			if (index != 0)
				jdString.append(',')
			jdString.append(s)
		}
		searchBean.jd = jdString.toString()
		val xlString = StringBuilder()
		xl.forEachIndexed { index, s ->
			if (index != 0)
				xlString.append(',')
			xlString.append(s)
		}
		searchBean.xl = xlString.toString()
		searchBean.gx = gx
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
						searchBean.yx,
						searchBean.px)
				.execute()
		if (!apiResponse.isSuccessful)
			throw KGCException(ExceptionCodeConstant.J_ERROR_INTERNET)
		return apiResponse.body()?.string()
				?: throw KGCException(ExceptionCodeConstant.J_ERROR_EMPTY_RESPONSE)
	}
}