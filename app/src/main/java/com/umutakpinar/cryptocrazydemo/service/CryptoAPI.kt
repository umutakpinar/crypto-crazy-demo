package com.umutakpinar.cryptocrazydemo.service

import com.umutakpinar.cryptocrazydemo.model.Crypto
import com.umutakpinar.cryptocrazydemo.model.CryptoList
import com.umutakpinar.cryptocrazydemo.util.Constants
import retrofit2.http.GET

interface CryptoAPI {

    @GET(Constants.EXTENSION_URL_CRYPTO_LIST)
    suspend fun getCryptoList() : CryptoList

    @GET(Constants.EXTENSION_URL_CRYPTO)
    suspend fun getCrypto(id : String) : Crypto
}





