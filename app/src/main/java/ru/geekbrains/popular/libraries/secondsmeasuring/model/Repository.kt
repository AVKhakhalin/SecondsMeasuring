package ru.geekbrains.popular.libraries.secondsmeasuring.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.geekbrains.popular.libraries.secondsmeasuring.application.Constants

class Repository {
    /** Задание переменных */ //region
    // DataSource
    private val dataSource: DataSource = DataSource()

    //dataData
    val dataData: Flow<Data> = dataSource.data.map { data -> Data(data) }
    //endregion

    fun setTimerAction(timerAction: Constants.Companion.TIMER_ACTIONS) {
        dataSource.setTimerAction(timerAction)
    }
}