package com.furqoncreative.kadesubs3.presenter

import com.furqoncreative.kadesubs3.data.network.ApiRepository
import com.furqoncreative.kadesubs3.model.MatchResponse
import com.furqoncreative.kadesubs3.view.MatchView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchPresenter(private val apiRepository: ApiRepository, private val view: MatchView) {


    fun getPrevMatch(id: String) {
        view.showLoading()
        apiRepository.services.getPrevMatch(id).enqueue(object :
            Callback<MatchResponse> {
            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                if (response.code() == 200) {
                    view.showMatchList(response.body()!!.events)
                    view.hideLoading()
                }
            }

            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
            }
        })
    }

    fun getNextMatch(id: String) {
        view.showLoading()
        apiRepository.services.getNextMatch(id).enqueue(object :
            Callback<MatchResponse> {
            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                if (response.code() == 200) {
                    view.showMatchList(response.body()!!.events)
                    view.hideLoading()
                }
            }

            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
            }
        })
    }


}