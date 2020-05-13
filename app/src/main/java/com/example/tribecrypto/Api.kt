package com.example.tribecrypto

import com.example.tribecrypto.data.networkObject.CryptoCurrencyCallObject
import io.reactivex.Single
import retrofit2.http.*

interface Api {

    @GET("/v1/cryptocurrency/listings/latest")
    @Headers("X-CMC_PRO_API_KEY: 9c461423-3d8e-4816-be9d-6d298a85347b")
    fun getLatestList() : Single<CryptoCurrencyCallObject>

}