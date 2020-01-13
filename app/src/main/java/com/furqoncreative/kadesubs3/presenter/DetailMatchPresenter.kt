package com.furqoncreative.kadesubs3.presenter

import android.util.Log
import com.furqoncreative.kadesubs3.data.network.ApiRepository
import com.furqoncreative.kadesubs3.model.MatchResponse
import com.furqoncreative.kadesubs3.model.TeamResponse
import com.furqoncreative.kadesubs3.view.DetailMatchView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatchPresenter(
    private val apiRepository: ApiRepository,
    private val view: DetailMatchView
) {


    fun getDetailMatch(id: String) {
        view.showLoading()
        apiRepository.services.getDetailMatch(id).enqueue(object :
            Callback<MatchResponse> {
            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                if (response.code() == 200) {
                    view.showDetailMatch(response.body()!!.events)
                    view.hideLoading()
                }
            }

            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
            }
        })
    }

    fun getHomeBadge(id: String) {
        ApiRepository().services.getDetailTeam(id).enqueue(object :
            Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if (response.isSuccessful) {
                    Log.d("ID", "" + id)
                    view.showHomeBadge(response.body()!!.teams)
                }

            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Log.e("Detail Response", "" + t)

            }
        })
    }

    fun getAwayBadge(id: String) {
        ApiRepository().services.getDetailTeam(id).enqueue(object :
            Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if (response.isSuccessful) {
                    Log.d("ID", "" + id)
                    view.showAwayBadge(response.body()!!.teams)
                }

            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Log.e("Detail Response", "" + t)

            }
        })
    }
}