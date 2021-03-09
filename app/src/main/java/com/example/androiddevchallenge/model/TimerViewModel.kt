package com.example.androiddevchallenge.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
////

    var myTimer : MyTimer by mutableStateOf(_myTimer)

//    init {
//        this._myTimer = MutableLiveData(myTimer)
//    }
}
