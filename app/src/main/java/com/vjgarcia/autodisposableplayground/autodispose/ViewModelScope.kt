package com.vjgarcia.autodisposableplayground.autodispose

import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class ViewModelScope : LifecycleScopeProvider<ViewModelDisposableEvent> {

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

    fun start() {
        eventsSubject.onNext(ViewModelDisposableEvent.START)
    }

    fun end() {
        eventsSubject.onNext(ViewModelDisposableEvent.END)
    }
}