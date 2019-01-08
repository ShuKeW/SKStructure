package com.skstructure.demo

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.skstructure.SKHelper
import com.skstructure.recyclerview.RVAdapter
import com.skstructure.recyclerview.RVHolder

/**
 *  @date 2019/1/2   10:43 AM
 *  @author weishukai
 *  @describe
 */
class JokeListRVAdapter(context: Context, dataList: List<JokeData.JokeBean>?) :
    RVAdapter<JokeData.JokeBean, JokeListHolder>(context, dataList) {
    override fun newViewHolder(parent: ViewGroup?, viewType: Int): JokeListHolder {
        return JokeListHolder(mInflater.inflate(R.layout.item_joke, parent, false))
    }
}

class JokeListHolder(itemView: View) : RVHolder<JokeData.JokeBean>(itemView) {

    private var userName: TextView = itemView.findViewById(R.id.userName)
    private var content: TextView = itemView.findViewById(R.id.content)
    private var forward: TextView = itemView.findViewById(R.id.forward)
    private var up: TextView = itemView.findViewById(R.id.up)
    private var down: TextView = itemView.findViewById(R.id.down)

    init {
        itemView.setOnClickListener(this)
        forward.setOnClickListener(this)
        up.setOnClickListener(this)
        down.setOnClickListener(this)
    }

    override fun onBindData(model: JokeData.JokeBean?, position: Int) {
        userName.text = "${model?.username}:"
        content.text = "        ${model?.text}"
        forward.text = "forward:${model?.forward}"
        up.text = "up:${model?.up}"
        down.text = "down:${model?.down}"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.item_joke -> {
                SKHelper.display(IDemoActivityDisplay::class.java).startJokeDetailActivity(dataBean)
            }
            R.id.forward -> {
                Log.i("tag", "click forward")
                SKHelper.pre(IJokeListPre::class.java, 0).forward(dataBean.soureid)
            }
            R.id.up -> {
                SKHelper.pre(IJokeListPre::class.java, 0).up(dataBean.soureid)
            }
            R.id.down -> {
                SKHelper.pre(IJokeListPre::class.java, 0).down(dataBean.soureid)
            }
        }
    }

    override fun onDestory() {
    }

}