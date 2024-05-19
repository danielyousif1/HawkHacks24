package com.nathanespejo.blockchaincharityapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nathanespejo.blockchaincharityapp.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private  val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Charity> = MutableLiveData()

    fun getCharity() {
        viewModelScope.launch {
            val response = repository.getCharity()
            myResponse.value = response
        }
    }
}