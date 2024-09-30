package com.ruben.demo01

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

            if (tName.isBlank()) {
                binding.tietName.error = getString(R.string.txt_warning)
            } else {
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
                    if (binding.cbADA.isChecked) modules.add(getString(R.string.ada))
                    if (binding.cbDI.isChecked) modules.add(getString(R.string.di))
                    if (binding.cbPMDM.isChecked) modules.add(getString(R.string.pmdm))
                    if (binding.cbPSP.isChecked) modules.add(getString(R.string.psp))
                    if (binding.cbSGE.isChecked) modules.add(getString(R.string.sge))

                    binding.tvResult.append(
                        getString(R.string.txt_result3, modules.joinToString("\n"))
                    )

                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.txt_warning_modules),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            if (!binding.tvResult.text.isNullOrEmpty())
                putString("Result", binding.tvResult.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run {
            if (containsKey("Result"))
                binding.tvResult.setText(getString("Result"))
        }
    }
}