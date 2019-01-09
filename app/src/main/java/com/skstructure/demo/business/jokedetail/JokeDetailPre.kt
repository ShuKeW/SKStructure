package com.skstructure.demo.business.jokedetail

import com.skstructure.SKHelper
import com.skstructure.core.Impl
import com.skstructure.core.SKIPre
import com.skstructure.core.SKPre
import com.skstructure.demo.ApiServices
import com.skstructure.demo.BaseBean
import com.skstructure.demo.JokeData
import com.skstructure.demo.display.IDialogDisplay
import com.skstructure.modules.methodproxy.Background
import com.skstructure.modules.methodproxy.BackgroundType

/**
 *  @date 2019/1/3   5:02 PM
 *  @author weishukai
 *  @describe
 */
@Impl(JokeDetailPre::class)
interface IJokeDetailPre : SKIPre {
    @Background(BackgroundType.HTTP)
    fun loadJokeComment(jokeBean: JokeData.JokeBean?)
}

class JokeDetailPre : SKPre<IJokeDetailActivity>(),
    IJokeDetailPre {

    override fun onCreate() {

    }

    override fun loadJokeComment(jokeBean: JokeData.JokeBean?) {
        if (jokeBean == null) {
            return
        }
        SKHelper.display(IDialogDisplay::class.java).dialogLoading()
        val jokeComment = http(ApiServices::class.java).loadJokeComment(jokeBean.soureid, 1)
        SKHelper.display(IDialogDisplay::class.java).dialogLoadingDismiss()
        var commentList: ArrayList<BaseBean>
        if (jokeComment != null && jokeComment.code == 200) {
            commentList = ArrayList()
            commentList.add(jokeBean)
            commentList.addAll(jokeComment.data?.hot?.list)
            commentList.addAll(jokeComment.data?.normal?.list)
            ui().showCommentList(commentList)
        }
    }

    override fun onDestory() {

    }

}
