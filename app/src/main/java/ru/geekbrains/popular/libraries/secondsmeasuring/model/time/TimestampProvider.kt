package ru.geekbrains.popular.libraries.secondsmeasuring.model.time

interface TimestampProvider {
    fun getMilliseconds(): Long
}