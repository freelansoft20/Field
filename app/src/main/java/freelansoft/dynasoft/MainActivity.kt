package freelansoft.dynasoft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import freelansoft.dynasoft.R
import freelansoft.dynasoft.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow()
//
//        }

        signup.setOnClickListener {
            createEmailId()
        }

        signin.setOnClickListener {
            login()
        }
    }

    private fun login() {

        var email = email.text.toString()
        var password = password.text.toString()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                moveToReport()
            }
        }
    }

    private fun moveToReport() {
        var currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainFragment::class.java))
        }
    }

    private fun createEmailId() {

        var email = email.text.toString()
        var password = password.text.toString()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("Sign up successful")
            }
        }
    }
}