package com.example.lostandfound.Activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import com.example.lostandfound.R

import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


open class baseactivity : AppCompatActivity() {

    private var doublebacktoexitpressedonce=false
    private lateinit var  mprogressdialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baseactivity)



    }



    fun showprogressdialog(Text:String){
        mprogressdialog=Dialog(this)
        mprogressdialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mprogressdialog.setContentView(R.layout.dialog_progress)

        mprogressdialog.findViewById<TextView>(R.id.tv_progress_text).text=Text

        mprogressdialog.show()
        Handler().postDelayed({},2500)


    }
    fun hideprogressdialog()
    {
        mprogressdialog.dismiss()
    }
    fun getcurrentuserid():String
    {
        return FirebaseAuth.getInstance().currentUser!!.uid

    }
fun doublebacktoexit()
{
    if(doublebacktoexitpressedonce)
    {
        super.onBackPressed()
        return
    }
    this.doublebacktoexitpressedonce=true

    Toast.makeText(this, "Click back once again to Exit", Toast.LENGTH_SHORT).show()
    Handler().postDelayed({doublebacktoexitpressedonce=false},1500)



}
    fun showerrorsnackbar(message:String){
    val snackbar =Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG)
        val snackbarview=snackbar.view
    snackbarview.setBackgroundColor(ContextCompat.getColor(this,R.color.snackbar_error_color))
    snackbar.show()

    }
    fun showsuccesfullnackbar(message:String){
        val snackbar1 =Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG)
        val snackbarview1=snackbar1.view
        snackbarview1.setBackgroundColor(ContextCompat.getColor(this,R.color.snackbar_succesful_color))
        snackbar1.show()

    }


}