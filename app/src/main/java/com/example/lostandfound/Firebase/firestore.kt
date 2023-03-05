package com.example.lostandfound.Firebase

import android.util.Log
import com.example.lostandfound.Activities.SignInActivity
import com.example.lostandfound.Activities.SignUpActivity
import com.example.lostandfound.models.User
import com.example.lostandfound.utils.constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject

class firestore {
    private val firestore=FirebaseFirestore.getInstance()
    fun registeruser(activity:SignUpActivity,Userinfo:User)
    {
        firestore.collection("USERS").document(getcurrentuserid()).
        set(Userinfo, SetOptions.merge()).addOnSuccessListener {
            activity.userregisteredsuccess()
        }.addOnFailureListener{
            e->
            Log.e(activity.javaClass.simpleName,"Error writing document")
        }
    }
 fun getcurrentuserid():String
 {
     var currentuser=FirebaseAuth.getInstance().currentUser
      var currentuserid=""
     if(currentuser!=null)
     {
         currentuserid=currentuser.uid
     }
     return currentuserid
 }

    fun signinuser(activity: SignInActivity)
    {

        firestore.collection("USERS").document(getcurrentuserid()).
        get().addOnSuccessListener {document->
            val loggedinuserinfo=document.toObject(User::class.java)
            if(loggedinuserinfo!=null)
                 activity.Signinsuccess(loggedinuserinfo)
        }.addOnFailureListener{
                e->
            Log.e(activity.javaClass.simpleName,"Error in getting document")
        }



    }

}