package kgc.laki.recruitment.repository.local.service

import kgc.laki.recruitment.model.JobInfo
import kgc.laki.recruitment.repository.local.dao.JobInfoDao

object JobInfoService {
	fun set(jobInfo: JobInfo) {
		JobInfoDao.delete(jobInfo.jobID.toString())
		JobInfoDao.insert(jobInfo)
	}

	fun get(id: String): JobInfo? = JobInfoDao.query(id)
}