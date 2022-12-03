package com.umutakpinar.cryptocrazydemo.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutakpinar.cryptocrazydemo.model.CryptoListItem
import com.umutakpinar.cryptocrazydemo.repository.CryptoRepository
import com.umutakpinar.cryptocrazydemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {
    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoadig = mutableStateOf(false)

    private var initialCryptoList = listOf<CryptoListItem>()
    private var isSearhStarting = true

    init {
        loadCryptos()
    }

    fun searchCryptoList(query : String){

        val listToSearch = if(isSearhStarting){
            cryptoList.value
        }else{
            initialCryptoList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                cryptoList.value = initialCryptoList
                isSearhStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                it.currency.contains(query.trim(),true)
            }

            if(isSearhStarting){
                initialCryptoList = cryptoList.value
                isSearhStarting = false
            }

            cryptoList.value = results
        }
    }

    fun loadCryptos(){
        isLoadig.value = true
        viewModelScope.launch {
            val result = repository.getCryptoList()

            when(result){
                is Resource.Success -> {
                    val cryptoItems = result.data!!.mapIndexed { index, cryptoListItem ->
                        CryptoListItem(cryptoListItem.currency,cryptoListItem.price)
                    }
                    isLoadig.value = false
                    errorMessage.value = ""
                    cryptoList.value += cryptoItems
                }

                is Resource.Error -> {
                    isLoadig.value = false
                    errorMessage.value = result.message!!
                }
            }
        }

    }
}







