package com.example.lostandfound.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.lostandfound.R

class IntroActivity12 : AppCompatActivity() {
    var videoView: VideoView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro12)
        videoView = findViewById(R.id.videoview)
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.clouds)
        videoView?.setVideoURI(uri)
        videoView?.start()
        videoView?.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })
        val typeFace: Typeface = Typeface.createFromAsset(assets,"carbon bl.otf")
        val textview123: TextView =findViewById(R.id.textView)
        textview123.typeface=typeFace

        val btnsignup=findViewById<Button>(R.id.button2)

        btnsignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))

        }
        val btnsignin=findViewById<Button>(R.id.button1)
        btnsignin.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))

        }






    }



    override fun onPostResume() {
        videoView!!.resume()
        super.onPostResume()
    }

    override fun onRestart() {
        videoView!!.start()
        super.onRestart()
    }

    override fun onPause() {
        videoView!!.suspend()
        super.onPause()
    }

    override fun onDestroy() {
        videoView!!.stopPlayback()
        super.onDestroy()
    }
}