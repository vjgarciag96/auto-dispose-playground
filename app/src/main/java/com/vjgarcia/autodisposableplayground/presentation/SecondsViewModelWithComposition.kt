package com.vjgarcia.autodisposableplayground.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.AutoDispose.autoDisposable
import com.vjgarcia.autodisposableplayground.autodispose.ViewModelScope
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SecondsViewModelWithComposition(
    private val viewModelScope: ViewModelScope,
    application: Application
) : AndroidViewModel(application) {

    private val mutableSeconds = MutableLiveData<Long>()
    val seconds: LiveData<Long> = mutableSeconds
    private val mutableIsDisposed = MutableLiveData<Boolean>()
    val isDisposed = mutableIsDisposed

    fun start() {
        viewModelScope.start()
        Observable.interval(1, TimeUnit.SECONDS)
            .doOnSubscribe { mutableIsDisposed.value = false }
            .doOnDispose {
                Log.d(LOG_TAG, "Subscription disposed auto magically")
                mutableIsDisposed.value = true
            }
            .`as`(autoDisposable(viewModelScope))
            .subscribe(mutableSeconds::postValue)
    }

    fun stop() {
        viewModelScope.end()
    }

    private companion object {
        const val LOG_TAG = "SecondsVM - Composition"
    }
}