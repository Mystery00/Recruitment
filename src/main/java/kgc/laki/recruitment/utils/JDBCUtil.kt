package kgc.laki.recruitment.utils

import kgc.laki.recruitment.constant.PropertiesConstant
import kgc.laki.recruitment.model.PageBean
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.util.ArrayList
import java.util.HashMap

object JDBCUtil {
	private val USERNAME = PropertiesUtil.getMysqlInfo(PropertiesConstant.MYSQL_USER)
	private val PASSWORD = PropertiesUtil.getMysqlInfo(PropertiesConstant.MYSQL_PASSWD)
	private val PORT = PropertiesUtil.getMysqlInfo(PropertiesConstant.MYSQL_PORT)
	private const val DRIVER = "com.mysql.jdbc.Driver"
	private const val DB_NAME = "db_bjyzpw"

	private val url = "jdbc:mysql://localhost:$PORT/$DB_NAME?useUnicode=true&characterEncoding=utf-8"
	private lateinit var connection: Connection
	private lateinit var preparedStatement: PreparedStatement

	init {
		Class.forName(DRIVER)
	}

	private fun getConnection(): Connection? {
		if (!::connection.isInitialized)
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD)
		return connection
	}

	private fun getPreparedStatement(sql: String): PreparedStatement {
		this.preparedStatement = getConnection()!!.prepareStatement(sql)
		return preparedStatement
	}

	private fun setParams(sql: String, params: Array<String>) {
		preparedStatement = getPreparedStatement(sql)
		for (i in params.indices)
			preparedStatement.setObject(i + 1, params[i])
	}

	private fun setParams(sql: String, params: List<String>) {
		preparedStatement = getPreparedStatement(sql)
		for (i in params.indices)
			preparedStatement.setObject(i + 1, params[i])
	}

	fun getList(sql: String, params: Array<String>): List<Map<String, String>> {
		val list = ArrayList<Map<String, String>>()
		setParams(sql, params)
		val resultSet = preparedStatement.executeQuery()
		val resultSetMetaData = resultSet.metaData
		while (resultSet.next()) {
			val map = HashMap<String, String>()
			for (i in 1..resultSetMetaData.columnCount) {
				val colName = resultSetMetaData.getColumnName(i)
				map[colName] = resultSet.getString(colName)
			}
			list.add(map)
		}
		resultSet.close()
		return list
	}

	fun getMap(sql: String, params: Array<String>): Map<String, String> {
		val list = getList(sql, params)
		return if (list.isEmpty()) HashMap() else list[0]
	}

	fun update(sql: String, params: Array<String>): Int {
		setParams(sql, params)
		return preparedStatement.executeUpdate()
	}

	fun update(sql: String, params: List<String>): Int {
		setParams(sql, params)
		return preparedStatement.executeUpdate()
	}

	private fun getTotalRows(sqlString: String, params: Array<String>): Int {
		val sql = sqlString.toLowerCase()
		val countSql = "select count(*) as tempNum ${sql.substring(sql.indexOf("from"))}"
		return getMap(countSql, params)["tempNum"]?.toInt() ?: 0
	}

	fun getPageBean(sql: String, params: Array<String>, curPage: Int): PageBean {
		val pageSize = 10
		val data = getList("$sql limit ${(curPage - 1) * pageSize},$pageSize", params)
		val pageBean = PageBean()
		pageBean.curPage = curPage
		pageBean.pageSize = data.size
		pageBean.totalRows = getTotalRows(sql, params)
		pageBean.totalPages = pageBean.totalRows / pageSize + 1
		pageBean.data = data
		return pageBean
	}
}