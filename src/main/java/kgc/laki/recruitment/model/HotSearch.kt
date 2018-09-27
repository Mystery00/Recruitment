package kgc.laki.recruitment.model

import java.io.Serializable

class HotSearch : Serializable {
	var id = 0
	lateinit var href: String
	lateinit var value: String
	lateinit var date: String

	override fun equals(other: Any?): Boolean = other is HotSearch && other.href == href && other.value == value && other.date == date

	override fun hashCode(): Int {
		var result = href.hashCode()
		result = 31 * result + value.hashCode()
		result = 31 * result + date.hashCode()
		return result
	}
}