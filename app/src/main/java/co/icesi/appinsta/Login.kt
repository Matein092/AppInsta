package co.icesi.appinsta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.icesi.appinsta.databinding.ActivityLoginBinding
import co.icesi.appinsta.databinding.ActivityMainBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var firstUser = User("Xmateo092X", "mateor@gmail.com", "cali092")
        var secondUser = User("hermeregildo", "donherme@gmail.com", "herme123")

        binding.logBtm.setOnClickListener{
            var email = binding.editName.text.toString()
            var password = binding.editPassword.text.toString()

            if(email.equals(firstUser.email) && password.equals(firstUser.password) || email.equals(secondUser.email) && password.equals(secondUser.password)){
                val i= Intent(applicationContext, Navigation :: class.java).apply{}
                startActivity(i)
            }else{
                Toast.makeText(this, "An error occurred, please try to log in again", Toast.LENGTH_SHORT).show()
            }
            }
        }
    }

