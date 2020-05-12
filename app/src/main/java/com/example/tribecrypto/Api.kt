package com.example.tribecrypto

import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("/v1/cryptocurrency/listings/latest")
    fun getLatestList() /*: Single*/

}