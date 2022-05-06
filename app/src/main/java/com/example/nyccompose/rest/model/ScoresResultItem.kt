package com.example.nyccompose.rest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ScoresResultItem {

    @SerializedName("dbn")
    @Expose
    var dbn: String? = null

    @SerializedName("sat_critical_reading_avg_score")
    @Expose
    var readingScore: String? = null

    @SerializedName("sat_math_avg_score")
    @Expose
    var mathScore: String? = null

    @SerializedName("sat_writing_avg_score")
    @Expose
    var writingScore: String? = null

    @SerializedName("school_name")
    @Expose
    var schoolName: String? = null

    @SerializedName("num_of_sat_test_takers")
    @Expose
    var numOfSatTestTakers: String? = null
}