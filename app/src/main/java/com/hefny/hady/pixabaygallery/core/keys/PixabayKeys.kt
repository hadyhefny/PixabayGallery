package com.hefny.hady.pixabaygallery.core.keys

object PixabayKeys {
    external fun getBaseUrl(): String
    external fun getApiKey(): String
    init {
        System.loadLibrary("native-lib")
    }
}