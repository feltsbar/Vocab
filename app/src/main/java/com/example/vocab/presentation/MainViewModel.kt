package com.example.vocab.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vocab.data.DictionaryRepositoryImpl

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DictionaryRepositoryImpl(application)

}