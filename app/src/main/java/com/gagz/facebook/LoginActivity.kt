package com.gagz.facebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.gagz.facebook.util.SessionStore
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        btnLogin.onClick {

            val email = emailEdit.text.toString()
            val password = passwordEdit.text.toString()

            validate(email, password)
        }
    }

    private fun validate(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {

            alert("Complete the following!", "Error") {
                okButton { return@okButton }
            }.show()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            alert("Email not valid", "Error") {
                okButton { return@okButton }
            }.show()
        } else if (email.equals("gagz_2017@mail.com") && password.equals("kt123")) {

            startActivity<MainActivity>()
            val store = SessionStore()
            store.saveEmail(email, this)
            Toast.makeText(applicationContext, "Login Successful!", Toast.LENGTH_LONG).show()
            finish()
        } else {

            alert("Incorrect password or email!", "Error") {
                okButton { return@okButton }
            }.show()
        }
    }
}
