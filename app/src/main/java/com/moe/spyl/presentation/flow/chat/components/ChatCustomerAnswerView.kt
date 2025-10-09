package com.moe.spyl.presentation.flow.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moe.spyl.R
import com.moe.spyl.presentation.flow.chat.models.ChatMessage
import com.moe.spyl.presentation.flow.login.models.LoginState
import com.moe.spyl.presentation.flow.login.preview.LoginStatePreviewParameterProvider
import com.moe.spyl.ui.theme.SpylTheme

@Composable
fun ChatCustomerAnswerView(
    modifier: Modifier = Modifier,
    message: ChatMessage.CustomerMessage
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 18.dp,
                            topEnd = 18.dp,
                            bottomEnd = 18.dp
                        )
                    )
                    .background(Color.LightGray)
                    .padding(vertical = 18.dp, horizontal = 12.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = message.text,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
            //TODO... if needed
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                modifier = Modifier,
//                text = "8:00 AM Kikr, Representative",
//                fontSize = 14.sp,
//                textAlign = TextAlign.End,
//                fontWeight = FontWeight.Normal,
//                color = Color.LightGray
//            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp)
                .background(Color.Cyan),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null

        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatCustomerAnswerViewPreview(
    @PreviewParameter(LoginStatePreviewParameterProvider::class) state: LoginState
) {
    SpylTheme {
        var code by remember { mutableStateOf(CharArray(4) { ' ' }) }
        val focusRequesters = remember { (1..4).map { FocusRequester() } }
        ChatCustomerAnswerView(
            modifier = Modifier.padding(8.dp),
            message = ChatMessage.CustomerMessage(text = "הי אני רוצה")
        )
    }
}