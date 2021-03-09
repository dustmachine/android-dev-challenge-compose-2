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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TimerViewModel(_myTimer: MyTimer) : ViewModel() {

//    private var _myTimer = MutableLiveData(MyTimer(99, 59, 59))
//
//    private val _hours = MutableLiveData(_myTimer.value!!.hours)
//    val hours: LiveData<Int> = _myTimer.value.hours
//    private val _minutes = MutableLiveData(_myTimer.value!!.minutes)
//    val minutes: LiveData<Int> = _myTimer.value.minutes
//    private val _seconds = MutableLiveData(_myTimer.value!!.seconds)
//    val seconds: LiveData<Int> = _myTimer.value.seconds
// //

    var myTimer: MyTimer by mutableStateOf(_myTimer)

//    init {
//        this._myTimer = MutableLiveData(myTimer)
//    }
}
