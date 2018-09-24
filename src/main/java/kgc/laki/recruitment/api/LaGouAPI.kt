package kgc.laki.recruitment.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface LaGouAPI {
	@GET("/hotword")
	fun getHotSearch(@Query("callback") callback: String = "json"): Call<ResponseBody>

	@GET("/")
	fun getKeyWord(): Call<ResponseBody>

	@GET("/jobs/list_{query}")
	fun search(@Path("query") query: String,
			   @Query("city") city: String,//城市
			   @Query("isSchoolJob") isSchoolJob: Int,//是否是校招
			   @Query("gm") gm: String,//公司规模
			   @Query("hy") hy: String,//行业领域
			   @Query("jd") jd: String,//融资阶段
			   @Query("xl") xl: String,//学历要求
			   @Query("gx") gx: String,//工作性质
			   @Query("yx") yx: String,//月薪
			   @Query("px") px: String//排序
	): Call<ResponseBody>

	@GET("/collect")
	fun getSearchResultFirst():Call<ResponseBody>

	@FormUrlEncoded
	@POST("/jobs/positionAjax.json?needAddtionalResult=false")
	fun getSearchResult(@Field("pn") page: Int, @Field("kd") query: String, @Field("first") first: String = "false"): Call<ResponseBody>
}