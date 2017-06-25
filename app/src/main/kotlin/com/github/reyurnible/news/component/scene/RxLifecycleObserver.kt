package com.github.reyurnible.news.component.scene

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.OutsideLifecycleException
import com.trello.rxlifecycle2.RxLifecycle
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.subjects.BehaviorSubject

/**
 * LifecycleObserver convert to Rx
 */
abstract class RxLifecycleObserver : LifecycleProvider<Lifecycle.Event>, LifecycleObserver {
    companion object {
        // For Architecture LifeCycle Binder
        val ARCH_LIFECYCLE = Function<Lifecycle.Event, Lifecycle.Event> { lastEvent ->
            when (lastEvent) {
                Lifecycle.Event.ON_CREATE -> Lifecycle.Event.ON_DESTROY
                Lifecycle.Event.ON_START -> Lifecycle.Event.ON_STOP
                Lifecycle.Event.ON_RESUME -> Lifecycle.Event.ON_PAUSE
                Lifecycle.Event.ON_PAUSE -> Lifecycle.Event.ON_STOP
                Lifecycle.Event.ON_STOP -> Lifecycle.Event.ON_DESTROY
                Lifecycle.Event.ON_ANY -> Lifecycle.Event.ON_DESTROY
                Lifecycle.Event.ON_DESTROY -> throw OutsideLifecycleException("Cannot bind to Architecture lifecycle when outside of it.")
            }
        }
    }

    private val lifecycleSubject = BehaviorSubject.create<Lifecycle.Event>()

    override fun lifecycle(): Observable<Lifecycle.Event> = lifecycleSubject.hide()

    override fun <T : Any?> bindToLifecycle(): LifecycleTransformer<T> = RxLifecycle.bind(lifecycleSubject, ARCH_LIFECYCLE)

    override fun <T : Any?> bindUntilEvent(event: Lifecycle.Event): LifecycleTransformer<T> = RxLifecycle.bindUntilEvent(lifecycleSubject, event)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
        lifecycleSubject.onNext(Lifecycle.Event.ON_CREATE)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
        lifecycleSubject.onNext(Lifecycle.Event.ON_START)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        lifecycleSubject.onNext(Lifecycle.Event.ON_RESUME)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        lifecycleSubject.onNext(Lifecycle.Event.ON_PAUSE)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
        lifecycleSubject.onNext(Lifecycle.Event.ON_STOP)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        lifecycleSubject.onNext(Lifecycle.Event.ON_DESTROY)
    }
}
