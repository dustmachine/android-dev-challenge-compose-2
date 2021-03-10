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
    val _started = MutableLiveData(false)
    var isStarted: LiveData<Boolean> = _started

    val _hours = MutableLiveData(0)
    var hours: LiveData<Int> = _hours
    val _minutes = MutableLiveData(0)
    var minutes: LiveData<Int> = _minutes
    val _seconds = MutableLiveData(0)
    var seconds: LiveData<Int> = _seconds

    val _isNonZero = MutableLiveData(false)
    var isNonZero: LiveData<Boolean> = _isNonZero

    fun updateNonZero() { _isNonZero.value = ((_seconds.value!! + _minutes.value!! + _hours.value!!) > 0) }

    fun changeHour(diff: Int) {
        if (_hours.value == 0 && diff < 0) return; _hours.value = _hours.value?.plus(diff)
        updateNonZero()
    }

    fun changeMinute(diff: Int) {
        if (_minutes.value == 0 && diff < 0) return; _minutes.value = _minutes.value?.plus(diff)
        updateNonZero()
    }

    fun changeSeconds(diff: Int) {
        if (_seconds.value == 0 && diff < 0) return; _seconds.value = _seconds.value?.plus(diff)
        updateNonZero()
    }

    fun start() {
        if (_started.value!!) return
        _started.value = true
        val howManyMillis = ((_seconds.value!! + (_minutes.value!! * 60) + (_hours.value!! * 60 * 60)) * 1000).toLong()
        val timer = object : CountDownTimer(howManyMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                val h = (secondsLeft / 60 / 60)
                val m = (secondsLeft - (h * 60 * 60)) / 60
                val s = (secondsLeft - (h * 60 * 60) - (m * 60))
                _hours.value = h.toInt()
                _minutes.value = m.toInt()
                _seconds.value = s.toInt()
                Log.i(
                    "myTimer",
                    String.format("seconds remaining: %s...   H:%s M:%s S:%s", secondsLeft, h, m, s)
                )
            }

            override fun onFinish() {
                Log.i("myTimer", "Finished")
                _started.value = false
            }
        }
        timer.start()
    }
}
