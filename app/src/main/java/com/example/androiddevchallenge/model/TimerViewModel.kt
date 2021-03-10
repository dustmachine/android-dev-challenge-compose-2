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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel() : ViewModel() {
    fun changeHour(diff: Int) { mHours.value = mHours.value?.plus(diff) }
    fun changeMinute(diff: Int) { mMinutes.value = mMinutes.value?.plus(diff) }
    fun changeSeconds(diff: Int) { mSeconds.value = mSeconds.value?.plus(diff) }

    val mHours = MutableLiveData(5)
    var hours: LiveData<Int> = mHours
    val mMinutes = MutableLiveData(4)
    var minutes: LiveData<Int> = mMinutes
    val mSeconds = MutableLiveData(3)
    var seconds: LiveData<Int> = mSeconds
}
