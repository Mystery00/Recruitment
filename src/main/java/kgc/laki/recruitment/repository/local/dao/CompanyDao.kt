package kgc.laki.recruitment.repository.local.dao

import kgc.laki.recruitment.base.BaseDao
import kgc.laki.recruitment.model.CompanyInfo
import kgc.laki.recruitment.utils.JDBCUtil
import java.util.*

object CompanyDao : BaseDao<CompanyInfo> {
	override fun insert(data: CompanyInfo) {
		val sql = "insert into db_bjyzpw.tb_company_info(ci_id, ci_name, ci_company_icon_url, ci_company_url, ci_company_introduce, ci_labels, ci_introduction_string, ci_pictures, ci_hy, ci_gm, ci_city, ci_jd, ci_location) values (?,?,?,?,?,?,?,?,?,?,?,?,?)"
		JDBCUtil.update(sql, arrayOf(data.companyID.toString(), data.companyName, data.companyIconUrl, data.companyUrl, data.companyIntroduce, Arrays.toString(data.labels.toTypedArray()), data.introductionString, Arrays.toString(data.pictures.toTypedArray()), data.hy, data.gm, data.city, data.jd, data.location))
	}

	override fun delete(date: String, data: CompanyInfo?) {}

	fun delete(id: String) {
		val sql = "delete from db_bjyzpw.tb_company_info where ci_id = ?"
		JDBCUtil.update(sql, arrayOf(id))
	}

	override fun queryAll(date: String): List<CompanyInfo> = emptyList()

	fun query(id: String): CompanyInfo? {
		val sql = "select * from db_bjyzpw.tb_company_info where ci_id=?"
		val map = JDBCUtil.getMap(sql, arrayOf(id))
		if (map.isEmpty())
			return null
		val companyInfo = CompanyInfo()
		companyInfo.companyID = map["ci_id"]!!.toInt()
		companyInfo.companyName = map["ci_name"]!!
		companyInfo.companyIconUrl = map["ci_company_icon_url"]!!
		companyInfo.companyUrl = map["ci_company_url"]!!
		companyInfo.companyIntroduce = map["ci_company_introduce"]!!
		val ci_labels = map["ci_labels"]!!
		companyInfo.labels = ci_labels.substring(1, ci_labels.length - 1).split(",")
		companyInfo.introductionString = map["ci_introduction_string"]!!
		val ci_pictures = map["ci_pictures"]!!
		companyInfo.pictures = ci_pictures.substring(1, ci_pictures.length - 1).split(",")
		companyInfo.hy = map["ci_hy"]!!
		companyInfo.gm = map["ci_gm"]!!
		companyInfo.city = map["ci_city"]!!
		companyInfo.jd = map["ci_jd"]!!
		companyInfo.location = map["ci_location"]!!
		return companyInfo
	}

	override fun update(data: CompanyInfo) {}
}