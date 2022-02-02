package com.alereavila.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    //variable global
    var tts : TextToSpeech ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts= TextToSpeech(this,this)


        findViewById<Button>(R.id.btnPlay).setOnClickListener { speaker() }

        //Log.i("mensaje TextView",mensaje)

    }
    private fun speaker (){
        var mensaje:String=findViewById<EditText>(R.id.etMensaje).text.toString()
        if(mensaje.isEmpty()){

            findViewById<TextView>(R.id.tvStatus).text="Introduzaca un texto"
            mensaje="Ya escribe algo hijo de perra"
        }
        tts!!.speak(mensaje,TextToSpeech.QUEUE_FLUSH,null,"")

    }
    override fun onInit(status: Int) {

        if(status==TextToSpeech.SUCCESS) {

            findViewById<TextView>(R.id.tvStatus).text="they are all assholes!"
            tts !!.setLanguage(Locale("ES"))
        }else{
            findViewById<TextView>(R.id.tvStatus).text="No disponible "
        }
    }

    override fun onDestroy() {
        if (tts!=null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}