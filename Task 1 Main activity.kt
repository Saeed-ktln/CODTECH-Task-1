package com.example.password

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var color: Int = R.color.weak

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

            val moveNxtAct = findViewById<Button>(R.id.button)

            moveNxtAct.setOnClickListener{
                val Intent = Intent(this,MainActivity2::class.java)
                startActivity(Intent)
                Toast.makeText(applicationContext, "Your Password has been recorded", Toast.LENGTH_LONG).show()
            }

         val passwordStrength = PasswordStrength()
        editTextTextPersonName.addTextChangedListener(passwordStrength)

        passwordStrength.leveldestrength.observe(this, Observer{leveldestrength ->
            if (leveldestrength != null) {
                displayStrengthLevel(leveldestrength)
            }
        })

        passwordStrength.colordestrength.observe(this, Observer {colordestrength ->
            if (colordestrength != null) {
                color = colordestrength
            }
        })

        passwordStrength.lowercase.observe(this, Observer {value ->

            displayPasswordSuggestion(value, textView3, lowercase)

        })
        passwordStrength.uppercase.observe(this, Observer { value ->

            displayPasswordSuggestion(value, textView4, uppercase)

        })
        passwordStrength.digits.observe(this, Observer { value ->

            displayPasswordSuggestion(value, textView5, digits)

        })
        passwordStrength.special.observe(this, Observer { value ->

            displayPasswordSuggestion(value, textView6, specialchar)
        })

    }



    private fun displayPasswordSuggestion(value: Int?, textView: TextView, lowercase: TextView) {
        if (value == 1){
            textView.setTextColor(ContextCompat.getColor(this, R.color.bulletproof))
            lowercase.setTextColor(ContextCompat.getColor(this,R.color.bulletproof))
        }else{
            textView.setTextColor(ContextCompat.getColor(this, R.color.white))
            lowercase.setTextColor(ContextCompat.getColor(this,R.color.white))
        }
    }

    private fun displayStrengthLevel(leveldestrength: String) {
        button.isEnabled = leveldestrength.contains("BULLETPROOF")

        levelstrength.text = leveldestrength
        levelstrength.setTextColor(ContextCompat.getColor(this, color))
        view.setBackgroundColor(ContextCompat.getColor(this, color))
    }
}


