package com.easymanga.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.easymanga.data.network.DownloadManager
import com.easymanga.data.network.NetworkDataManager
import com.easymanga.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(
    application: Application,
    protected val networkDataManager: NetworkDataManager,
    protected val downloadManager: DownloadManager,
    protected val compositeDisposable: CompositeDisposable,
    protected val schedulerProvider: SchedulerProvider
) : AndroidViewModel(application) {

    val empty = MutableLiveData<Boolean>().apply { value = false }

    val loadingState = MutableLiveData<LoadingState>()
        .apply {
            value = LoadingState.IDLE
        }

    val toastMessage = MutableLiveData<String>()

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    enum class LoadingState {

        IDLE, LOADING, SUCCESS, FAILED
    }
}