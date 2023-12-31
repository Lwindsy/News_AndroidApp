package com.example.news.ui.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.news.R

@Composable
fun InputField(
    value: String,                  // 当前要显示的值
    onValueChange: (String) -> Unit,// 值更改时触发，以便可以在其他位置更新状态
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 50.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .clip(RoundedCornerShape(20)),
    )
    {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
                .clip(RoundedCornerShape(20)),
            singleLine = true,          // 单行
            maxLines = 1,               // 最多一行
            value = value,              // 框中的值
            onValueChange = onValueChange,  // 触发事件
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            )
        )
    }
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 50.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .clip(RoundedCornerShape(20)),
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
                .clip(RoundedCornerShape(20))
                ,
            singleLine = true,
            maxLines = 1,
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }

}

@Composable
fun SearchField(
    text: String = "",
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    hint: String = "请输入",
    @DrawableRes startIcon: Int = -1,
    iconSpacing: Dp = 6.dp,

    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    cursorBrush: Brush = SolidColor(MaterialTheme.colors.primary)
) {
    var hasFocus by remember { mutableStateOf(false) }

    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier.onFocusChanged { hasFocus = it.isFocused },
        singleLine = true,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        cursorBrush = cursorBrush,
        decorationBox = @Composable { innerTextField ->
            Row(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.white),
//                        shape = RoundedCornerShape(18.dp)
                    )
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // -1 不显示 左侧Icon
                if (startIcon != -1) {
                    Image(painter = painterResource(id = startIcon), contentDescription = null)
                    Spacer(modifier = Modifier.width(iconSpacing))
                }

                Box(modifier = Modifier.weight(1f)) {
                    // 当空字符时, 显示hint
                    if (text.isEmpty())
                        Text(text = hint, color = Color.Gray, style = textStyle)

                    // 原本输入框的内容
                    innerTextField()
                }

                // 存在焦点 且 有输入内容时. 显示叉号
                if (hasFocus && text.isNotEmpty()) {
                    Image(imageVector = Icons.Filled.Clear, // 清除图标
                        contentDescription = null,
                        // 点击就清空text
                        modifier = Modifier.clickable { onValueChange.invoke("") })
                }
            }
        }
    )
}

/**
 * 分割线
 */
@Composable
fun MyDivider() {
    Divider(
        thickness = 0.5.dp,     //设置分割线的高度
        color = Color.Gray,     //设置分割线的颜色
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    )
}