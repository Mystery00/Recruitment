package kgc.laki.recruitment.repository.remote

import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.factory.GsonFactory
import kgc.laki.recruitment.model.CompanyInfo
import kgc.laki.recruitment.model.response.CompanyInfoResponse
import kgc.laki.recruitment.repository.dataSource.GetCompanyInfoDataSource
import kgc.laki.recruitment.utils.exception.KGCException
import org.jsoup.Jsoup
import java.lang.Exception

object GetCompanyInfoRemoteDataSource : GetCompanyInfoDataSource {
	override fun get(positionID: String, html: String): CompanyInfo {
		val company = CompanyInfo()
		try {
			val doc = Jsoup.parse(html)
			val companyInfoData = doc.select("#companyInfoData")[0].data()
			val companyInfoResponse = GsonFactory.gson.fromJson<CompanyInfoResponse>(companyInfoData, CompanyInfoResponse::class.java)
			company.companyName = companyInfoResponse.coreInfo.companyName
			company.companyIconUrl = "https://www.lgstatic.com/thumbnail_300x300/${companyInfoResponse.coreInfo.logo}"
			company.companyUrl = companyInfoResponse.coreInfo.companyUrl
			company.companyIntroduce = companyInfoResponse.coreInfo.companyIntroduce
			company.labels = companyInfoResponse.labels
			company.introductionString = companyInfoResponse.introduction.companyProfile
			company.pictures = companyInfoResponse.introduction.pictures.map { "https://www.lgstatic.com/thumbnail_1000x0/${it.companyPicUrl}" }
			company.hy = companyInfoResponse.baseInfo.industryField
			company.gm = companyInfoResponse.baseInfo.companySize
			company.city = companyInfoResponse.baseInfo.city
			company.jd = companyInfoResponse.baseInfo.financeStage
			val address = companyInfoResponse.addressList[0]
			company.location = "${address.province} - ${address.district}  ${address.detailAddress}"
		} catch (e: Exception) {
			e.printStackTrace()
			throw KGCException(ExceptionCodeConstant.J_ERROR_PARSE)
		}
		return company
	}
}