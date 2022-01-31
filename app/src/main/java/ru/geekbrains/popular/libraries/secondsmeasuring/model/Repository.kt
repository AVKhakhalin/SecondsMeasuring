package ru.geekbrains.popular.libraries.secondsmeasuring.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class Repository(dataSource: DataSource = DataSource()) {

    val dataData: Flow<Data> =
        dataSource.data.map { data -> Data(data) }
    //.onEach { saveInCache(it) }
}