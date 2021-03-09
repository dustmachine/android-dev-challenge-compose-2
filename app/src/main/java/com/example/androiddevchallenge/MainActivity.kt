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
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.MyTimer
import com.example.androiddevchallenge.model.TimerViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myTimer = MyTimer(2, 34, 7)
        myTimer.start()

        setContent {
            MyTheme {
                TimerScreen(TimerViewModel(myTimer))
            }
        }
    }
}

// Start building your app here!
@Composable
fun TimerScreen(timerViewModel: TimerViewModel) {
    val hours: String by timerViewModel.myTimer.hours.observeAsState("03")
    val minutes: String by timerViewModel.myTimer.minutes.observeAsState("04")
    val seconds: String by timerViewModel.myTimer.seconds.observeAsState("05")

    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.padding(30.dp)
        ) {
            TimerRow(hours, minutes, seconds)
        }
    }
}

@Composable
fun TimerRow(hours: String, minutes: String, seconds: String) {
    Row(
        modifier = Modifier.fillMaxWidth(80f)
    ) {
        Text(text = hours, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
        Text(text = minutes, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
        Text(text = seconds, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {

    val myTimer = MyTimer(1, 23, 45)
    myTimer.start()

    MyTheme {
        TimerScreen(TimerViewModel(myTimer))
    }
}

// @Preview("Dark Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MyApp()
//    }
// }
