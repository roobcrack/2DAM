package com.ruben.demo01

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.ruben.demo01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()

        binding.button.setOnClickListener{
            binding.tvResult.text = null

            val tName = binding.tietName.text.toString()
            val tSurname = binding.tietSurName.text.toString()
            val tDate = binding.tietBirthDate.text.toString()

            if (tName.isBlank())
                binding.tietName.error = getString(R.string.txt_warning)
            else if(tSurname.isBlank())
                binding.tietSurName.error = getString(R.string.txt_warning)
            else if(tDate.isBlank())
                binding.tietBirthDate.error = getString(R.string.txt_warning)
            else {
                binding.tietName.error = null

                val moduleCheck = with(binding) {
                    cbADA.isChecked || cbDI.isChecked || cbPMDM.isChecked || cbPSP.isChecked || cbSGE.isChecked
                }

                if (moduleCheck) {
                    binding.tvResult.text = getString(
                        R.string.txt_result1,
                        tName,
                        binding.tietSurName.text.toString(),
                        binding.tietBirthDate.text.toString()
                    )

                    val license = if (binding.rbNo.isChecked) getString(R.string.no) else getString(R.string.yes)
                    val turn = if (binding.swTurn.isChecked) getString(R.string.afternoon_shift) else getString(R.string.txt_morning)

                    binding.tvResult.append(
                        getString(R.string.txt_result2, license, turn)
                    )

                    val modules = mutableListOf<String>()
                    val view = binding.root.getChildAt(1)
                    if (view is ViewGroup) {
                        val linear = view.getChildAt(0) as LinearLayout
                        for (j in 0 until linear.childCount) {
                            val viewChild = linear.getChildAt(j)
                            if (viewChild is CheckBox) {
                                Log.d(
                                    "MainActivity",
                                    "CheckBox: ${viewChild.text} is ${viewChild.isChecked}"
                                )
                                if (viewChild.isChecked)
                                    modules.add(viewChild.text.toString())
                            }
                        }
                    }
                    binding.tvResult.append(
                        getString(
                            R.string.txt_result3,
                            modules.joinToString("\n")
                        )
                    )

                    //Sets the button enabled if the form is completed
                    binding.btRestart.isEnabled = true
                    //And disable the validate button
                    binding.button.isEnabled = false

                } else {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.txt_warning_modules),
                        Snackbar.LENGTH_SHORT
                    ).show()

                }
            }
        }

        //This methid is called when restart button is pressed after being enabled
        binding.btRestart.setOnClickListener{
            //Call the function that sets it to default
            restartData()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            if (!binding.tvResult.text.isNullOrEmpty())
                putString("Result", binding.tvResult.text.toString())
            //We check the button state if it is enabled or not and save it
            putBoolean("Validate", binding.button.isEnabled)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run {
            if (containsKey("Result"))
                binding.tvResult.text = getString("Result")
            //Search for the validate key
            if(containsKey("Validate")) {
                //And set the saved values. If one is enabled the other would be disabled
                binding.button.isEnabled = getBoolean("Validate")
                binding.btRestart.isEnabled = !getBoolean("Validate")
            }
        }
    }

    //In order to set to default all the form
    private fun restartData(){
        //First set the 3 textboxes to null to restart the form
        binding.tietName.text = null
        binding.tietSurName.text = null
        binding.tietBirthDate.text = null

        //Check again the "no" option
        binding.rbNo.isChecked = true

        //Set the switch to desactivated
        binding.swTurn.isChecked = false

        //Uncheck all the checkboxes
        val view = binding.root.getChildAt(1)
        if (view is ViewGroup) {
            val linear = view.getChildAt(0) as LinearLayout
            for (j in 0 until linear.childCount) {
                val viewChild = linear.getChildAt(j)
                if (viewChild is CheckBox) {
                    viewChild.isChecked = false
                }
            }
        }

        //Set the result text to null
        binding.tvResult.text = null

        //Finally invalidate the restart button
        binding.btRestart.isEnabled = false
        //Ad set the validate button again enable
        binding.button.isEnabled = true
    }
}