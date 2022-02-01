package ru.geekbrains.popular.libraries.secondsmeasuring.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import ru.geekbrains.popular.libraries.secondsmeasuring.application.Constants
import ru.geekbrains.popular.libraries.secondsmeasuring.model.Data
import ru.geekbrains.popular.libraries.secondsmeasuring.model.Repository

class MainViewModel(
    private val repository: Repository = Repository()
): ViewModel() {
    /** Задание переменных */ //region
    // Job
    private var firstJob: Job? = null
    private var secondJob: Job? = null

    // LiveData
    val firstLiveData: MutableLiveData<Data> = MutableLiveData()
    val secondLiveData: MutableLiveData<Data> = MutableLiveData()

    // ViewModelScope
    private val firstScope: CoroutineScope = viewModelScope
    private val secondScope: CoroutineScope = viewModelScope

    // TimerAction
    private var firstTimerActions: Constants.Companion.TIMER_ACTIONS =
        Constants.Companion.TIMER_ACTIONS.STOP
    private var secondTimerActions: Constants.Companion.TIMER_ACTIONS =
        Constants.Companion.TIMER_ACTIONS.STOP
    //endregion

    /** Методы управления первым таймером */ //region
    fun startFirst() {
        firstTimerActions = Constants.Companion.TIMER_ACTIONS.START
        if (firstJob == null) startFirstJob()
        repository.setTimerFirstAction(firstTimerActions)
    }

    fun pauseFirst() {
        firstTimerActions = Constants.Companion.TIMER_ACTIONS.PAUSE
        repository.setTimerFirstAction(firstTimerActions)
    }

    fun stopFirst() {
        firstTimerActions = Constants.Companion.TIMER_ACTIONS.STOP
        repository.setTimerFirstAction(firstTimerActions)
       firstJob = null
        firstScope.launch {
            delay(Constants.DELAY_UPDATE_TIME)
            firstScope.coroutineContext.cancelChildren()
            startSecondJob()
        }
    }
    //endregion

    /** Методы управления вторым таймером */ //region
    fun startSecond() {
        secondTimerActions = Constants.Companion.TIMER_ACTIONS.START
        if (secondJob == null) startSecondJob()
        repository.setTimerSecondAction(secondTimerActions)
    }

    fun pauseSecond() {
        secondTimerActions = Constants.Companion.TIMER_ACTIONS.PAUSE
        repository.setTimerSecondAction(secondTimerActions)
    }

    fun stopSecond() {
        secondTimerActions = Constants.Companion.TIMER_ACTIONS.STOP
        repository.setTimerSecondAction(secondTimerActions)
        secondJob = null
        secondScope.launch {
            delay(Constants.DELAY_UPDATE_TIME)
            secondScope.coroutineContext.cancelChildren()
            startFirstJob()
        }
    }
    //endregion

    // Запуск первого таймера
    private fun startFirstJob() {
        firstScope.launch {
            while (isActive) {
                repository.firstDataData.flowOn(Dispatchers.Main)
                    // Для использования .collect нужно: import kotlinx.coroutines.flow.collect
                    .collect { data ->
                        if (firstTimerActions != Constants.Companion.TIMER_ACTIONS.STOP)
                            firstLiveData.value = data
                        else
                            firstLiveData.value = Constants.DEFAULT_TIME
                    }
            }
        }
    }

    // Запуск второго таймера
    private fun startSecondJob() {
        secondScope.launch {
            while (isActive) {
                repository.secondDataData.flowOn(Dispatchers.Main)
                    // Для использования .collect нужно: import kotlinx.coroutines.flow.collect
                    .collect { data ->
                        if (secondTimerActions != Constants.Companion.TIMER_ACTIONS.STOP)
                            secondLiveData.value = data
                        else
                            secondLiveData.value = Constants.DEFAULT_TIME
                    }
            }
        }
    }
}