package edu.uoc.pac4.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.uoc.pac4.R
import edu.uoc.pac4.ui.login.LoginActivity
import edu.uoc.pac4.ui.streams.StreamsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LaunchActivity : AppCompatActivity() {

    private val launchViewModel by viewModel<LaunchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        checkUserSession()
    }

//    private fun checkUserSession() {
//        if (SessionManager(this).isUserAvailable()) {
//            // User is available, open Streams Activity
//            startActivity(Intent(this, StreamsActivity::class.java))
//        } else {
//            // User not available, request Login
//            startActivity(Intent(this, LoginActivity::class.java))
//        }
//        finish()
//    }

    private fun checkUserSession(){
        launchViewModel.getUserAvailability()
        launchViewModel.isUserAvailable.observe(this){
            if (it){
                startActivity(Intent(this, StreamsActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }

    }
}