package edu.uoc.pac4.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import edu.uoc.pac4.R
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import edu.uoc.pac4.ui.login.oauth.OAuthActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
//No veo la necesidad de un viewModel para esta actividad
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Login with Twitch
        twitchLoginButton.setOnClickListener {
            startActivity(Intent(this, OAuthActivity::class.java))
        }
    }
}