package ru.geekbrains.popular.libraries.secondsmeasuring.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.geekbrains.popular.libraries.secondsmeasuring.databinding.ActivityMainBinding
import ru.geekbrains.popular.libraries.secondsmeasuring.viewmodel.MainViewModel
import kotlin.random.Random

class MainActivity: AppCompatActivity() {
    /** Задание переменных */ //region
    // Binding
    private lateinit var binding: ActivityMainBinding

    // ViewModel
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Установка binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Установка TextView
        val textViewFirst = binding.textTime
        val textViewSecond = binding.textTimeSecond
        // Настройка ViewModel
        viewModel.firstLiveData.observe(
            this,
            { dataFromDataBaseFirst ->
                textViewFirst.text = dataFromDataBaseFirst.data
            })
        viewModel.secondLiveData.observe(
            this,
            { dataFromDataBaseSecond ->
                textViewSecond.text = dataFromDataBaseSecond.data
            })

        //region Установка основных действий для работы с секундомером
        binding.buttonStart.setOnClickListener { viewModel.startFirst() }
        binding.buttonPause.setOnClickListener { viewModel.pauseFirst() }
        binding.buttonStop.setOnClickListener { viewModel.stopFirst() }
        //endregion

        //region Установка основных действий для работы с секундомером
        binding.buttonStartSecond.setOnClickListener { viewModel.startSecond() }
        binding.buttonPauseSecond.setOnClickListener { viewModel.pauseSecond() }
        binding.buttonStopSecond.setOnClickListener { viewModel.stopSecond() }
        //endregion

        val liveData: LiveData<Data> = Repository().userData.asLiveData()

/*val liveData: MutableLiveData<Data> = MutableLiveData()

init {
   viewModelScope.launch {
       repository.userData.flowOn(Dispatchers.Main)
           .collect { data ->
               liveData.value = data
           }
   }
}*/

    }

    internal data class Data(val data: String)

    internal object DataBase {
        fun fetchData() = Random.nextInt()
    }

    internal class DataSource(
        private val dataBase: DataBase = DataBase,
        private val refreshIntervalMs: Long = 1000
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

    internal class Repository(dataSource: DataSource = DataSource()) {

        val userData: Flow<Data> =
            dataSource.data.map { data -> Data(data) }
        //.onEach { saveInCache(it) }
    }

    internal class MainViewModelOther(
        repository: Repository = Repository()
    ): ViewModel() {

        val liveData: MutableLiveData<Data> = MutableLiveData()

        init {
            viewModelScope.launch {
                repository.userData.flowOn(Dispatchers.Main)
                    .collect { data ->
                        liveData.value = data
                    }
            }
        }
    }
}
