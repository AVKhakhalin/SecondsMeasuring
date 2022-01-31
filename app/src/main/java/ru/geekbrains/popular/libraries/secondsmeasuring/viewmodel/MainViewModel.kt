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
    private var job: Job? = null

    // LiveData
    val liveData: MutableLiveData<Data> = MutableLiveData()

    // ViewModelScope
    private val scope: CoroutineScope = viewModelScope

    // TimerAction
    private var timerActions: Constants.Companion.TIMER_ACTIONS =
        Constants.Companion.TIMER_ACTIONS.STOP
    //endregion

    /** Методы управления таймером */ //region
    fun start() {
        timerActions = Constants.Companion.TIMER_ACTIONS.START
        if (job == null) startJob()
        repository.setTimerAction(Constants.Companion.TIMER_ACTIONS.START)
    }

    fun pause() {
        timerActions = Constants.Companion.TIMER_ACTIONS.PAUSE
        repository.setTimerAction(Constants.Companion.TIMER_ACTIONS.PAUSE)
    }

    fun stop() {
        timerActions = Constants.Companion.TIMER_ACTIONS.STOP
        repository.setTimerAction(Constants.Companion.TIMER_ACTIONS.STOP)
        job = null
        scope.launch {
            delay(Constants.DELAY_UPDATE_TIME)
            scope.coroutineContext.cancelChildren()
        }
    }
    //endregion


    private fun startJob() {
        scope.launch {
            while (isActive) {
                repository.dataData.flowOn(Dispatchers.Main)
//                .onEach { delay(1000) } // Задержка в самом потоке
                    // Для использования .collect нужно: import kotlinx.coroutines.flow.collect
                    .collect { data ->
                        if (timerActions != Constants.Companion.TIMER_ACTIONS.STOP)
                            liveData.value = data
                        else
                            liveData.value = Constants.DEFAULT_TIME
                    }
            }
        }
    }
}