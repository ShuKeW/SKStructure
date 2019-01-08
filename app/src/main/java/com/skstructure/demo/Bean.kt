package com.skstructure.demo

import java.io.Serializable

class JokeData {
    var code: Int = 0
    var msg: String = ""
    var data: List<JokeBean>? = null

    class JokeBean : BaseBean(),Serializable {
        var type: String = ""
        var text: String = ""
        var username: String = ""
        var uid: String = ""
        var comment: Long = 0
        var passtime: String = ""
        var soureid: Long = 0
        var up: Long = 0
        var down: Long = 0
        var forward: Long = 0
    }
}

abstract class BaseBean {}

data class JokeComment(
    val code: Int,
    val data: Data,
    val msg: String
)

data class Data(
    val author_uid: Int,
    val hot: Hot,
    val normal: Normal
)

data class Hot(
    val list: List<JokeCommentBean>
)

data class Normal(
    val list: List<JokeCommentBean>
)

class JokeCommentBean(
    val content: String,
    val ctime: String,
    val data_id: Int,
    val hate_count: Int,
    val id: Int,
    val like_count: Int,
    val more: String,
    val status: Int,
    val type: String,
    val user: User
) : BaseBean()

data class User(
    val id: Int,
    val is_vip: Boolean,
    val personal_page: String,
    val profile_image: String,
    val qq_uid: String,
    val qzone_uid: String,
    val room_icon: String,
    val room_name: String,
    val room_role: String,
    val room_url: String,
    val sex: String,
    val total_cmt_like_count: String,
    val username: String,
    val weibo_uid: String
)

