package ru.geekbrains.popular.libraries.secondsmeasuring.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.popular.libraries.secondsmeasuring.R
import ru.geekbrains.popular.libraries.secondsmeasuring.viewmodel.MainViewModel

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_time)
        ViewModelProvider(this).get(MainViewModel::class.java).liveData.observe(
            this,
            { dataFromDataBase ->
                textView.text = dataFromDataBase.data
            })
    }
}