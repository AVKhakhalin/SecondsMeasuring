package ru.geekbrains.popular.libraries.secondsmeasuring.model.time

// мы получаем текущее время, сравниваем его со старт таймом
// если больше, то высчитываем разницу, если меньше, то ставим
class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider,
) {

    fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}