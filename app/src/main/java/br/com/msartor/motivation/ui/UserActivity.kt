package br.com.msartor.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.msartor.motivation.databinding.ActivityUserBinding
import br.com.msartor.motivation.infra.MotivationConstants
import br.com.msartor.motivation.infra.SecurityPreferences
import br.com.msartor.motivation.R

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
         */

        binding.buttonSave.setOnClickListener(this)


    }


    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name != "") {
            SecurityPreferences(applicationContext).storeString(MotivationConstants.KEY.USER_NAME,name)
            lauchMainActivity()
        } else {
            Toast.makeText(applicationContext,R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun lauchMainActivity() {
        finish()
    }
}