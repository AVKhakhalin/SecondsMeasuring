package ru.geekbrains.popular.libraries.secondsmeasuring.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.geekbrains.popular.libraries.secondsmeasuring.application.Constants

class Repository {
    /** Задание переменных */ //region
    // DataSource
    private val firstDataSource: DataSource = DataSource()
    private val secondDataSource: DataSource = DataSource()

    //dataData
    val firstDataData: Flow<Data> = firstDataSource.dataFirst.map { data -> Data(data) }
    val secondDataData: Flow<Data> = secondDataSource.dataSecond.map { data -> Data(data) }
    //endregion

    fun setTimerFirstAction(timerAction: Constants.Companion.TIMER_ACTIONS) {
        firstDataSource.setTimerFirstAction(timerAction)
    }

    fun setTimerSecondAction(timerAction: Constants.Companion.TIMER_ACTIONS) {
        secondDataSource.setTimerSecondAction(timerAction)
    }
}