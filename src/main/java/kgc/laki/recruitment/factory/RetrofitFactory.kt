package kgc.laki.recruitment.factory

import kgc.laki.recruitment.constant.PropertiesConstant
import kgc.laki.recruitment.utils.PropertiesUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
	private val client = OkHttpClient.Builder()
			.retryOnConnectionFailure(true)
			.connectTimeout(20, TimeUnit.SECONDS)
			.readTimeout(20, TimeUnit.SECONDS)
			.writeTimeout(20, TimeUnit.SECONDS)
			.build()

	val laGouRetrofit = Retrofit.Builder()
			.baseUrl(PropertiesUtil.getUrl(PropertiesConstant.LA_GOU))
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build()!!

	val hotSearchRetrofit = Retrofit.Builder()
			.baseUrl(PropertiesUtil.getUrl(PropertiesConstant.HOT_SEARCH))
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build()!!
}