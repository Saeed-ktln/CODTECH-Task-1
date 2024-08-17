package com.example.password

import android.arch.lifecycle.MutableLiveData
import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Matcher
import java.util.regex.Pattern
// a class which defines the strength of password
class PasswordStrength:TextWatcher {


    //the variables containing a mutable live data

    var leveldestrength : MutableLiveData<String> = MutableLiveData()
    var colordestrength : MutableLiveData<Int> = MutableLiveData()

    var lowercase : MutableLiveData<Int> = MutableLiveData()
    var uppercase : MutableLiveData<Int> = MutableLiveData()
    var digits : MutableLiveData<Int> = MutableLiveData()
    var special : MutableLiveData<Int> = MutableLiveData()


    //this function tells the computer how to calculate strength based on the users input

    private fun calculatestrength(password: CharSequence){
        if (password.length in 0..5){
            colordestrength.value = R.color.weak
            leveldestrength.value = "WEAK"
        }else if (password.length in 6..7){

            if (lowercase.value == 1 || uppercase.value == 1 || digits.value == 1 || special.value == 1){
                colordestrength.value = R.color.medium
                leveldestrength.value = "MEDIUM"
            }
        }else if (password.length in 8..10){
            if (lowercase.value == 1 || uppercase.value == 1 || digits.value ==1 || special.value == 1){
                if (lowercase.value == 1 && uppercase.value ==1){
                    colordestrength.value = R.color.strong
                    leveldestrength.value = "STRONG"
                }
            }
        }else if (password.length >10){

            if (lowercase.value == 1 && uppercase.value == 1 && digits.value == 1 && special.value == 1){
                colordestrength.value = R.color.bulletproof
                leveldestrength.value = "BULLETPROOF"
            }
        }
    }

    override fun afterTextChanged(p0: Editable?) {}
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
// this is what happens on changing text in the user interface
    override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (char!=null){
            lowercase.value = if (char.haslowercase()) { 1 } else { 0 }
            uppercase.value = if (char.hasuppercase()) { 1 } else { 0 }
            digits.value = if (char.hasdigits()) { 1 } else { 0 }
            special.value = if (char.hasspecialchar()) { 1 } else { 0 }
            calculatestrength(char)
        }
    }
    // this creates a pattern and searches for lowercase letters entered by the user
    private fun CharSequence.haslowercase():Boolean{

        val pattern: Pattern = Pattern.compile("[a-z]")
        val haslowercase: Matcher = pattern.matcher(this)
        return haslowercase.find()// line3

    }

    // this creates a pattern and searches for uppercase letters entered by the user

    private fun CharSequence.hasuppercase():Boolean{

        val pattern: Pattern = Pattern.compile("[A-Z]")
        val hasuppercase: Matcher = pattern.matcher(this)
        return hasuppercase.find()

    }

    // this creates a pattern and searches for digits entered by the user
    private fun CharSequence.hasdigits():Boolean{

        val pattern: Pattern = Pattern.compile("[0-9]")
        val hasdigits: Matcher = pattern.matcher(this)
        return hasdigits.find()

    }

    // this creates a pattern and searches for special characters entered by the user

    private fun CharSequence.hasspecialchar():Boolean{

        val pattern: Pattern = Pattern.compile("[|•√π÷×§∆£¢€¥^°=%©®™✓\\[\\]@#$&+/*\"':;!?><_-]")
        val hasspecialchar: Matcher = pattern.matcher(this)
        return hasspecialchar.find()

    }



}
