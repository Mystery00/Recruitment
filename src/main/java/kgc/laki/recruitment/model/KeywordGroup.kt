package kgc.laki.recruitment.model

import java.io.Serializable

class KeywordGroup : Serializable {
	lateinit var title: String
	lateinit var keywordList: List<String>
}