package com.vin.animationdeo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionManager
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rootView: ConstraintLayout = findViewById(R.id.constraintRoot)

        /*Handler(Looper.getMainLooper()).postDelayed({
            val constraintSet = ConstraintSet()
            constraintSet.clone(this, R.layout.activity_main_end_keyframe)

            TransitionManager.beginDelayedTransition(rootView)

            constraintSet.applyTo(rootView)
        }, 2000)*/

        Handler(Looper.getMainLooper()).postDelayed({
            val constraintSet = ConstraintSet()
            constraintSet.clone(rootView)

            TransitionManager.beginDelayedTransition(rootView)

            val tvTest:TextView = findViewById(R.id.tvTest)
            tvTest.textSize = 50.0f

            //constraintSet.clear(R.id.tvTest, ConstraintSet.BOTTOM)
            constraintSet.setMargin(R.id.tvTest, ConstraintSet.TOP, 0)

            constraintSet.applyTo(rootView)
        }, 2000)
    }
}