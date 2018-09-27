package kgc.laki.recruitment.base

interface BaseDao<T> {
	fun insert(data: T)
	fun delete(date: String, data: T?)
	fun queryAll(date: String): List<T>
	fun update(data: T)
}