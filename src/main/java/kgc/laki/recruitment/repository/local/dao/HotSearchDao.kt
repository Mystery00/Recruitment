package kgc.laki.recruitment.repository.local.dao

import kgc.laki.recruitment.base.BaseDao
import kgc.laki.recruitment.model.HotSearch
import kgc.laki.recruitment.utils.JDBCUtil
import kotlin.collections.ArrayList

object HotSearchDao : BaseDao<HotSearch> {
	override fun insert(data: HotSearch) {
		val sql = "insert into db_bjyzpw.tb_hot_search(hs_href, hs_value, hs_date) values (?,?,?)"
		JDBCUtil.update(sql, arrayOf(data.href, data.value, data.date))
	}

	override fun delete(date: String, data: HotSearch?) {
		val sql = "delete from db_bjyzpw.tb_hot_search where hs_date = ?"
		JDBCUtil.update(sql, arrayOf(date))
	}

	override fun queryAll(date: String): List<HotSearch> = emptyList()

	fun queryList(date: String): List<HotSearch> {
		val sql = "select * from db_bjyzpw.tb_hot_search where hs_date=?"
		val mapList = JDBCUtil.getList(sql, arrayOf(date))
		val list = ArrayList<HotSearch>()
		mapList.forEach {
			val hotSearch = HotSearch()
			hotSearch.id = it["hs_id"]!!.toInt()
			hotSearch.href = it["hs_href"]!!
			hotSearch.value = it["hs_value"]!!
			hotSearch.date = it["hs_date"]!!
			list.add(hotSearch)
		}
		return list
	}

	override fun update(data: HotSearch) {}
}