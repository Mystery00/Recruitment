package kgc.laki.recruitment.repository.local.dao

import kgc.laki.recruitment.base.BaseDao
import kgc.laki.recruitment.model.JobInfo
import kgc.laki.recruitment.utils.JDBCUtil
import java.util.*

object JobInfoDao : BaseDao<JobInfo> {
	override fun insert(data: JobInfo) {
		val sql = "insert into db_bjyzpw.tb_job_info (ji_id, ji_name, ji_salary, ji_city, ji_exp, ji_grade, ji_gx, ji_tag, ji_temptation, ji_description, ji_word_address, ji_publish_time, ji_company_name, ji_company_icon_url, ji_company_id, ji_hy, ji_jd, ji_gm, ji_company_website) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
		JDBCUtil.update(sql, arrayOf(data.jobID.toString(), data.jobName, data.salary, data.city, data.exp, data.grade, data.gx, Arrays.toString(data.tag.toTypedArray()), data.temptation, data.description, data.workAddress, data.publishTIme, data.companyName, data.companyIconUrl, data.companyID, data.companyHY, data.jd, data.gm, data.companyWebsite))
	}

	override fun delete(date: String, data: JobInfo?) {}

	fun delete(id: String) {
		val sql = "delete from db_bjyzpw.tb_job_info where ji_id=?"
		JDBCUtil.update(sql, arrayOf(id))
	}

	override fun queryAll(date: String): List<JobInfo> = emptyList()

	fun query(id: String): JobInfo? {
		val sql = "select * from db_bjyzpw.tb_job_info where ji_id=?"
		val map = JDBCUtil.getMap(sql, arrayOf(id))
		if (map.isEmpty())
			return null
		val jobInfo = JobInfo()
		jobInfo.jobID = map["ji_id"]!!.toInt()
		jobInfo.jobName = map["ji_name"]!!
		jobInfo.salary = map["ji_salary"]!!
		jobInfo.city = map["ji_city"]!!
		jobInfo.exp = map["ji_exp"]!!
		jobInfo.grade = map["ji_grade"]!!
		jobInfo.gx = map["ji_gx"]!!
		val tag = map["ji_tag"]!!
		jobInfo.tag = tag.substring(1, tag.length - 1).split(",")
		jobInfo.temptation = map["ji_temptation"]!!
		jobInfo.description = map["ji_description"]!!
		jobInfo.workAddress = map["ji_word_address"]!!
		jobInfo.publishTIme = map["ji_publish_time"]!!
		jobInfo.companyName = map["ji_company_name"]!!
		jobInfo.companyIconUrl = map["ji_company_icon_url"]!!
		jobInfo.companyID = map["ji_company_id"]!!
		jobInfo.companyHY = map["ji_hy"]!!
		jobInfo.jd = map["ji_jd"]!!
		jobInfo.gm = map["ji_gm"]!!
		jobInfo.companyWebsite = map["ji_company_website"]!!
		return jobInfo
	}

	override fun update(data: JobInfo) {}
}