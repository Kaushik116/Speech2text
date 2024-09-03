package com.kaushik.voicetotext

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.Toast
import java.util.Locale
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private val RQ_SPEECH_REC = 102
    private lateinit var mbtn_button: Button
//    private lateinit var mtv_text: TextView
    private lateinit var medit_text_id: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mbtn_button =findViewById(R.id.btn_button)
        medit_text_id = findViewById(R.id.edit_text_id)

        mbtn_button.setOnClickListener {
            askspeechInput()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
//            mtv_text.text = result?.get(0).toString()
            medit_text_id.setText(result?.get(0).toString())
        }
    }

    private fun askspeechInput(){
        if(!SpeechRecognizer.isRecognitionAvailable(this)){
            Toast.makeText(this,"Speech recognition is not available",Toast.LENGTH_SHORT).show()
        }else{
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something!")
            startActivityForResult(i,RQ_SPEECH_REC)
        }
    }
}