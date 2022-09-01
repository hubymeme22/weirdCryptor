package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.util.Log
import android.view.View

private const val debugTag: String = "MAIN_ACTIVITY_TEST";

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button retrieval onclick listeners
        val encryptButton: androidx.appcompat.widget.AppCompatButton = findViewById(R.id.encrypt_button)
        val decryptButton: androidx.appcompat.widget.AppCompatButton = findViewById(R.id.decrypt_button)

        // TextInput views for inputs
        val keyInput: TextView = findViewById(R.id.key)
        val decryptedInput: EditText = findViewById(R.id.inputText)
        val encryptedInput: EditText = findViewById(R.id.outputText)

        encryptButton.setOnClickListener() {
            it.setOnClickListener(View.OnClickListener {
                if (decryptedInput.text.toString() == "") {
                    this.noProperInputToast()
                } else if (keyInput.text.toString() == "") {
                    this.noKeyToast()
                } else {
                    Log.v(debugTag, "ChugChugan na")
                }
            })
        }

        decryptButton.setOnClickListener() {
            it.setOnClickListener(View.OnClickListener {
                if (encryptedInput.text.toString() == "") {
                    this.noProperInputToast()
                } else if (keyInput.text.toString() == "") {
                    this.noKeyToast()
                } else {
                    Log.v(debugTag, "ChugChugan na")
                }
            })
        }
    }


    ////////////////////////////////
    //  Toasts                    //
    //  Displays toast on screen  //
    ////////////////////////////////
    private fun conversionToast() {
        val toast = Toast.makeText(this, "Converting text into readable digits", Toast.LENGTH_LONG)
        Log.d(debugTag, "conversionToast called!")
        toast.show()
    }

    private fun noKeyToast() {
        val toast = Toast.makeText(this, "Please Enter a key", Toast.LENGTH_LONG)
        Log.d(debugTag, "noKeyToast called!")
        toast.show()
    }

    private fun noProperInputToast() {
        val toast = Toast.makeText(this, "Please Enter proper input", Toast.LENGTH_LONG)
        Log.d(debugTag, "noProperInputToast called!")
        toast.show()
    }
}