package ru.geekbrains.popular.libraries.secondsmeasuring.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class DataSource(
    private val dataBase: DataBase = DataBase,
    private val refreshIntervalMs: Long = 20000
) {
    val data: Flow<String> = flow {
        while (true) {
            val dataFromDataBase = dataBase.fetchData()
            emit(dataFromDataBase.toString())
            delay(refreshIntervalMs)
        }
    }
        .flowOn(Dispatchers.Default)
        .catch { e ->
            println(e.message)//Error!
        }
}