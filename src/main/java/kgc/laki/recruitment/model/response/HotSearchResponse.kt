package kgc.laki.recruitment.model.response

class HotSearchResponse {

	/**
	 * hotwords : [{"text":"铜板街","url":"https://www.lagou.com/gongsi/2540.html","isHighLight":true,"linkTarget":"_blank","weight":91},{"text":"曹操专车","url":"https://www.lagou.com/gongsi/135727.html","isHighLight":true,"linkTarget":"_blank","weight":82},{"text":"爱奇艺","url":"https://www.lagou.com/gongsi/1686.html","isHighLight":true,"linkTarget":"_blank","weight":78},{"text":"高德地图","url":"https://www.lagou.com/gongsi/91.html","isHighLight":true,"linkTarget":"_blank","weight":77},{"text":"作业帮","url":"https://www.lagou.com/gongsi/113856.html","isHighLight":true,"linkTarget":"_self","weight":59},{"text":"后端开发","url":"http://www.lagou.com/zhaopin/houduankaifa","isHighLight":true,"linkTarget":"_self","weight":53},{"text":"移动产品经理","url":"http://www.lagou.com/zhaopin/yidongchanpinjingli","isHighLight":true,"linkTarget":"_self","weight":52}]
	 * recommendSearchWord : {"text":"实力派招聘专场","url":"https://activity.lagou.com/topic/2018shilipai.html","isHighLight":false}
	 */

	lateinit var recommendSearchWord: RecommendSearchWordBean
	lateinit var hotwords: List<HotwordsBean>

	class RecommendSearchWordBean {
		/**
		 * text : 实力派招聘专场
		 * url : https://activity.lagou.com/topic/2018shilipai.html
		 * isHighLight : false
		 */

		lateinit var text: String
		lateinit var url: String
		var isIsHighLight: Boolean = false
	}

	class HotwordsBean {
		/**
		 * text : 铜板街
		 * url : https://www.lagou.com/gongsi/2540.html
		 * isHighLight : true
		 * linkTarget : _blank
		 * weight : 91
		 */

		lateinit var text: String
		lateinit var url: String
		var isIsHighLight: Boolean = false
		lateinit var linkTarget: String
		var weight: Int = 0
	}
}
