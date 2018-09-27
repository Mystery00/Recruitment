package kgc.laki.recruitment.repository.remote

import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.factory.GsonFactory
import kgc.laki.recruitment.model.CompanyInfo
import kgc.laki.recruitment.model.response.CompanyInfoResponse
import kgc.laki.recruitment.repository.dataSource.GetCompanyInfoDataSource
import kgc.laki.recruitment.repository.local.service.CompanyService
import kgc.laki.recruitment.utils.exception.KGCException
import org.jsoup.Jsoup
import java.lang.Exception

object GetCompanyInfoRemoteDataSource : GetCompanyInfoDataSource {
	override fun get(positionID: String, html: String): CompanyInfo {
		val companyInfo = CompanyInfo()
		try {
			companyInfo.companyID = positionID.toInt()
			val doc = Jsoup.parse(html)
			val companyInfoData = doc.select("#companyInfoData")[0].data()
			val companyInfoResponse = GsonFactory.gson.fromJson<CompanyInfoResponse>(companyInfoData, CompanyInfoResponse::class.java)
			companyInfo.companyName = companyInfoResponse.coreInfo.companyName
			companyInfo.companyIconUrl = "https://www.lgstatic.com/thumbnail_300x300/${companyInfoResponse.coreInfo.logo}"
			companyInfo.companyUrl = companyInfoResponse.coreInfo.companyUrl
			companyInfo.companyIntroduce = companyInfoResponse.coreInfo.companyIntroduce
			companyInfo.labels = companyInfoResponse.labels
			companyInfo.introductionString = companyInfoResponse.introduction.companyProfile
			companyInfo.pictures = companyInfoResponse.introduction.pictures.map { "https://www.lgstatic.com/thumbnail_1000x0/${it.companyPicUrl}" }
			companyInfo.hy = companyInfoResponse.baseInfo.industryField
			companyInfo.gm = companyInfoResponse.baseInfo.companySize
			companyInfo.city = companyInfoResponse.baseInfo.city
			companyInfo.jd = companyInfoResponse.baseInfo.financeStage
			val address = companyInfoResponse.addressList[0]
			companyInfo.location = "${address.province} - ${address.district}  ${address.detailAddress}"
			CompanyService.set(companyInfo)
		} catch (e: Exception) {
			e.printStackTrace()
			throw KGCException(ExceptionCodeConstant.J_ERROR_PARSE)
		}
		return companyInfo
	}
}