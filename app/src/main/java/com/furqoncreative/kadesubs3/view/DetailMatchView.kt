package com.furqoncreative.kadesubs3.view

import com.furqoncreative.kadesubs3.model.Match
import com.furqoncreative.kadesubs3.model.Team

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(data: List<Match>)
    fun showHomeBadge(data: List<Team>)
    fun showAwayBadge(data: List<Team>)
}