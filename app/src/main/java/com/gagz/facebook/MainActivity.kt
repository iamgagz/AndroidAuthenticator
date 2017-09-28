package com.gagz.facebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gagz.facebook.util.SessionStore
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    private val TIME_LIMIT = 1500
    private var backPressed:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailView.setText(SessionStore().restoreEmail(this))

        btnLogout.onClick {

            logoutUser()
        }
    }

    private fun logoutUser() {

        val clear = SessionStore()
        clear.clearEmail(this)
        startActivity<LoginActivity>()
        toast("Logout Successful!")
        finish()
    }

    override fun onBackPressed() {

        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            toast("Press back again to exit")
        }
        backPressed = System.currentTimeMillis()
    }
}
