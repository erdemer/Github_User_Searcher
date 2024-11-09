package com.example.githubusersearcher.util.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Observes the given [Flow] and triggers the [onEvent] action when a new item is emitted.
 *
 * This function uses the [Lifecycle.repeatOnLifecycle] function to launch a new coroutine
 * when the [Lifecycle] is at least [Lifecycle.State.STARTED], and the coroutine is cancelled
 * when the [Lifecycle] is [Lifecycle.State.DESTROYED].
 *
 * @param flow The [Flow] to observe.
 * @param onEvent The action to trigger when a new item is emitted from the [Flow].
 *
 * @see [Lifecycle.repeatOnLifecycle]
 * @see [Flow.collect]
 */

fun <T> LifecycleOwner.observeAsEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onEvent)
            }
        }
    }
}