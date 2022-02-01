package ru.geekbrains.popular.libraries.secondsmeasuring.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.geekbrains.popular.libraries.secondsmeasuring.application.Constants

class DataSource {
    /** Задание переменных */ //region
    // DataBase
    private val dataBase: DataBase = DataBase

    // Data
    val dataFirst: Flow<String> = flow {
        while (true) {
            val dataFromDataBaseFirst = dataBase.fetchFirstData()
            emit(dataFromDataBaseFirst.toString())
            delay(Constants.DELAY_UPDATE_TIME)
        }
    }
        .flowOn(Dispatchers.Default)
        .catch { e ->
            println(e.message) //Error!
        }
    val dataSecond: Flow<String> = flow {
        while (true) {
            val dataFromDataBaseSecond = dataBase.fetchSecondData()
            emit(dataFromDataBaseSecond.toString())
            delay(Constants.DELAY_UPDATE_TIME)
        }
    }
        .flowOn(Dispatchers.Default)
        .catch { e ->
            println(e.message) //Error!
        }
    //endregion

    fun setTimerFirstAction(timerAction: Constants.Companion.TIMER_ACTIONS) {
        dataBase.setTimerFirstAction(timerAction)
    }

    fun setTimerSecondAction(timerAction: Constants.Companion.TIMER_ACTIONS) {
        dataBase.setTimerSecondAction(timerAction)
    }
}