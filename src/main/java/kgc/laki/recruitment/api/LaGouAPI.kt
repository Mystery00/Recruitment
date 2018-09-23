package kgc.laki.recruitment.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LaGouAPI {
	@GET("/hotword")
	fun getHotSearch(@Query("callback") callback: String = "json"): Call<ResponseBody>
}