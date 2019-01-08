package com.skstructure.demo

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import com.skstructure.recyclerview.DividerLinearItemDecoration
import com.skstructure.recyclerview.SKRecyclerView
import com.skstructure.view.SKActivity

/**
 *  @date 2019/1/3   5:00 PM
 *  @author weishukai
 *  @describe
 */

interface IJokeDetailActivity {
    fun showCommentList(commentList: List<BaseBean>)

}

class JokeDetailActivity : SKActivity<IJokeDetailPre>(), IJokeDetailActivity {

    private lateinit var skRecyclerView: SKRecyclerView
    private var adapter: JokeDetailRVAdapter? = null

    override fun initView() {
        setContentView(R.layout.activity_joke_detail)
        initRecyclerView()
        pre().loadJokeComment(intent.getSerializableExtra("jokeBean") as? JokeData.JokeBean)
    }

    private fun initRecyclerView() {
        skRecyclerView = findViewById(R.id.skrv_joke_detail)
        skRecyclerView.setHasFixedSize(true)
        skRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        skRecyclerView.addItemDecoration(DividerLinearItemDecoration(Color.parseColor("#CCCCCC"), 1))
        adapter = JokeDetailRVAdapter(applicationContext, null)
        skRecyclerView.adapter = adapter
    }

    override fun showCommentList(commentList: List<BaseBean>) {
        adapter?.setDataList(commentList)
    }

}