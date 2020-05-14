package com.example.tribecrypto

import com.example.tribecrypto.data.network_object.CryptoCurrencyCallObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {

    @GET("/v1/cryptocurrency/listings/latest")
    @Headers("X-CMC_PRO_API_KEY: ${BuildConfig.TOKEN}")
    fun getLatestList(): Single<CryptoCurrencyCallObject>
}