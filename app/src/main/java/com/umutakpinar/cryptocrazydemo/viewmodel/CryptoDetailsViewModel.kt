package com.umutakpinar.cryptocrazydemo.viewmodel

import androidx.lifecycle.ViewModel
import com.umutakpinar.cryptocrazydemo.model.Crypto
import com.umutakpinar.cryptocrazydemo.repository.CryptoRepository
import com.umutakpinar.cryptocrazydemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailsViewModel @Inject constructor( private val repository: CryptoRepository): ViewModel(){
    suspend fun getCrypto(id : String) : Resource<Crypto> {
        return repository.getCrypto(id)
    }
}

