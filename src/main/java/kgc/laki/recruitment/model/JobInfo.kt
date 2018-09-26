package kgc.laki.recruitment.model

import java.io.Serializable

class JobInfo : Serializable {
	lateinit var jobName: String
	lateinit var salary: String
	lateinit var city: String
	lateinit var exp: String
	lateinit var grade: String
	lateinit var gx: String
	lateinit var tag: List<String>
	lateinit var temptation: String
	lateinit var description: String
	lateinit var workAddress: String
	lateinit var publishTIme: String
	lateinit var companyName: String
	lateinit var companyIconUrl: String
	lateinit var companyID: String
	lateinit var companyHY: String
	lateinit var jd: String
	lateinit var gm: String
	lateinit var companyWebsite: String
}