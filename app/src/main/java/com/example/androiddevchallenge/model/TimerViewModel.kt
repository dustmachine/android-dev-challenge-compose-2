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
package com.example.androiddevchallenge.model

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel() : ViewModel() {
    var isAlreadyRunning: Boolean = false
    val mHours = MutableLiveData(0)
    var hours: LiveData<Int> = mHours
    val mMinutes = MutableLiveData(0)
    var minutes: LiveData<Int> = mMinutes
    val mSeconds = MutableLiveData(0)
    var seconds: LiveData<Int> = mSeconds

    fun changeHour(diff: Int) {
        if (mHours.value == 0 && diff < 0) return; mHours.value = mHours.value?.plus(diff)
    }

    fun changeMinute(diff: Int) {
        if (mMinutes.value == 0 && diff < 0) return; mMinutes.value = mMinutes.value?.plus(diff)
    }

    fun changeSeconds(diff: Int) {
        if (mSeconds.value == 0 && diff < 0) return; mSeconds.value = mSeconds.value?.plus(diff)
    }

    fun start() {
        if (this.isAlreadyRunning) return
        val howManyMillis = ((mSeconds.value!! + (mMinutes.value!! * 60) + (mHours.value!! * 60 * 60)) * 1000).toLong()
        val timer = object : CountDownTimer(howManyMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                val h = (secondsLeft / 60 / 60)
                val m = (secondsLeft - (h * 60 * 60)) / 60
                val s = (secondsLeft - (h * 60 * 60) - (m * 60))
                mHours.value = h.toInt()
                mMinutes.value = m.toInt()
                mSeconds.value = s.toInt()
                Log.i(
                    "myTimer",
                    String.format("seconds remaining: %s...   H:%s M:%s S:%s", secondsLeft, h, m, s)
                )
            }

            override fun onFinish() {
                Log.i("myTimer", "Finished")
            }
        }
        timer.start()
        isAlreadyRunning = true
    }
}
