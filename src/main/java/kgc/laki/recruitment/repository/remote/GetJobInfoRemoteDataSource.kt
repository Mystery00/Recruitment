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
		val job_detail = doc.select("#job_detail")[0]
		val job_advantage = job_detail.select(".job-advantage")[0]
		jobInfo.temptation = job_advantage.select("p")[0].text()
		val job_bt = job_detail.select(".job_bt")[0]
//		jobInfo.description=Jsoup.parse(job)
		val job_company = doc.select("#job_company")[0]
		val dt = job_company.select("dt")[0]
		jobInfo.companyIconUrl = dt.select("img")[0].attr("src")
		jobInfo.companyName = dt.select("h2")[0].text().trim().split(" ")[0]
		val lagouUrl = dt.select("a")[0].attr("href")
		jobInfo.companyID = "http:" + lagouUrl.substring(lagouUrl.lastIndexOf('/') + 1, lagouUrl.lastIndexOf('.'))
		val dd = job_company.select(".c_feature")[0]
		val lis = dd.select("li")
		jobInfo.companyHY = lis[0].text().split(" ")[0]
		jobInfo.jd = lis[1].text().split(" ")[0]
		jobInfo.gm = lis[lis.size - 2].text().split(" ")[0]
		jobInfo.companyWebsite = lis[lis.size - 1].select("a")[0].attr("href")
		return jobInfo
	}
}