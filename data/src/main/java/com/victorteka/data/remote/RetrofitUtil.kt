package com.victorteka.data.remote

import com.victorteka.domain.Result
import com.google.gson.Gson
import com.victorteka.domain.models.StatsErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

object RetrofitUtil {
    suspend inline fun <T> safeApiCall(
        crossinline responseFunction: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val response = withContext(Dispatchers.IO) { responseFunction.invoke() }
            when {
                response.isSuccessful -> Result.Success(response.body()!!)
                else -> {
                    val error: String = response.errorBody()!!.string()
                    Result.error(Gson().fromJson(error, StatsErrorHandler::class.java))
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
            Result.error(e)
        }
    }
}