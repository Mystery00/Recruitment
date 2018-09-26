package kgc.laki.recruitment.utils

object StringUtil {
	fun stringToList(string: String, split: String): List<String> = string.split(split).toMutableList()

	fun arrayToString(array: Array<String>, split: String): String {
		val stringBuilder = StringBuilder()
		array.filter {
			if (array.size == 1)
				it != ""
			else
				it != "" && it != "不限"
		}
				.forEachIndexed { index, s ->
					if (index != 0)
						stringBuilder.append(split)
					stringBuilder.append(s)
				}
		return stringBuilder.toString()
	}

	fun listToString(list: List<String>, split: String): String {
		val stringBuilder = StringBuilder()
		list.filter {
			if (list.size == 1)
				it != ""
			else
				it != "" && it != "不限"
		}
				.forEachIndexed { index, s ->
					if (index != 0)
						stringBuilder.append(split)
					stringBuilder.append(s)
				}
		return stringBuilder.toString()
	}
}