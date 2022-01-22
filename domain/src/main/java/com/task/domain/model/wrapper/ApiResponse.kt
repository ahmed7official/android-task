package com.task.domain.model.wrapper

data class ApiResponse<ResponseType>(

    val success: Boolean,
    val statusCode: Int?,
    val statusMessage: String?,
    val data: ResponseType?
) {


    companion object {

        const val LOADING = "loading"

    }


    fun onLoading(action: () -> Unit) {

        if (statusMessage == LOADING) action()

    }

    /**
     * Performs the given [action] if success member variable is true && data member variable is
     * not null.
     * Returns the original `ApiResponse` unchanged.
     */
    fun onSuccess(action: (data: ResponseType) -> Unit): ApiResponse<ResponseType> {

        if (success && data != null) action(data)

        return this

    }


    /**
     * Performs the given [action] if success member variable is false or data member variable is
     *  null.
     * Returns the original `ApiResponse` unchanged.
     */
    fun onFailure(action: (message: String, code: Int) -> Unit): ApiResponse<ResponseType> {

        if (!success || data == null) action(statusMessage!!, statusCode!!)

        return this

    }

}