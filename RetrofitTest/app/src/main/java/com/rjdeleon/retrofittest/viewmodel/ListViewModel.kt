package com.rjdeleon.retrofittest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rjdeleon.retrofittest.model.Country
import com.rjdeleon.retrofittest.network.CountriesService
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class ListViewModel: ViewModel() {

    val countriesService = CountriesService.getCountriesService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    val countries: LiveData<List<Country>> = liveData(Dispatchers.IO) {
        val response = countriesService.getCountries()
        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                countryLoadError.value = null
                loading.value = false
                response.body()
            } else {
                onError("Error: ${response.message()}")
                null
            }
        }
    }
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = countriesService.getCountries()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countries.value = response.body()
                    countryLoadError.value = null
                    loading.value = false
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }



    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }
}