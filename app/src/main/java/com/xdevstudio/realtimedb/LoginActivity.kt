package com.xdevstudio.realtimedb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()

        val emailLogin = findViewById<EditText>(R.id.emailLogin)
        val passwordLogin = findViewById<EditText>(R.id.passwordLogin)
        val loginButton =  findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val  email = emailLogin.text.toString()
            val password  = passwordLogin.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Login failed: ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}