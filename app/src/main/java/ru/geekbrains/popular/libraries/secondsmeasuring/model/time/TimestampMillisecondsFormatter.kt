package ru.geekbrains.popular.libraries.secondsmeasuring.model.time

class TimestampMillisecondsFormatter {

    fun format(timestamp: Long): String {
        val millisecondsFormatted = (timestamp % 1000).pad(3)
        val seconds = timestamp / 1000
        val secondsFormatted = (seconds % 60).pad(2)
        val minutes = seconds / 60
        val minutesFormatted = (minutes % 60).pad(2)
        val hours = minutes / 60
        return if (hours > 0) {
            val hoursFormatted = (minutes / 60).pad(2)
            "$hoursFormatted:$minutesFormatted:$secondsFormatted"
        } else {
            "$minutesFormatted:$secondsFormatted:$millisecondsFormatted"
        }
    }

    private fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')

    /** Новые методы для тестирования к заданию 3 ДЗ */ //region
    // Метод для проверки на Equals и NotEquals
    fun format(timeFormat: Double): String {
        return format(timeFormat.toLong())
    }

    // Метод для проверки на ArrayEquals
    fun format(hours: Long, minutes: Long, seconds: Long): Array<String> {
        return arrayOf("$hours", "$minutes", "$seconds")
    }
    // Метод для проверки на Null и на NotNull
    fun format(timeFormat: String?): String? {
        return if (timeFormat == null) {
            null
        } else {
            format(timeFormat.toLong())
        }
    }
    // Метод для проверки на assertSame
    fun format(): String {
        return "00:00:000"
    }
    //endregion
}