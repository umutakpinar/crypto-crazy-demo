package com.umutakpinar.cryptocrazydemo.dependencyinjection

import com.umutakpinar.cryptocrazydemo.repository.CryptoRepository
import com.umutakpinar.cryptocrazydemo.service.CryptoAPI
import com.umutakpinar.cryptocrazydemo.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCryptoRepository(api : CryptoAPI) = CryptoRepository(api)

    @Singleton
    @Provides
    fun provideCryptoAPI() : CryptoAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(CryptoAPI::class.java)
    }

}




