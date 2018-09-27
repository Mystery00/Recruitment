package kgc.laki.recruitment.model

class PageBean {
	var curPage: Int = 0
	var totalPages: Int = 0
	var totalRows: Int = 0
	var pageSize: Int = 0
	lateinit var data: List<Map<String, String>>//具体数据
}