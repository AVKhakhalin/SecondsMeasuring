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
    private var viewModel: MainViewModel? = null
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Установка binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Установка TextView
        val textView = binding.textTime
        // Создание ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Настройка ViewModel
        viewModel?.let {
            it.liveData.observe(
                this,
                { dataFromDataBase ->
                    textView.text = dataFromDataBase.data
                })
        }

        //region Установка основных действий для работы с секундомером
        binding.buttonStart.setOnClickListener { viewModel?.let { it.start() } }
        binding.buttonPause.setOnClickListener { viewModel?.let { it.pause() } }
        binding.buttonStop.setOnClickListener { viewModel?.let { it.stop() } }
        //endregion
    }
}
