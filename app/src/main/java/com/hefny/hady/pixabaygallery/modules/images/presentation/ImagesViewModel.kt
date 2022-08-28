package com.hefny.hady.pixabaygallery.modules.images.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.map
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.ImageEntity
import com.hefny.hady.pixabaygallery.modules.images.domain.interactor.GetImagesUseCase
import com.hefny.hady.pixabaygallery.modules.images.presentation.model.ImagesState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        getImages("fruits")
    }

    private val _imagesStateMutableLiveData = MutableLiveData<ImagesState>()
    val imagesStateLiveData: LiveData<ImagesState>
        get() = _imagesStateMutableLiveData

    private val TAG = "AppDebug"

    fun getImages(query: String) {
        getImagesUseCase(query)
            .doOnSubscribe {
                _imagesStateMutableLiveData.postValue(ImagesState(isLoading = true))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onBackpressureBuffer()
            .subscribe({
                _imagesStateMutableLiveData.postValue(ImagesState(data = it))
            }, {
                _imagesStateMutableLiveData.postValue(ImagesState(error = it))
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}