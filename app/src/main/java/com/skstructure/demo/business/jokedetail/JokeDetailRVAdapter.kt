package com.skstructure.demo.business.jokedetail

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.skstructure.SKHelper
import com.skstructure.demo.*
import com.skstructure.demo.business.jokelist.IJokeListPre
import com.skstructure.recyclerview.RVAdapter
import com.skstructure.recyclerview.RVHolder

/**
 *  @date 2019/1/3   5:19 PM
 *  @author weishukai
 *  @describe
 */

class JokeDetailRVAdapter(context: Context, dataList: List<BaseBean>?) :
    RVAdapter<Any, RVHolder<BaseBean>>(context, dataList) {

    override fun newViewHolder(parent: ViewGroup?, viewType: Int): RVHolder<BaseBean>? {
        return when (viewType) {
            0 -> {
                JokeHolder(
                    mInflater.inflate(
                        R.layout.item_joke,
                        parent,
                        false
                    )
                )
            }
            1 -> {
                JokeCommentHolder(
                    mInflater.inflate(
                        R.layout.item_joke_comment,
                        parent,
                        false
                    )
                )
            }
            else -> {
                null
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                0
            }
            else -> {
                1
            }
        }
    }

}

class JokeHolder(itemView: View) : RVHolder<BaseBean>(itemView) {

    private var userName: TextView = itemView.findViewById(R.id.userName)
    private var content: TextView = itemView.findViewById(R.id.content)
    private var forward: TextView = itemView.findViewById(R.id.forward)
    private var up: TextView = itemView.findViewById(R.id.up)
    private var down: TextView = itemView.findViewById(R.id.down)

    init {
        forward.setOnClickListener(this)
        up.setOnClickListener(this)
        down.setOnClickListener(this)
    }

    override fun onBindData(model: BaseBean?, position: Int) {
        (model as? JokeData.JokeBean)?.let {
            userName.text = "${it.username}:"
            content.text = "        ${it.text}"
            forward.text = "forward:${it.forward}"
            up.text = "up:${it.up}"
            down.text = "down:${it.down}"
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.forward -> {
                SKHelper.pre(IJokeListPre::class.java, 0).forward((dataBean as? JokeData.JokeBean)?.soureid ?: 0)
            }
            R.id.up -> {
                SKHelper.pre(IJokeListPre::class.java, 0).up((dataBean as? JokeData.JokeBean)?.soureid ?: 0)
            }
            R.id.down -> {
                SKHelper.pre(IJokeListPre::class.java, 0).down((dataBean as? JokeData.JokeBean)?.soureid ?: 0)
            }
        }
    }

    override fun onDestory() {
    }

}

class JokeCommentHolder(itemView: View) : RVHolder<BaseBean>(itemView) {
    private var jokeComment: TextView = itemView.findViewById(R.id.joke_comment)

    override fun onBindData(model: BaseBean?, position: Int) {
        val comment = model as JokeCommentBean
        jokeComment.text = "${comment.user.username}:\n        ${comment.content}"
    }

    override fun onDestory() {
    }

}