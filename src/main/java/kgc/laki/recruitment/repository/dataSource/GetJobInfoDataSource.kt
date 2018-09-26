package kgc.laki.recruitment.repository.dataSource

import kgc.laki.recruitment.model.JobInfo

interface GetJobInfoDataSource {
	fun get(positionID: String, html: String): JobInfo
}