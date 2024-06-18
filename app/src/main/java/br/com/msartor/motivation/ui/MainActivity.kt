package br.com.msartor.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.motivation.infra.MotivationConstants
import br.com.msartor.motivation.R
import br.com.msartor.motivation.infra.SecurityPreferences
import br.com.msartor.motivation.databinding.ActivityMainBinding
import br.com.msartor.motivation.repository.Mock
import br.com.msartor.motivation.repository.Phrase

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryId: Int = MotivationConstants.PHRASEFILTER.ALL;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // TODO - Estudar esse trecho de codigo
        /**/
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        handleFilter(R.id.image_all)
        handleNextPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textUserName.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        handleUserName()
    }

    private fun handleUserName() {
        val name = SecurityPreferences(applicationContext).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_new_phrase){
            handleNextPhrase()
        }else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)){
            handleFilter(view.id)
        }else if(view.id == R.id.text_user_name) {
            startActivity(Intent(applicationContext, UserActivity::class.java))
        }
    }

    private fun handleNextPhrase() {
        var phrase = Mock().getPhrase(categoryId)
        binding.textPhrase.text = phrase
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(applicationContext,R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(applicationContext,R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(applicationContext,R.color.dark_purple))
        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(applicationContext,R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(applicationContext,R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(applicationContext,R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.SUNNY
            }
        }
    }

}