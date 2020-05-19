package com.pce.weatherapp.internal

import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

fun <T> Task<T>.asDeferredAsync(): Deferred<T> {
    val deferred = CompletableDeferred<T>()
    this.addOnSuccessListener { result ->
        deferred.complete(result)
    }
    this.addOnFailureListener { exception ->
        deferred.completeExceptionally(exception)
    }
    return deferred
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}