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
    private val firstStopwatchStateHolder: StopwatchStateHolder =
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        )
    private val secondStopwatchStateHolder: StopwatchStateHolder =
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        )
    //endregion

    fun fetchFirstData() = firstStopwatchStateHolder.getStringTimeRepresentation()
    fun setTimerFirstAction(timerAction: Constants.Companion.TIMER_ACTIONS) {
        firstStopwatchStateHolder.setTimerAction(timerAction)
    }

    fun fetchSecondData() = secondStopwatchStateHolder.getStringTimeRepresentation()
    fun setTimerSecondAction(timerAction: Constants.Companion.TIMER_ACTIONS) {
        secondStopwatchStateHolder.setTimerAction(timerAction)
    }
}