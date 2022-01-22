package com.task.data.remote


import android.util.Log
import com.task.domain.model.wrapper.ApiResponse
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException


const val ERROR_NO_INTERNET_CONNECTION = "No Internet Connection"
const val ERROR_TIMEOUT = "timeout"

suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): ApiResponse<T> {

    try {

        val response = call.invoke()

        if (response.isSuccessful) {


            return ApiResponse(
                response.isSuccessful, response.code(),
                response.message(),
                response.body()
            )

        } else {

            val responseBody = JSONObject(response.errorBody()?.string().toString())
            Log.e("api_error", responseBody.toString())

            val detail = responseBody.getString("error")
            Log.e("api_error", "detail: $detail")


            return ApiResponse(response.isSuccessful, response.code(), detail, null)
        }

    } catch (e: Exception) {
        e.printStackTrace()

        return when (e) {

            is UnknownHostException -> {
                ApiResponse(false, 0, ERROR_NO_INTERNET_CONNECTION, null)
            }

            is SocketTimeoutException -> {
                ApiResponse(false, 0, ERROR_TIMEOUT, null)
            }

            else -> {
                ApiResponse(false, 0, e.message.toString(), null)
            }

        }

    }

}