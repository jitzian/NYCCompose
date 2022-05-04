package com.example.nyccompose.rest

import com.example.nyccompose.rest.model.SchoolsResult
import com.example.nyccompose.rest.model.ScoresResult
import retrofit2.http.GET

interface RestApi {

    @GET("s3k6-pzi2.json")
    suspend fun fetchSchools(): SchoolsResult

    @GET("f9bf-2cp4.json")
    suspend fun fetchScores(): ScoresResult

}