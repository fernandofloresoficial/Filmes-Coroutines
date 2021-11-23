package com.example.filmescoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmescoroutines.service.model.FilmsModel
import com.example.filmescoroutines.service.repository.MainRepository
import kotlinx.coroutines.*

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val filmsLiveData = MutableLiveData<List<FilmsModel>>()

    fun getFilms() {
        mainRepository.getFilms { films ->
            filmsLiveData.postValue(films)
        }
    }

    fun getFilmsCoroutines() {
        CoroutineScope(Dispatchers.Main).launch {
            val films = withContext(Dispatchers.Default) {
                mainRepository.getFilmsCourotine()
            }

            filmsLiveData.value = films

        }
    }

    class MainViewModelFactory(
        private val repository: MainRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}