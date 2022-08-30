package com.assignment.dogbreeds.application.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assignment.dogbreeds.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DogCeoMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_ceo_main)
    }
}