package com.easymanga.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.easymanga.data.network.NetworkDataManager
import com.easymanga.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(
    protected val networkDataManager: NetworkDataManager,
    protected val compositeDisposable: CompositeDisposable,
    protected val schedulerProvider: SchedulerProvider
) : ViewModel() {

    val empty = MutableLiveData<Boolean>().apply { value = false }

    val dataLoading = MutableLiveData<Boolean>().apply { value = false }

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