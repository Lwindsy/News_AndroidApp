package com.example.news.data

data class UserUiState(
    val userId: String = "",
    val userName: String = "",
    // list of following users' Id
    val followingList: List<String>? = null,
    // list of the user's comments' Id
    val commentList: List<String>? = null,
    // list of the user's liked articles' Id
    val likeList: List<String>? = null,
    // list of the user's bookmarked articles' Id
    val bookmarkList: List<String>? = null
)