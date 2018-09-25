package kgc.laki.recruitment.model.response

import kgc.laki.recruitment.model.SearchBean

class SearchChoose {
	lateinit var query: String//关键字
	lateinit var city: List<String>//城市 单选
	var isSchoolJob: Boolean = true//是否是校招 是-1 否-0 单选
	lateinit var gm: List<String>//公司规模 少于15人、15-50人、50-150人、150-500人、500-2000人、2000人以上 可多选（中间用,分隔）
	lateinit var hy: List<String>//行业领域 移动互联网、电子商务、金融、企业服务、教育、文化娱乐、游戏、O2O、硬件、社交网络、旅游、医疗健康、生活服务、信息安全、数据服务、广告营销、分类信息、招聘、其他、区块链、人工智能 可多选（中间用,分隔）
	lateinit var jd: List<String>//融资阶段 未融资、天使轮、A轮、B轮、C轮、D轮、D轮及以上、上市公司、不需要融资 可多选（中间用,分隔）
	lateinit var xl: List<String>//学历要求 大专、本科、硕士、博士、不要求 可多选（中间用,分隔）
	lateinit var gx: List<String>//工作性质 应届、实习 单选
	lateinit var yx: List<String>//月薪 2k以下、2k-5k、5k-10k、10k-15k、15-25k、25k-50k、50k以上
	lateinit var px: List<String>//排序 默认[default]、最新[new]
	lateinit var searchBean: SearchBean
}