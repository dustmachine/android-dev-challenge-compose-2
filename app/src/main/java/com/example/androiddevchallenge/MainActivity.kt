/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.TimerViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.grayish
import com.example.androiddevchallenge.ui.theme.pinkish

class MainActivity : AppCompatActivity() {

    val timerViewModel by viewModels<TimerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                TimerScreen(timerViewModel)
            }
        }
    }
}

// Start building your app here!
@Composable
fun TimerScreen(timerViewModel: TimerViewModel) {
    var isClickable by remember { mutableStateOf(true) }

    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.padding(30.dp)
        ) {
            TimerRow(timerViewModel)
//            ButtonRow()
            TextButton(
                onClick = {
                    timerViewModel.start()
                    isClickable = false
                },
                enabled = isClickable,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(20.dp)
                    .background(
                        color = (if (isClickable) pinkish else grayish),
                        shape = RoundedCornerShape(5.dp)
                    )
            ) {
                Text(
                    text = "start",
                    style = TextStyle(fontSize = 30.sp, color = Color.White),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun TimerRow(timerViewModel: TimerViewModel) {
    //  do we need to remember?  var hours: Int by rememberSaveable { mutableStateOf(7) }
    val hours: Int by timerViewModel.hours.observeAsState(9)
    val minutes: Int by timerViewModel.minutes.observeAsState(9)
    val seconds: Int by timerViewModel.seconds.observeAsState(9)

    Row(
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        NumberColumn(
            digit = hours,
            digitChanger = { timerViewModel.changeHour(it) },
            Modifier.weight(1f)
        )
        NumberColumn(
            digit = minutes,
            digitChanger = { timerViewModel.changeMinute(it) },
            Modifier.weight(1f)
        )
        NumberColumn(
            digit = seconds,
            digitChanger = { timerViewModel.changeSeconds(it) },
            Modifier.weight(1f)
        )
    }
}

@Composable
fun NumberColumn(digit: Int, digitChanger: (Int) -> Unit, modifier: Modifier) {

    Column(modifier.fillMaxHeight(.5f)) {
        Text(
            text = String.format("%02d", digit),
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth(1f)
                .padding(top = 5.dp)
        )
        TextButton(
            onClick = { digitChanger(1) },
            modifier = modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
                .background(color = pinkish, shape = CircleShape)
        ) {
            Text(
                text = "+",
                style = TextStyle(fontSize = 30.sp, color = Color.White),
                modifier = modifier,
                textAlign = TextAlign.Center
            )
        }
        TextButton(
            enabled = digit > 0,
            onClick = { digitChanger(-1) },
            modifier = modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
                .background(color = pinkish, shape = CircleShape)
        ) {
            Text(
                text = "-",
                style = TextStyle(fontSize = 30.sp, color = Color.White),
                modifier = modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        TimerScreen(TimerViewModel())
    }
}

// @Preview("Dark Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MyApp()
//    }
// }
