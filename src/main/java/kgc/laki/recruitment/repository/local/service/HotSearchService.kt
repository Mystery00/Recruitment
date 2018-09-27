package kgc.laki.recruitment.repository.local.service

import kgc.laki.recruitment.model.HotSearch
import kgc.laki.recruitment.repository.local.dao.HotSearchDao
import kgc.laki.recruitment.utils.StringUtil

object HotSearchService {
	fun set(list: List<HotSearch>) {
		if (list.isNotEmpty()) {
			HotSearchDao.delete(list[0].date, null)
			list.forEach { HotSearchDao.insert(it) }
		}
	}

	fun get(date: String?): List<HotSearch> = HotSearchDao.queryList(date ?: StringUtil.getTodayInfo())
}