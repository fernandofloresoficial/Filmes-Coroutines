package com.example.filmescoroutines.service.repository

import com.example.filmescoroutines.service.model.FilmsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.security.auth.callback.Callback


class MainRepository {

    fun getFilms(callback: (List<FilmsModel>) -> Unit){
        Thread(Runnable {
            Thread.sleep(3000)
            callback.invoke(
                listOf(
                    FilmsModel(1, "Título 01"),
                    FilmsModel(2, "Título 02")
                )
            )
        }).start()
    }

    suspend fun getFilmsCourotine() : List<FilmsModel> {
        return withContext(Dispatchers.Default){
            delay(3000)
            listOf(
                FilmsModel(1, "Título 01"),
                FilmsModel(2, "Título 02")
            )

        }
    }
}