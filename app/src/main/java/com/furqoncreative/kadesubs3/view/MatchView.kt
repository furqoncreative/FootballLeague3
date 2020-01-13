package com.furqoncreative.kadesubs3.view

import com.furqoncreative.kadesubs3.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}