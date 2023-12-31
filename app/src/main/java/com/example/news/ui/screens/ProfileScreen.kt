package com.example.news.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.news.R
import com.example.news.data.BookMarkedTableUiState
import com.example.news.ui.theme.LongCard
import com.example.news.ui.utils.MyDivider
import com.example.news.ui.utils.ProfileCollectedLoadingCard
import com.example.news.ui.viewmodel.NewsAppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: NewsAppViewModel,
    modifier: Modifier = Modifier,
    onArticleCardClick: (String) -> Unit = {},
) {

    val bookMarkedTableState = viewModel.bookMarkedTableUiState
    val userInfo by viewModel.userUiState.collectAsState()

    // 获取 viewModel 来封装信息
    val followAmountPe: String = userInfo.followNum
    val likeAmount: String = userInfo.likeNum
    val reviewAmount: String = userInfo.commentNum
    val personalSignature: String = userInfo.userName
    Scaffold{
            LazyColumn(contentPadding = it) {
                item { Spacer(modifier = Modifier.height(30.dp)) }

                /* 人物头像 */
                item {
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp)
                    ) {
                        Image(
                            modifier = modifier
                                .size(dimensionResource(R.dimen.image_size))
                                .padding(dimensionResource(R.dimen.padding_small))
                                .clip(RoundedCornerShape(50)),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(R.drawable.head_icon),
                            contentDescription = null
                        )
                        androidx.compose.material3.Text(
                            modifier = Modifier
                                .padding(top = 14.dp),
//                            fontSize = 26.sp,
                            text = personalSignature,
                            color = Color.Black,
                            fontSize = 26.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }

                /* 关注等信息 */
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp),
                        horizontalArrangement = Arrangement.Center,//设置水平居中对齐
                        verticalAlignment = Alignment.CenterVertically//设置垂直居中对齐
                    ) {
                        androidx.compose.material3.Text(
                            text = stringResource(R.string.profile_follow),
                            fontSize = 18.sp,
                        )
                        Spacer(modifier = Modifier.width(60.dp))
                        androidx.compose.material3.Text(
                            text = stringResource(R.string.profile_like),
                            fontSize = 18.sp,
                        )

                        Spacer(modifier = Modifier.width(60.dp))
                        androidx.compose.material3.Text(
                            text = stringResource(R.string.profile_review),
                            fontSize = 18.sp,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp),
                        horizontalArrangement = Arrangement.Center,//设置水平居中对齐
                        verticalAlignment = Alignment.CenterVertically//设置垂直居中对齐
                    ) {
                        androidx.compose.material3.Text(
                            text = followAmountPe,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(80.dp))
                        androidx.compose.material3.Text(
                            text = likeAmount,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.width(80.dp))
                        androidx.compose.material3.Text(
                            text = reviewAmount,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    MyDivider()     // 分割线
                    Spacer(modifier = Modifier.height(30.dp))

                }
                /* 我的关注列表 */
                item {
                    androidx.compose.material3.Text(
                        text = stringResource(R.string.profile_following),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    LazyRow(
                        contentPadding = it,
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,//设置水平居中对齐
                        verticalAlignment = Alignment.CenterVertically//设置垂直居中对齐
                    ) {
                        items(10) {
                            Image(
                                modifier = modifier
                                    .size(dimensionResource(R.dimen.image_size))
                                    .padding(dimensionResource(R.dimen.padding_small))
                                    .clip(RoundedCornerShape(50)),
                                contentScale = ContentScale.Crop,
                                painter = painterResource(R.drawable.head_icon),
                                contentDescription = null
                            )
                        }
                    }

                    MyDivider() // 分割线
                    Spacer(modifier = Modifier.height(30.dp))
                }

                /* 收藏夹 */
                item {
                    androidx.compose.material3.Text(
                        text = stringResource(R.string.profile_favorites),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                when (bookMarkedTableState) {
                    is BookMarkedTableUiState.Loading -> {
                        /* 你写好的Loading组件，比如你写一个HeadArticleTable，里面是一个LazyRow展现上面的HeadArticleCard_Loading */
                        items(10) {
                            ProfileCollectedLoadingCard()
                        }
                    }

                    is BookMarkedTableUiState.Success -> {
                        /* 你写好的Success组件 */
                        items(bookMarkedTableState.bookMarkedTable) { article ->
                            LongCard(articleItem = article, onArticleCardClick = onArticleCardClick)
                        }
                    }

                    is BookMarkedTableUiState.Error -> {
                        /* 忽略 */
                    }
                }
            }
    }
}