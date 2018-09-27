package kgc.laki.recruitment.repository.local.service

import kgc.laki.recruitment.model.KeywordCategory
import kgc.laki.recruitment.repository.local.dao.KeywordDao
import kgc.laki.recruitment.utils.StringUtil

object KeywordService{
	fun set(list: List<KeywordCategory>) {
		if (list.isNotEmpty()) {
			KeywordDao.delete(list[0].date, null)
			list.forEach { KeywordDao.insert(it) }
		}
	}

	fun get(date: String?): List<KeywordCategory> = KeywordDao.queryAll(date ?: StringUtil.getTodayInfo())
}