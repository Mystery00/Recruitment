package kgc.laki.recruitment.repository.local.dao

import kgc.laki.recruitment.base.BaseDao
import kgc.laki.recruitment.model.KeywordCategory
import kgc.laki.recruitment.model.KeywordGroup
import kgc.laki.recruitment.utils.JDBCUtil
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object KeywordDao : BaseDao<KeywordCategory> {
	override fun insert(data: KeywordCategory) {
		val sql = "insert into db_bjyzpw.tb_keyword (k_category, k_group, k_key_word_list, k_date) values (?, ?, ?, ?)"
		data.keywordGroupList.forEach { group ->
			val keywordList = Arrays.toString(group.keywordList.toTypedArray())
			JDBCUtil.update(sql, arrayOf(data.category, group.title, keywordList, data.date))
		}
	}

	override fun delete(date: String, data: KeywordCategory?) {
		val sql = "delete from db_bjyzpw.tb_keyword where k_date=?"
		JDBCUtil.update(sql, arrayOf(date))
	}

	override fun queryAll(date: String): List<KeywordCategory> {
		val sql = "select * from db_bjyzpw.tb_keyword"
		val mapList = JDBCUtil.getList(sql, emptyArray())
		val list = ArrayList<KeywordCategory>()
		val saveMap = HashMap<String, HashMap<String, List<String>>>()
		mapList.forEach {
			val category = it["k_category"]!!
			val group = it["k_group"]!!
			val keyword = it["k_key_word_list"]!!
			val keywordList = keyword.substring(1, keyword.length - 1).split(',')
			if (saveMap.containsKey(category)) {
				if (saveMap[category]!!.containsKey(group)) {
					saveMap[category]!![group]!!.toMutableList().addAll(keywordList)
				} else {
					saveMap[category]!![group] = keywordList
				}
			} else {
				val map = HashMap<String, List<String>>()
				map[group] = keywordList
				saveMap[category] = map
			}
		}
		saveMap.forEach { it ->
			val keywordCategory = KeywordCategory()
			keywordCategory.category = it.key
			keywordCategory.date = date
			val keywordGroupList = ArrayList<KeywordGroup>()
			it.value.forEach { entry ->
				val keywordGroup = KeywordGroup()
				keywordGroup.title = entry.key
				keywordGroup.keywordList = entry.value
				keywordGroupList.add(keywordGroup)
			}
			keywordCategory.keywordGroupList = keywordGroupList
			list.add(keywordCategory)
		}
		return list
	}

	override fun update(data: KeywordCategory) {
	}
}