package com.example.lostandfound.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lostandfound.R
import com.example.lostandfound.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addfragment(homescreenFragment(),0)
        binding.bottomnavigationBar.itemIconTintList=null
        binding.bottomnavigationBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home123->addfragment(homescreenFragment(),1)
                R.id.lost123->addfragment(LostScreenFragment(),1)
                R.id.found123->addfragment(FoundScreenFragment(),1)

                else ->{

            }

        }
         true
        }



    }
    private fun addfragment(fragments:Fragment,Flags:Int)
    {
        val fragmentmanager=supportFragmentManager
        val fragmentTransaction=fragmentmanager.beginTransaction()
        if(Flags==1)
        {
            fragmentTransaction.replace(R.id.framelayout,fragments)

        }
        else
        {
            fragmentTransaction.add(R.id.framelayout,fragments)

        }
        fragmentTransaction.commit()
    }




}