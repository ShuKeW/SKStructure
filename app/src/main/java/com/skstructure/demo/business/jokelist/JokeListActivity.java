package com.skstructure.demo.business.jokelist;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;

import com.skstructure.demo.JokeData;
import com.skstructure.demo.R;
import com.skstructure.recyclerview.DividerLinearItemDecoration;
import com.skstructure.recyclerview.SKRecyclerView;
import com.skstructure.view.SKActivity;

import java.util.List;

interface IJokeListActivity {

    void showJokeList(List<JokeData.JokeBean> jokeList);
}

public class JokeListActivity extends SKActivity<IJokeListPre> implements IJokeListActivity, SKRecyclerView.OnLoadMoreListener {
    private SKRecyclerView skRecyclerView;
    private JokeListRVAdapter adapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_joke_list);
        initRecyclerView();
        pre().loadJokeList();
    }

    private void initRecyclerView() {
        skRecyclerView = findViewById(R.id.skrv_joke_list);
        skRecyclerView.setHasFixedSize(true);
        skRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        skRecyclerView.addItemDecoration(new DividerLinearItemDecoration(Color.parseColor("#CCCCCC"), 2));
        adapter = new JokeListRVAdapter(getApplicationContext(), null);
        skRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        skRecyclerView.setOnLoadMoreListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        skRecyclerView.setOnLoadMoreListener(null);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void showJokeList(List<JokeData.JokeBean> jokeList) {
        adapter.setDataList(jokeList);
    }
}
