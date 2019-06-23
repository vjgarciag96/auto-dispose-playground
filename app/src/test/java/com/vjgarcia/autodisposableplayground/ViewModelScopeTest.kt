package com.vjgarcia.autodisposableplayground

import com.uber.autodispose.AutoDispose.autoDisposable
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleNotStartedException
import com.vjgarcia.autodisposableplayground.autodispose.ViewModelScope
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.PublishSubject
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ViewModelScopeTest {

    private lateinit var sut: ViewModelScope

    @Before
    fun setUp() {
        sut = ViewModelScope()
    }

    @Test
    fun `When scope is started but not ended observable is not disposed`() {
        val testObserver = TestObserver<Int>()
        val autoDisposableObservable = ANY_OBSERVABLE.`as`(autoDisposable(sut))

        sut.start()
        val disposable = autoDisposableObservable.subscribeWith(testObserver)

        testObserver.assertSubscribed()
        testObserver.assertValues(1, 2, 3, 5)
        assertThat(disposable.isDisposed).isFalse()
    }

    @Test
    fun `When scope is started and ended observable is disposed`() {
        val testObserver = TestObserver<Int>()
        val subject = PublishSubject.create<Int>()
        val autoDisposableObservable = subject.`as`(autoDisposable(sut))

        sut.start()
        val disposable = autoDisposableObservable.subscribeWith(testObserver)
        testObserver.assertSubscribed()
        subject.onNext(1)
        testObserver.assertValue(1)
        sut.end()

        // https://github.com/ReactiveX/RxJava/pull/4873
        // assertThat(disposable.isDisposed).isTrue()
    }

    @Test
    fun `When scope is ended and a subscription is started an exception is thrown`() {
        val testObserver = TestObserver<Int>()
        val autoDisposableObservable = ANY_OBSERVABLE.`as`(autoDisposable(sut))

        sut.end()
        autoDisposableObservable.subscribeWith(testObserver)

        testObserver.assertError(LifecycleEndedException::class.java)
    }

    @Test
    fun `When a subscription is started without events emitted an exception is thrown`() {
        val testObserver = TestObserver<Int>()

        ANY_OBSERVABLE.`as`(autoDisposable(sut)).subscribeWith(testObserver)

        testObserver.assertError(LifecycleNotStartedException::class.java)
    }

    private companion object {
        val ANY_OBSERVABLE = Observable.just(1, 2, 3, 5)
    }
}