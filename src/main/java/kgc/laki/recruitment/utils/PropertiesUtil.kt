package kgc.laki.recruitment.utils

import java.util.*

object PropertiesUtil {
	private val map = HashMap<String, Properties>()

	fun get(fileName: String, keyName: String): String {
		var properties = map[fileName]
		if (properties == null) {
			val inputStream = javaClass.getResourceAsStream(fileName)
			val newProperties = Properties()
			newProperties.load(inputStream)
			map[fileName] = newProperties
			properties = newProperties
		}
		return properties.getProperty(keyName)
	}

	fun getUrl(keyName: String): String = get("/url4request.properties", keyName)
}