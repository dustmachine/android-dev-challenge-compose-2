package com.example.androiddevchallenge.model

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class MyTimer(var _hours: Int, var _minutes: Int, var _seconds: Int) {

    val mHours = MutableLiveData(_hours.toString())
    var hours: LiveData<String> = mHours
    val mMinutes = MutableLiveData(_minutes.toString())
    var minutes: LiveData<String> = mMinutes
    val mSeconds = MutableLiveData(_seconds.toString())
    var seconds: LiveData<String> = mSeconds

    private fun format(i: Int) = String.format("%02d", i)

    fun start() {
        val howManyMillis = ((_seconds + (_minutes * 60) + (_hours * 60 * 60)) * 1000).toLong()
        val timer = object : CountDownTimer(howManyMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                val h = (secondsLeft / 60 / 60)
                val m = ((secondsLeft - (h * 60 * 60)) / 60)
                val s = (secondsLeft - (h * 60 * 60) - (m * 60))
                mHours.value = format(h.toInt())
                mMinutes.value = format(m.toInt())
                mSeconds.value = format(s.toInt())
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
    }

}
