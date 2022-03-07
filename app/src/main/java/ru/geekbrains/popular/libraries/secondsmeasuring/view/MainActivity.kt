package ru.geekbrains.popular.libraries.secondsmeasuring.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.popular.libraries.secondsmeasuring.databinding.ActivityMainBinding
import ru.geekbrains.popular.libraries.secondsmeasuring.viewmodel.MainViewModel

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
        binding.buttonStart.setOnClickListener { viewModel?.let { it.startFirst() } }
        binding.buttonPause.setOnClickListener { viewModel?.let { it.pauseFirst() } }
        binding.buttonStop.setOnClickListener { viewModel?.let { it.stopFirst() } }
        //endregion

        //region Установка основных действий для работы с секундомером
        binding.buttonStartSecond.setOnClickListener { viewModel?.let { it.startSecond() } }
        binding.buttonPauseSecond.setOnClickListener { viewModel?.let { it.pauseSecond() } }
        binding.buttonStopSecond.setOnClickListener { viewModel?.let { it.stopSecond() } }
        //endregion
    }
}
