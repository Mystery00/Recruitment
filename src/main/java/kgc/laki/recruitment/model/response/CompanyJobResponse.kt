package kgc.laki.recruitment.model.response

class CompanyJobResponse {
	lateinit var content: ContentBean

	class ContentBean {
		var pageNo: Int = 0
		var pageSize: Int = 0
		lateinit var positionResult: PositionResultBean

		class PositionResultBean {
			var totalCount: Int = 0
			var resultSize: Int = 0
			lateinit var locationInfo: LocationInfoBean
			lateinit var result: List<ResultBean>

			class LocationInfoBean {
				/**
				 * businessZone : null
				 * locationCode : null
				 * queryByGisCode : false
				 * isAllhotBusinessZone : false
				 * city : 成都
				 * district : null
				 */

				lateinit var businessZone: Any
				lateinit var locationCode: Any
				var isQueryByGisCode: Boolean = false
				var isIsAllhotBusinessZone: Boolean = false
				lateinit var city: String
				lateinit var district: Any
			}

			class ResultBean {
				/**
				 * companyId : 102581
				 * companyShortName : 100课堂
				 * positionId : 2891727
				 * industryField : 移动互联网,教育
				 * education : 本科
				 * workYear : 1-3年
				 * city : 成都
				 * positionAdvantage : 前沿技术,技术导向,团队融洽,五险一金
				 * createTime : 2018-09-21 13:30:48
				 * salary : 6k-12k
				 * positionName : 测试工程师
				 * companySize : 150-500人
				 * companyLogo : image2/M00/0F/EB/CgqLKVYrUnKAS-7KAAATqc8gnA0277.jpg?cc=0.0753089243080467
				 * financeStage : A轮
				 * approve : 1
				 * jobNature : 全职
				 * companyLabelList : ["节日礼物","带薪年假","绩效奖金","年度旅游"]
				 * publisherId : 4388719
				 * score : 0
				 * district : 高新区
				 * positionLables : ["信息安全","移动端","脚本","自动化"]
				 * industryLables : ["信息安全","移动端","脚本","自动化"]
				 * businessZones : null
				 * longitude : 104.061207
				 * latitude : 30.546235
				 * imState : sevenDays
				 * lastLogin : 1537523350000
				 * explain : null
				 * plus : null
				 * pcShow : 0
				 * appShow : 0
				 * deliver : 0
				 * gradeDescription : null
				 * promotionScoreExplain : null
				 * firstType : 开发|测试|运维类
				 * secondType : 测试
				 * isSchoolJob : 0
				 * subwayline : 1号线
				 * stationname : 世纪城
				 * linestaion : 1号线_天府五街;1号线_天府三街;1号线_世纪城
				 * thirdType : 测试工程师
				 * skillLables : ["移动端","脚本","自动化"]
				 * hitags : null
				 * resumeProcessRate : 100
				 * resumeProcessDay : 1
				 * formatCreateTime : 3天前发布
				 * companyFullName : 北京亿佰教育科技有限公司
				 * adWord : 0
				 */

				var companyId: Int = 0
				lateinit var companyShortName: String
				var positionId: Int = 0
				lateinit var industryField: String
				lateinit var education: String
				lateinit var workYear: String
				lateinit var city: String
				lateinit var positionAdvantage: String
				lateinit var createTime: String
				lateinit var salary: String
				lateinit var positionName: String
				lateinit var companySize: String
				lateinit var companyLogo: String
				lateinit var financeStage: String
				lateinit var jobNature: String
				var district: String? = null
				lateinit var longitude: String
				lateinit var latitude: String
				lateinit var imState: String
				lateinit var firstType: String
				lateinit var secondType: String
				var isSchoolJob: Int = 0
				lateinit var subwayline: String
				lateinit var stationname: String
				lateinit var linestaion: String
				lateinit var thirdType: String
				lateinit var formatCreateTime: String
				lateinit var companyFullName: String
				lateinit var companyLabelList: List<String>
				lateinit var positionLables: List<String>
				lateinit var industryLables: List<String>
				lateinit var skillLables: List<String>
			}
		}
	}
}
