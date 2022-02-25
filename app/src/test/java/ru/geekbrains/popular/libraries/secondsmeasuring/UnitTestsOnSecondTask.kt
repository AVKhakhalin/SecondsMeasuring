package ru.geekbrains.popular.libraries.secondsmeasuring

import android.text.format.Time
import org.junit.Assert
import org.junit.Test
import ru.geekbrains.popular.libraries.secondsmeasuring.model.time.TimestampMillisecondsFormatter

class UnitTestsOnSecondTask {
    /** Выполнение 2 пункта ДЗ к уроку №1 */ //region
    @Test
    fun millisecondsFormatter_CorrectOutputMillisecondsTime_ReturnsTrue() {
        Assert.assertEquals(
            "Корректный вывод миллисекунд классом TimestampMillisecondsFormatter()",
            TimestampMillisecondsFormatter().format(123),
            "00:00:123")
    }
    @Test
    fun millisecondsFormatter_CorrectOutputSecondsTime_ReturnsTrue() {
        Assert.assertEquals(
    "Корректный вывод минут классом TimestampMillisecondsFormatter()",
            TimestampMillisecondsFormatter().format(45000),
            "00:45:000")
    }
    @Test
    fun millisecondsFormatter_CorrectOutputHoursTime_ReturnsTrue() {
        Assert.assertEquals(
    "Корректный вывод часов классом TimestampMillisecondsFormatter()",
            TimestampMillisecondsFormatter().format(720000),
            "12:00:000")
    }
    @Test
    fun millisecondsFormatter_CorrectOutputAllLargeTime_ReturnsTrue() {
        Assert.assertEquals(
    "Корректный вывод часов, минут, миллисекунд классом TimestampMillisecondsFormatter()",
            TimestampMillisecondsFormatter().format(43990100),
            "12:13:10")
    }
    //endregion
    /** Выполнение 3 пункта ДЗ */ //region
    // a. Проверка на Equals
    @Test
    fun millisecondsFormatter_AssertEquals_ReturnsTrue() {
        Assert.assertEquals(
    "Корректное сравнение с эталонным результатом TimestampMillisecondsFormatter()",
            TimestampMillisecondsFormatter().format(10.5),
            "00:00:010")
    }
    // b. Проверка на NotEquals
    @Test
    fun millisecondsFormatter_AssertNotEquals_ReturnsTrue() {
        Assert.assertNotEquals(
    "Корректное сравнение различного результат класса TimestampMillisecondsFormatter()",
            TimestampMillisecondsFormatter().format(39.9),
            "00:00:040")
    }
    // c. Проверка на ArrayEquals
    @Test
    fun millisecondsFormatter_AssertArraysEquals_ReturnsTrue() {
        Assert.assertArrayEquals(
    "Корректное сравнение массива, полученного от класса TimestampMillisecondsFormatter()",
            TimestampMillisecondsFormatter().
            format(12, 34, 56), arrayOf<String>("12", "34", "56"))
    }
    // d. Проверка на Null
    @Test
    fun millisecondsFormatter_AssertNull_ReturnsTrue() {
        Assert.assertNull(
    "Корректный сравнение нулевого результата от класса TimestampMillisecondsFormatter()",
            TimestampMillisecondsFormatter().format(null))
    }
    // e. Проверка на NotNull
    @Test
    fun millisecondsFormatter_AssertNotNull_ReturnsTrue() {
        Assert.assertNotNull(
    "Корректное сравнение не нулевого результата от класса TimestampMillisecondsFormatter()",
            TimestampMillisecondsFormatter().format("10"))
    }
    // f. Проверка на AssertSame
    @Test
    fun millisecondsFormatter_AssertSame_ReturnsTrue() {
        Assert.assertSame(
    "Корректное сравнение объекта, полученного от класса TimestampMillisecondsFormatter()",
            TimestampMillisecondsFormatter().format(), "00:00:000")
    }
    //endregion
}