package kgc.laki.recruitment.repository.remote

import kgc.laki.recruitment.model.JobInfo
import kgc.laki.recruitment.repository.dataSource.GetJobInfoDataSource
import org.jsoup.Jsoup

object GetJobInfoRemoteDataSource : GetJobInfoDataSource {
	override fun get(positionID: String, html: String): JobInfo {
		val jobInfo = JobInfo()
		val doc = Jsoup.parse(html)
		val position_head = doc.select(".position-head")[0]
		val position_content_l = position_head.select(".position-content-l")[0]
		val job_name = position_content_l.select(".job-name")[0]
		jobInfo.jobName = job_name.select(".name")[0].text()
		val job_request = position_content_l.select(".job_request")[0]
		val span = job_request.select("p")[0].select("span")
		jobInfo.salary = span[0].text()
		jobInfo.city = span[1].text()
		jobInfo.exp = span[2].text()
		jobInfo.grade = span[3].text()
		jobInfo.gx = span[4].text()
		val position_label = job_request.select(".position-label")[0]
		jobInfo.tag = position_label.select("li").map { it.text() }
		val publish_time = job_request.select(".publish_time")[0].text().split(" ")
		val list = publish_time.filter { it != "" }
		jobInfo.publishTIme = list[0]
		return jobInfo
	}
}