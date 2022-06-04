package com.example.nyccompose.schools.requirements.priority.domain.model

data class RequirementPriority(
    var dbn: String? = "",
    var schoolName: String? = "",
    var overViewParagraph: String? = "",
    var numOfSatTestTakers: String? = "",
    var satCriticalReadingAvgScore: String? = "",
    var satMathAvgScore: String? = "",
    var satWritingAvgScore: String? = ""
)