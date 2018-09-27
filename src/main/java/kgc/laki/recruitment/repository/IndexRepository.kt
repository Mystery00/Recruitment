package kgc.laki.recruitment.repository

import kgc.laki.recruitment.constant.ExceptionCodeConstant
import kgc.laki.recruitment.model.HotSearch
import kgc.laki.recruitment.model.KeywordCategory
import kgc.laki.recruitment.repository.local.IndexLocalDataSource
import kgc.laki.recruitment.repository.remote.IndexRemoteDataSource
import kgc.laki.recruitment.utils.SessionUtil
import kgc.laki.recruitment.utils.exception.KGCException
import java.net.UnknownHostException
import javax.servlet.http.HttpServletRequest
import kotlin.Exception

object IndexRepository {
	fun getKeyWord(request: HttpServletRequest): List<KeywordCategory> {
		return try {
			IndexRemoteDataSource.getKeyWord()
		} catch (e: Exception) {
			when (e) {
				is KGCException -> SessionUtil.setException(request, e)
				is UnknownHostException -> SessionUtil.setException(request, KGCException(ExceptionCodeConstant.J_ERROR_INTERNET))
				else -> throw e
			}
			IndexLocalDataSource.getKeyWord()
		}
	}

	fun getHotSearch(request: HttpServletRequest): List<HotSearch> {
		return try {
			IndexRemoteDataSource.getHotSearch()
		} catch (e: Exception) {
			when (e) {
				is KGCException -> SessionUtil.setException(request, e)
				is UnknownHostException -> SessionUtil.setException(request, KGCException(ExceptionCodeConstant.J_ERROR_INTERNET))
				else -> throw e
			}
			IndexLocalDataSource.getHotSearch()
		}
	}
}