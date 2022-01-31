package ru.geekbrains.popular.libraries.secondsmeasuring.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.geekbrains.popular.libraries.secondsmeasuring.model.Data
import ru.geekbrains.popular.libraries.secondsmeasuring.model.Repository

internal class MainViewModel(
    repository: Repository = Repository()
): ViewModel() {

    //val liveData: LiveData<Data> = repository.userData.asLiveData()
    val liveData: MutableLiveData<Data> = MutableLiveData()

    init {
        viewModelScope.launch {
//            delay(1000) // Задержка запуска потока
            repository.dataData.flowOn(Dispatchers.Main)
//                .onEach { delay(1000) } // Задержка в самом потоке
                //import kotlinx.coroutines.flow.collect
                .collect { data ->
                    liveData.value = data
                }
        }
    }
}