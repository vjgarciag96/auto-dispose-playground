package com.vjgarcia.autodisposableplayground.autodispose

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

abstract class BaseViewModel(application: Application) :
    AndroidViewModel(application),
    LifecycleScopeProvider<ViewModelDisposableEvent> {

    private val eventsSubject = BehaviorSubject.create<ViewModelDisposableEvent>()

    override fun lifecycle(): Observable<ViewModelDisposableEvent> = eventsSubject.hide()

    override fun correspondingEvents(): CorrespondingEventsFunction<ViewModelDisposableEvent> =
        CorrespondingEventsFunction { event ->
            when (event) {
                ViewModelDisposableEvent.START -> ViewModelDisposableEvent.END
                ViewModelDisposableEvent.END -> throw LifecycleEndedException()
            }
        }


    override fun peekLifecycle(): ViewModelDisposableEvent? = eventsSubject.value

    open fun start() {
        eventsSubject.onNext(ViewModelDisposableEvent.START)
    }

    open fun stop() {
        eventsSubject.onNext(ViewModelDisposableEvent.END)
    }
}