package com.example.contactlist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.contactlist.database.SignUpDatabase
import com.example.contactlist.database.SignUpDatabaseDao
import com.example.contactlist.database.SignUpParameter
import com.example.contactlist.databinding.ActivitySignUpBinding
import com.example.contactlist.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
   // private lateinit var dao: SignUpDatabaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)



         val dao= SignUpDatabase.getInstance(this).signUpDatabaseDao

        binding.btnSignup.setOnClickListener {

            val fullName = binding.etFullnameText.text.toString()
            val email = binding.etEmailText.text.toString()
            val password = binding.etPasswordText.text.toString()

            if(fullName.isNotEmpty() &&email.isNotEmpty() && password.isNotEmpty()){
                lifecycleScope.launch {
            dao.insert(SignUpParameter(fullName,email,password))
               }
                Toast.makeText(this, "SignUp successful", Toast.LENGTH_SHORT)
                .show()
                CoroutineScope(Dispatchers.Main).launch {
                    setUpIntent()
                }
            }
            else{
                Toast.makeText(this, "Please fill in all the required information", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
        }

        binding.tvSignin.setOnClickListener {
            Intent(this,LoginActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    suspend  fun setUpIntent(){
        delay(2000L)
        Intent(this,LoginActivity::class.java).also {
            startActivity(it)
        }

    }
}