package com.example.filmescoroutines.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.filmescoroutines.R
import com.example.filmescoroutines.service.repository.MainRepository
import com.example.filmescoroutines.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this,
            MainViewModel.MainViewModelFactory(MainRepository())).get(MainViewModel::class.java)

        viewModel.filmsLiveData.observe(viewLifecycleOwner, Observer{ films ->
            textFilms.text = films[0].tittle
        })

        viewModel.getFilmsCoroutines()

    }

}