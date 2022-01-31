package ru.geekbrains.popular.libraries.secondsmeasuring.model

import ru.geekbrains.popular.libraries.secondsmeasuring.application.Constants
import ru.geekbrains.popular.libraries.secondsmeasuring.model.time.*

object DataBase {
    /** Задание переменных */ // region
    // timestampProvider
    private val timestampProvider = object: TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    // stopwatchStateHolder
    private val stopwatchStateHolder: StopwatchStateHolder =
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        )
    //endregion

    fun fetchData() = stopwatchStateHolder.getStringTimeRepresentation()

    fun setTimerAction(timerAction: Constants.Companion.TIMER_ACTIONS) {
        stopwatchStateHolder.setTimerAction(timerAction)
    }
}