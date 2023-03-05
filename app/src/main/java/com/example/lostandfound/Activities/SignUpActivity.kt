package com.example.lostandfound.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lostandfound.Firebase.firestore
import com.example.lostandfound.R
import com.example.lostandfound.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : baseactivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupActionBar()
       findViewById<Button>(R.id.btn_signup).setOnClickListener {
           userregistered()
       }
        val btnsignup=findViewById<View>(R.id.intentsignup)
        btnsignup.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
        }

    }
    fun setupActionBar(){
        val toolbar123=findViewById<androidx.appcompat.widget.Toolbar>(R.id.search_bar)
        setSupportActionBar(toolbar123)
        val actionbar=supportActionBar
        if(actionbar!=null)
        {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        }
        toolbar123.setNavigationOnClickListener{
            onBackPressed()

        }

    }
    private fun formvalidate(name:String,email:String,password:String)
    : Boolean {
        return when{
            TextUtils.isEmpty(name)->{
                showerrorsnackbar("Please enter your Name")
                false
            }
            TextUtils.isEmpty(email)->{
                showerrorsnackbar("Please enter your Email Address")
                false
            }
            TextUtils.isEmpty(password)->{
                showerrorsnackbar("Please enter your Password")
                false
            }
            else->{
                true
            }

        }



    }
    fun userregisteredsuccess()
    {

        val layout  = layoutInflater.inflate(R.layout.custom_toast_layout,findViewById(R.id.view_layout_of_toast))
        val toast:Toast= Toast(applicationContext)
        toast.view=layout
        val  txtmst:TextView=layout.findViewById(R.id.textview_toast)
        txtmst.setText("you have successfully registered so please Sign In ")

        toast.duration.toLong()
        toast.show()
        hideprogressdialog()

        FirebaseAuth.getInstance().signOut()
        finish()


    }


 private fun userregistered()
 {
     val name:String=findViewById<TextView>(R.id.et_name).text.toString().trim {it<=' '}
     val email:String=findViewById<TextView>(R.id.et_email).text.toString().trim {it<=' '}
     val password:String=findViewById<TextView>(R.id.et_password).text.toString().trim{it<=' '}
     if(formvalidate(name,email,password))
     {
        showprogressdialog("Signing Up...")
         FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->

             if (task.isSuccessful) {
                 val firebase: FirebaseUser = task.result!!.user!!
                 val registeredemail = firebase.email!!
                 val user:User=User(firebase.uid,name,registeredemail)
                 firestore().registeruser(this,user)

             } else {
                 val layout1 =layoutInflater.inflate(R.layout.error_toast_layout,findViewById(R.id.view_layout_of_toast1))
                 val toast1: Toast=Toast(applicationContext)
                 toast1.view=layout1
                 val txtmsg:TextView=layout1.findViewById(R.id.textview_toast1)
                 txtmsg.setText(task.exception!!.message)
                 toast1.duration.toShort()
                 toast1.show()

             }
         }
     }





 }





}











