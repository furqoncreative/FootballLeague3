package com.furqoncreative.kadesubs3.model


import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("events")
    val events: List<Match>
)