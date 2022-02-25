package ru.geekbrains.popular.libraries.secondsmeasuring

import org.junit.Assert
import org.junit.Test
import ru.geekbrains.popular.libraries.secondsmeasuring.model.time.TimestampMillisecondsFormatter

class UnitTestsOnSecondTask {
    /** Выполнение 2 пункта ДЗ к уроку №1 */ //region
    @Test
    fun millisecondsFormatter_CorrectOutputMillisecondsTime_ReturnsTrue() {
        Assert.assertEquals(TimestampMillisecondsFormatter().format(123),
            "00:00:123")
    }
    @Test
    fun millisecondsFormatter_CorrectOutputSecondsTime_ReturnsTrue() {
        Assert.assertEquals(TimestampMillisecondsFormatter().format(45000),
            "00:45:000")
    }
    @Test
    fun millisecondsFormatter_CorrectOutputHoursTime_ReturnsTrue() {
        Assert.assertEquals(TimestampMillisecondsFormatter().format(720000),
            "12:00:000")
    }
    @Test
    fun millisecondsFormatter_CorrectOutputAllLargeTime_ReturnsTrue() {
        Assert.assertEquals(TimestampMillisecondsFormatter().format(43990100),
            "12:13:10")
    }
    //endregion
}