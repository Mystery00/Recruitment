package kgc.laki.recruitment.factory

import kgc.laki.recruitment.constant.PropertiesConstant
import kgc.laki.recruitment.utils.PropertiesUtil
import kgc.laki.recruitment.utils.cookie.LoadCookiesInterceptor
import kgc.laki.recruitment.utils.cookie.SaveCookiesInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
	private val client = OkHttpClient.Builder()
			.retryOnConnectionFailure(true)
			.connectTimeout(20, TimeUnit.SECONDS)
			.readTimeout(20, TimeUnit.SECONDS)
			.writeTimeout(20, TimeUnit.SECONDS)
			.addInterceptor(LoadCookiesInterceptor())
			.addInterceptor(SaveCookiesInterceptor())
//			.addInterceptor(HttpLoggingInterceptor()
//					.setLevel(HttpLoggingInterceptor.Level.HEADERS))
			.build()

	val laGouRetrofit = Retrofit.Builder()
			.baseUrl(PropertiesUtil.getUrl(PropertiesConstant.LA_GOU))
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build()!!

	val laGouGetResultFirstRetrofit = Retrofit.Builder()
			.baseUrl(PropertiesUtil.getUrl(PropertiesConstant.LA_GOU_GET_RESULT_FIRST_URL))
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build()!!

	val hotSearchRetrofit = Retrofit.Builder()
			.baseUrl(PropertiesUtil.getUrl(PropertiesConstant.HOT_SEARCH))
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build()!!
}