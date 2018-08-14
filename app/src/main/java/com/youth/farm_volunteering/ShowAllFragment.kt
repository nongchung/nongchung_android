package com.youth.farm_volunteering

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import kotlinx.android.synthetic.main.fragment_showall.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.youth.farm_volunteering.home.WeekFarmAllAdapter
import com.youth.farm_volunteering.data.AllNewData
import com.youth.farm_volunteering.data.AllNewResponseData
import com.youth.farm_volunteering.data.PopularResponseData
import com.youth.farm_volunteering.data.PopularSubData
import kotlinx.android.synthetic.main.fragment_showall.view.*


class ShowAllFragment : Fragment() {

    var title : String? = null
    var isScrolling = false
    var visibleItemCount : Int? = null
    var totalItemCount : Int? = null
    var firstVisible : Int? = null
    var idx : Int = 6;
    var isEndList : Boolean = false
    var isPopulList : Boolean = false
    var readFragmentHandler : Handler? = null
    var popularWeekNonghwalList: ArrayList<PopularSubData>? = null
    var allNewNonghwalList: ArrayList<PopularSubData>? = null
    var showAllRecyclerView : RecyclerView? = null

    lateinit var weekFarmAllAdapter: WeekFarmAllAdapter
    lateinit var newFarmAdapter: NewFarmAllAdapter
    lateinit var NonghwalListToBeAdded : ArrayList<PopularSubData>      // 리팩토링 해야될 부분!!
    lateinit var NewNonghwalListToBeAdded : ArrayList<PopularSubData>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_showall, container, false)

        title = this.arguments.getString("title")
        showAllRecyclerView = v.findViewById(R.id.fragment_showall_rv)
        popularWeekNonghwalList = ArrayList()
        var layoutManager : LinearLayoutManager

        if(title == "이번 주 인기농활")
        {
            v.progressbar_read_fragment.visibility = View.VISIBLE

            isPopulList = true
            getShowAllList(isPopulList, idx, true)

            showAllRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                        isScrolling = true;
                    }
                }
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    layoutManager= showAllRecyclerView!!.layoutManager as LinearLayoutManager

                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    firstVisible = layoutManager.findFirstVisibleItemPosition()
                    if(isScrolling && (visibleItemCount!! + firstVisible!! == totalItemCount)){
                        isScrolling = false
                        if(isEndList == false) {
                            fetchData()
                        } else{
                            Toast.makeText(activity.applicationContext, "더 이상 불러올 농활 없음!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
        else if(title == "새로운 농활")
        {
            v.progressbar_read_fragment.visibility = View.VISIBLE

            isPopulList = false
            getShowAllList(isPopulList, idx, true)

            showAllRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                        isScrolling = true;
                    }
                }
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    layoutManager= showAllRecyclerView!!.layoutManager as LinearLayoutManager

                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    firstVisible = layoutManager.findFirstVisibleItemPosition()
                    if(isScrolling && (visibleItemCount!! + firstVisible!! == totalItemCount)){
                        isScrolling = false
                        if(isEndList == false) {
                            fetchData()
                        } else{
                            Toast.makeText(activity.applicationContext, "더 이상 불러올 농활 없음!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        show_all_title.setText(title)
    }

    fun fetchData(){
        progressbar_pagination.visibility = View.VISIBLE

        idx+=6
        getShowAllList(isPopulList, idx, false)
    }

    fun getShowAllList(isPopulList : Boolean, idx : Int, isInitial : Boolean){
        if(isPopulList) {
            var homeCall = ApplicationController.instance!!.networkService!!.popular(idx);
            homeCall.enqueue(object : Callback<PopularResponseData> {
                override fun onFailure(call: Call<PopularResponseData>, t: Throwable?) {
                    Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<PopularResponseData>, response: Response<PopularResponseData>) {
                    if (isInitial) {
                        progressbar_read_fragment.visibility = View.GONE

                        popularWeekNonghwalList = response.body().data

                        weekFarmAllAdapter = WeekFarmAllAdapter(popularWeekNonghwalList!!)

                        showAllRecyclerView!!.layoutManager = LinearLayoutManager(context)
                        showAllRecyclerView!!.adapter = weekFarmAllAdapter

                    } else {
                        progressbar_pagination.visibility = View.GONE

                        NonghwalListToBeAdded = response.body().data!!

                        var listSize = popularWeekNonghwalList!!.size

                        for (i in 0..NonghwalListToBeAdded.size - 1) {
                            popularWeekNonghwalList!!.add(listSize + i, NonghwalListToBeAdded[i])
                        }
                        weekFarmAllAdapter = WeekFarmAllAdapter(popularWeekNonghwalList!!)
                        showAllRecyclerView!!.adapter.notifyDataSetChanged()

                        if (response.body().isEnd == 1) {
                            isEndList = true
                        }
                    }
                }
            })
        } else{
            var homeCall = ApplicationController.instance!!.networkService!!.new(idx);
            homeCall.enqueue(object : Callback<AllNewResponseData> {
                override fun onFailure(call: Call<AllNewResponseData>, t: Throwable?) {
                    Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<AllNewResponseData>, response: Response<AllNewResponseData>) {
                    if (isInitial) {

                        progressbar_read_fragment.visibility = View.GONE

                        allNewNonghwalList = response.body().data

                        newFarmAdapter = NewFarmAllAdapter(allNewNonghwalList!!)

                        fragment_showall_rv.layoutManager = LinearLayoutManager(context)
                        fragment_showall_rv.adapter = newFarmAdapter

                    } else {
                        progressbar_pagination.visibility = View.GONE

                        NewNonghwalListToBeAdded = response.body().data!!

                        var listSize = allNewNonghwalList!!.size

                        for (i in 0..NewNonghwalListToBeAdded.size - 1) {
                            allNewNonghwalList!!.add(listSize + i, NewNonghwalListToBeAdded[i])
                        }
                        newFarmAdapter = NewFarmAllAdapter(allNewNonghwalList!!)
                        showAllRecyclerView!!.adapter.notifyDataSetChanged()

                        if (response.body().isEnd == 1) {
                            isEndList = true
                        }
                    }
                }
            })
        }
    }
}
