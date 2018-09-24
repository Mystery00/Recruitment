package kgc.laki.recruitment.model

class CompanyJob {
	lateinit var jobName: String
	lateinit var location: String
	lateinit var publishTime: String
	lateinit var money: String
	lateinit var exp: String
	lateinit var grade: String
	lateinit var tag: List<String>

	lateinit var companyName:String
	lateinit var hy:String//行业领域
	lateinit var jd:String//融资阶段
	lateinit var personNum:String//公司规模
	lateinit var temptation:String//职业诱惑
	lateinit var companyImgUrl:String// 大小 60x60
}