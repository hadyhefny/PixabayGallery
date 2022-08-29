package com.hefny.hady.pixabaygallery.modules.images.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.rxjava3.cachedIn
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

    fun getImages(query: String) {
        getImagesUseCase(query)
            .cachedIn(viewModelScope)
            .doOnSubscribe {
                _imagesStateMutableLiveData.postValue(ImagesState(isLoading = true))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onBackpressureBuffer()
            .subscribe({
                _imagesStateMutableLiveData.value = ImagesState(data = it)
            }, {
                _imagesStateMutableLiveData.value = ImagesState(error = it)
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}