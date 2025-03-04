package com.example.springtrainingproject2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DomainApiService {
    @GET("domains/search")
    fun searchDomains(
        @Query("domain") domain: String,
        @Query("zone") zone: String
    ): Call<DomainResponse>
}
