package com.example.kisahnabi.data.network

import com.example.kisahnabi.data.KisahResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("kisahnabi")
    fun getKisahNabi(): Flowable<List<KisahResponse>>

}