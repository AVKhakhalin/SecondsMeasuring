package ru.geekbrains.popular.libraries.secondsmeasuring.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.geekbrains.popular.libraries.secondsmeasuring.application.Constants

class DataSource(
    private val dataBase: DataBase = DataBase
) {
    /** Задание переменных */ //region
    // Data
    val data: Flow<String> = flow {
        while (true) {
            val dataFromDataBase = dataBase.fetchData()
            emit(dataFromDataBase.toString())
            delay(Constants.DELAY_UPDATE_TIME)
        }
    }
        .flowOn(Dispatchers.Default)
        .catch { e ->
            println(e.message) //Error!
        }
    //endregion

    fun setTimerAction(timerAction: Constants.Companion.TIMER_ACTIONS) {
        dataBase.setTimerAction(timerAction)
    }
}