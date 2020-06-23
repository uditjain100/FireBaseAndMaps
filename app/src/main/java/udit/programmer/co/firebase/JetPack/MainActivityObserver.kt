package udit.programmer.co.firebase.JetPack

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MainActivityObserver : LifecycleObserver {

    fun init() {
        Log.i("Ceased Meteor", "onCreate() is called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() {
        Log.i("Ceased Meteor", "onCreate() is called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        Log.i("Ceased Meteor", "onPause() is called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        Log.i("Ceased Meteor", "onDestroy() is called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        Log.i("Ceased Meteor", "onResume() is called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        Log.i("Ceased Meteor", "onStart() is called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        Log.i("Ceased Meteor", "onStop() is called")
    }

}