package kgc.laki.recruitment.model

import java.io.Serializable

class KeywordCategory : Serializable {
	lateinit var category: String
	lateinit var keywordGroupList: List<KeywordGroup>
	lateinit var date: String
}