package com.umutakpinar.cryptocrazydemo.repository

import com.umutakpinar.cryptocrazydemo.model.Crypto
import com.umutakpinar.cryptocrazydemo.model.CryptoList
import com.umutakpinar.cryptocrazydemo.service.CryptoAPI
import com.umutakpinar.cryptocrazydemo.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api : CryptoAPI
) {
    suspend fun getCryptoList(): Resource<CryptoList> {
        val response = try{
            api.getCryptoList()
        }catch (e : Exception){
            return Resource.Error("Something gone wrong...")
        }
        return Resource.Success(response)
    }

    suspend fun getCrypto(id : String) : Resource<Crypto>{
        val response = try {
            api.getCrypto(id)
        }catch (e : Exception){
            return Resource.Error("Something gone wrong...")
        }
        return Resource.Success(response)
    }
}





