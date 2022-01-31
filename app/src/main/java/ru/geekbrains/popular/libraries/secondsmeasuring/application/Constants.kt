package ru.geekbrains.popular.libraries.secondsmeasuring.application

import ru.geekbrains.popular.libraries.secondsmeasuring.model.Data

class Constants {
    companion object {
        // Задержка в обновлении времени, в миллисекундах (мс)
        const val DELAY_UPDATE_TIME: Long = 20

        // Отображение времени по-умолчанию
        val DEFAULT_TIME: Data = Data("00:00:000")

        // Действия для таймера
        enum class TIMER_ACTIONS {
            START,
            STOP,
            PAUSE
        }
    }
}