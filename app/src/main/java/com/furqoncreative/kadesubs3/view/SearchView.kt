package com.furqoncreative.kadesubs3.view

import com.furqoncreative.kadesubs3.model.Search

interface SearchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Search>)
    fun showEmptyData()
}