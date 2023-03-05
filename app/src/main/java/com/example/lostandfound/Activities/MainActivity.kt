package com.example.lostandfound.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.lostandfound.R
import com.example.lostandfound.databinding.ActivityMainBinding
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var doublebacktoexitpressedonce=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



      // another code for function of double back pressed
//         doubleheader()

        // code for doublebackpressed
//      val onbackpressedback=object: OnBackPressedCallback(true)
//      {
//          override fun handleOnBackPressed() {
//              Toast.makeText(this@MainActivity, "Bye", Toast.LENGTH_SHORT).show()
//               finish()
//          }
//      }
//        onBackPressedDispatcher.addCallback(onbackpressedback)



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
    // function for backbutton still not works
    private fun doubleheader() {

        if(supportFragmentManager.backStackEntryCount >0)
        {
            supportFragmentManager.popBackStack()
        }

        else if(!doublebacktoexitpressedonce)
        {
            this.doublebacktoexitpressedonce=true
//            Toast.makeText(this,"please click back once again to exit",Toast.LENGTH_SHORT).show()

            Handler().postDelayed({
                doublebacktoexitpressedonce=false
            },2000)


        }

        else {

            super.onBackPressed()
            exitProcess(0)
            return


        }
    }



}
