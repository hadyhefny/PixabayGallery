package com.hefny.hady.pixabaygallery.core.extensions

import com.hefny.hady.pixabaygallery.core.constants.ErrorMessages
import com.hefny.hady.pixabaygallery.core.data.source.remote.model.ErrorResponse
import java.io.IOException

fun Throwable.parseError(): ErrorResponse {
    var errorResponse = ErrorResponse(ErrorMessages.SOMETHING_WENT_WRONG)
    when (this) {
        is IOException -> {
            errorResponse = ErrorResponse(ErrorMessages.INTERNET_CONNECTION_ERROR)
        }
    }
    return errorResponse
}
