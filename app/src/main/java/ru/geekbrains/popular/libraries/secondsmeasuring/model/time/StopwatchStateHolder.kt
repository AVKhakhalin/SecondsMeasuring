package ru.geekbrains.popular.libraries.secondsmeasuring.model.time

import ru.geekbrains.popular.libraries.secondsmeasuring.application.Constants

class StopwatchStateHolder(
    private val stopwatchStateCalculator: StopwatchStateCalculator,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timestampMillisecondsFormatter: TimestampMillisecondsFormatter
) {

    var currentState: StopwatchState = StopwatchState.Paused(0)
        private set

    fun start() {
        currentState = stopwatchStateCalculator.calculateRunningState(currentState)
    }

    fun pause() {
        currentState = stopwatchStateCalculator.calculatePausedState(currentState)
    }

    fun stop() {
        currentState = StopwatchState.Paused(0)
    }

    fun getStringTimeRepresentation(): String {
        val elapsedTime = when (val currentState = currentState) {
            is StopwatchState.Paused -> currentState.elapsedTime
            is StopwatchState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timestampMillisecondsFormatter.format(elapsedTime)
    }

    fun setTimerAction(timerAction: Constants.Companion.TIMER_ACTIONS) {
        when (timerAction) {
            Constants.Companion.TIMER_ACTIONS.START -> start()
            Constants.Companion.TIMER_ACTIONS.STOP -> stop()
            Constants.Companion.TIMER_ACTIONS.PAUSE -> pause()
        }
    }
}