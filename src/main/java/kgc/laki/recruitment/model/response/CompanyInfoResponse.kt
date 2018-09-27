package kgc.laki.recruitment.model.response

class CompanyInfoResponse {
	lateinit var baseInfo: BaseInfoBean
	lateinit var coreInfo: CoreInfoBean
	lateinit var introduction: IntroductionBean
	lateinit var addressList: List<AddressListBean>
	lateinit var labels: List<String>
	lateinit var products: List<ProductsBean>

	class BaseInfoBean {
		/**
		 * companyId : 55446
		 * industryField : 移动互联网,O2O
		 * companySize : 2000人以上
		 * city : 北京
		 * financeStage : C轮
		 */

		lateinit var industryField: String
		lateinit var companySize: String
		lateinit var city: String
		lateinit var financeStage: String
	}

	class CoreInfoBean {
		/**
		 * companyId : 55446
		 * logo : i/image3/M00/4C/9E/CgpOIFrgFT-AB_lKAAAXCTsC5_U047.png
		 * companyName : 贝壳找房（北京）科技有限公司
		 * companyShortName : 贝壳
		 * approve : 2
		 * companyUrl : http://www.ke.com
		 * companyIntroduce : 以技术驱动的品质居住服务平台
		 * isFirst : false
		 */

		lateinit var logo: String
		lateinit var companyName: String
		lateinit var companyShortName: String
		lateinit var companyUrl: String
		lateinit var companyIntroduce: String
	}

	class IntroductionBean {
		/**
		 * companyId : 55446
		 * companyProfile :
		 *
		 *贝壳找房——以技术驱动的品质居住服务平台
		 *
		 * &nbsp;
		 *
		 * 从2011年开始做“真房源”，到2014年成立链家网，7年来，我们已经进入了超过30个城市，真房源率超过95%，月活用户数超千万。
		 *
		 * &nbsp;
		 *
		 * 我们的价值观是什么？
		 *
		 * ▶&nbsp;对用户好；
		 *
		 * ▶相信合作会共赢；
		 *
		 *
		 *
		 *
		 *
		 * 不远的将来，我们将覆盖全中国超过300个城市，服务超过2亿的社区家庭，连接100万职业经纪人和10万家门店，赋能超过100个品牌，作为基础设施推动行业正循环！
		 *
		 * &nbsp;
		 *
		 * ▶&nbsp;贝壳找房将开放资源和线上能力，聚合和赋能全行业的优质服务者，打造品质居住服务生态，提供包括二手房、新房、租赁和家装等全方位居住服务；
		 *
		 * &nbsp;
		 *
		 * ▶&nbsp;贝壳找房将继承和升级链家网的产品技术、品质控制和数据挖掘等优势能力，继承和持续迭代“楼盘字典”，研发VR看房等创新技术手段，为消费者提供更好的服务体验，通过线上交易流程的可视化、线下的闭环服务和平台承诺，为更多消费者提供安全保障；
		 *
		 * &nbsp;
		 *
		 * ▶&nbsp;贝壳找房还将搭建服务者的信用评价体系，为消费者甄选优质服务商，通过营销、经营、供应链、技术数据、金融、交易等各角度的赋能，让优质服务者和品牌在平台生态中得以更好地发展。
		 *
		 * &nbsp;
		 *
		 * 我们有一个愿望，希望我们的价值观是面向全行业而共享的，面向全社会而创造价值的。我们的价值实践，在于让行业变得更好，在于培养和服务大批优秀的从业者、服务者，在于让全行业的用户都获得更好的服务体验。让更多的伙伴成为价值观共同体，让产业链上、生态圈里所有认同我们价值观的伙伴都参与进来，聚沙成塔，聚水成涓！
		 *
		 * &nbsp;
		 *
		 * 这里拥有800+产品技术团队规模，绝大多数都来自百度、阿里、腾讯、新浪微博、网易、美团、京东、去哪儿网、滴滴、小米等一线互联网公司。
		 *
		 * 这里拥有第一届百度百万美金最高奖核心团队，还有”鸟哥“、”教主“、“囧哥”、“肖师傅”、“阿缪”等技术大牛带你玩转各个专业领域，更有一系列学习课程及技术站专业培养课，等你来挑战。
		 * pictures : [{"id":289195,"companyId":55446,"companyPicUrl":"i/image/M00/52/8E/CgpEMll10eqATCOlAAIcI3Balhc262.jpg","position":0,"createTime":"2017-07-24 18:55:35","updateTime":"2018-08-20 16:01:59","isdel":false},{"id":384317,"companyId":55446,"companyPicUrl":"i/image/M00/8D/64/CgpEMlrgGNuAK4_LABR1Lp9HI4w55.jpeg","position":0,"createTime":"2018-04-25 14:02:39","updateTime":"2018-08-20 16:01:59","isdel":false},{"id":384318,"companyId":55446,"companyPicUrl":"i/image/M00/8D/64/CgpEMlrgGOOAMZ4GAAGxA4EIFXQ22.jpeg","position":0,"createTime":"2018-04-25 14:02:39","updateTime":"2018-08-20 16:01:59","isdel":false},{"id":384319,"companyId":55446,"companyPicUrl":"i/image3/M00/4C/A0/Cgq2xlrgGPuAFv9AAAMhv_RhsJY84.jpeg","position":0,"createTime":"2018-04-25 14:02:39","updateTime":"2018-08-20 16:01:59","isdel":false},{"id":423989,"companyId":55446,"companyPicUrl":"i/image2/M01/7E/B8/CgotOVt6dRGATCVPABO7GeZ1AQI05.jpeg","position":0,"createTime":"2018-08-20 16:01:59","updateTime":"2018-08-20 16:01:59","isdel":false},{"id":423990,"companyId":55446,"companyPicUrl":"i/image2/M01/7E/B8/CgotOVt6dSqAG8sbAC3AAa34E8M228.jpg","position":0,"createTime":"2018-08-20 16:01:59","updateTime":"2018-08-20 16:01:59","isdel":false},{"id":423991,"companyId":55446,"companyPicUrl":"i/image2/M01/7E/BA/CgoB5lt6dTWASqgmAAzk2uF59rk97.jpeg","position":1,"createTime":"2018-08-20 16:01:59","updateTime":"2018-08-20 16:01:59","isdel":false},{"id":423992,"companyId":55446,"companyPicUrl":"i/image2/M01/7E/B8/CgotOVt6dUaAW9MLABTv2yj6iG418.jpeg","position":0,"createTime":"2018-08-20 16:01:59","updateTime":"2018-08-20 16:01:59","isdel":false},{"id":423993,"companyId":55446,"companyPicUrl":"i/image2/M01/7E/BA/CgoB5lt6dVyANBk2AAzmU5xtp0E82.jpeg","position":0,"createTime":"2018-08-20 16:01:59","updateTime":"2018-08-20 16:01:59","isdel":false},{"id":423994,"companyId":55446,"companyPicUrl":"i/image2/M01/7E/B9/CgotOVt6dWyAL7_gAAta09437yQ28.jpeg","position":0,"createTime":"2018-08-20 16:01:59","updateTime":"2018-08-20 16:01:59","isdel":false}]
		 */

		lateinit var companyProfile: String
		lateinit var pictures: List<PicturesBean>

		class PicturesBean {
			/**
			 * id : 289195
			 * companyId : 55446
			 * companyPicUrl : i/image/M00/52/8E/CgpEMll10eqATCOlAAIcI3Balhc262.jpg
			 * position : 0
			 * createTime : 2017-07-24 18:55:35
			 * updateTime : 2018-08-20 16:01:59
			 * isdel : false
			 */

			lateinit var companyPicUrl: String
			var position: Int = 0
			lateinit var createTime: String
			lateinit var updateTime: String
		}
	}

	class AddressListBean {
		/**
		 * id : 48491
		 * companyId : 55446
		 * userId : 1380273
		 * province : 北京
		 * city : 北京
		 * district : 海淀区
		 * detailAddress : 开拓路11号福道大厦
		 * lat : 40.04018018
		 * lng : 116.30561083
		 * businessArea : 上地,马连洼,西二旗
		 * createTime : 2015-03-07 16:00:10
		 * updateTime : 2016-06-07 13:24:24
		 * useTime : 2016-06-07 13:24:24
		 * provinceCode : 010000000
		 * cityCode : 010100000
		 * districtCode : 010114000
		 * businessAreaCode : 010114041,010114045,010114013
		 */

		lateinit var province: String
		lateinit var city: String
		lateinit var district: String
		lateinit var detailAddress: String
		lateinit var lat: String
		lateinit var lng: String
		lateinit var businessArea: String
		lateinit var createTime: String
		lateinit var updateTime: String
		lateinit var useTime: String
		lateinit var provinceCode: String
		lateinit var cityCode: String
		lateinit var districtCode: String
		lateinit var businessAreaCode: String
	}

	class ProductsBean {
		/**
		 * id : 29185
		 * companyid : 55446
		 * producturl : http://www.ke.com
		 * productprofile :
		 *
		 *以技术驱动的品质居住服务平台
		 * product : 贝壳找房
		 * productpicurl : i/image2/M00/48/A8/CgotOVrgFlOAJ1vgAAAiaJKBQEg399.png
		 * producttype : ["网站","移动app"]
		 */

		lateinit var producturl: String
		lateinit var productprofile: String
		lateinit var product: String
		lateinit var productpicurl: String
		lateinit var producttype: List<String>
	}
}
