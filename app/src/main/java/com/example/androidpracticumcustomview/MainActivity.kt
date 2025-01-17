package com.example.androidpracticumcustomview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidpracticumcustomview.ui.theme.CustomContainer
import com.example.androidpracticumcustomview.ui.theme.MainScreen

/*
Задание:
Реализуйте необходимые компоненты.
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        Раскомментируйте нужный вариант
         */
        startXmlPracticum() // «традиционный» android (XML)
//          setContent { // Jetpack Compose
//              MainScreen()
//          }
    }

    private fun startXmlPracticum() {
        val customContainer = CustomContainer(this)
        setContentView(customContainer)

        val firstView = TextView(this).apply {
            text = context.getString(R.string.hello)
            gravity = Gravity.CENTER
            textSize = 30f
        }

        val secondView = TextView(this).apply {
            text = context.getString(R.string.world)
            gravity = Gravity.CENTER
            textSize = 30f
        }

        customContainer.addView(firstView)

        // Добавление второго элемента через некоторое время
        Handler(Looper.getMainLooper()).postDelayed({
            customContainer.addView(secondView)
        }, 2000)
    }
}