package com.furqoncreative.kadesubs3.ui.search

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.furqoncreative.kadesubs3.R
import com.furqoncreative.kadesubs3.data.network.ApiRepository
import com.furqoncreative.kadesubs3.model.Search
import com.furqoncreative.kadesubs3.presenter.SearchPresenter
import com.furqoncreative.kadesubs3.ui.match.DetailMatchActivity
import com.furqoncreative.kadesubs3.view.SearchView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class ResultMatchActivity : AppCompatActivity() {
    companion object {
        const val QUERY = "query"
    }

    private var query: String = ""

    private var items: MutableList<Search> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: SearchPresenter
    private lateinit var adapter: SearchAdapter
    private lateinit var listItem: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var emptyLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent != null) {
            query = intent.getStringExtra(QUERY)
        }
        val actionbar = supportActionBar
        actionbar!!.title = "Result for : $query"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(8)
            leftPadding = dip(8)
            rightPadding = dip(8)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listItem = recyclerView {
                        id = R.id.list_match
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(this@ResultMatchActivity)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }

                    emptyLayout = linearLayout {
                        lparams(width = matchParent, height = matchParent)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        visibility = View.GONE


                        imageView {
                            imageResource = (R.drawable.ic_inbox_black_24dp)
                        }.lparams {
                            height = dip(100)
                            width = dip(100)
                        }


                        textView {
                            textSize = 14f
                            gravity = Gravity.CENTER
                            text = "Result not found"

                        }.lparams {
                            topMargin = dip(16)
                        }
                    }


                }
            }
        }

        adapter = SearchAdapter(
            applicationContext,
            items
        ) {
            startActivity<DetailMatchActivity>(
                DetailMatchActivity.ID to it.idEvent
            )
        }

        listItem.adapter = adapter

        Log.d("QUERY", "" + query)

        val request = ApiRepository()

        presenter = SearchPresenter(request, (object : SearchView {
            override fun showLoading() {
                progressBar.visibility = View.VISIBLE
                listItem.visibility = View.INVISIBLE

            }

            override fun hideLoading() {
                progressBar.visibility = View.INVISIBLE
                listItem.visibility = View.VISIBLE

            }

            override fun showMatchList(data: List<Search>) {
                swipeRefresh.isRefreshing = false
                items.clear()
                items.addAll(data)
                adapter.notifyDataSetChanged()
            }

            override fun showEmptyData() {
                listItem.visibility = View.GONE
                emptyLayout.visibility = View.VISIBLE

            }

        }))

        presenter.getResultMatch(query)

        swipeRefresh.onRefresh {
            presenter.getResultMatch(query)

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
