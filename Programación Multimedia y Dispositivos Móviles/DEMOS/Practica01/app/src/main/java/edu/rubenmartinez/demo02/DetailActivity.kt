package edu.rubenmartinez.demo02

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import edu.rubenmartinez.demo02.databinding.ActivityDetailBinding
import edu.rubenmartinez.demo02.model.Items

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_ITEM = "EXTRA_ITEM"

        fun navigateToDetail(activity: AppCompatActivity, item: Items) {
            activity.startActivity(Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_ITEM, item)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val item: Items? = intent.getParcelableExtra(EXTRA_ITEM)
        if (item == null) {
            finish()
            return
        }

        binding.mToolbar.title = "${item.title} [${item.id}]"
        binding.tvDescDetail.text = item.title
        Glide.with(this)
            .load(item.cover)
            .fitCenter()
            .transform(RoundedCorners(16))
            .into(binding.ivDetail)

        setSupportActionBar(binding.mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(
                    this@DetailActivity,
                    "Use the \"back\" button on the Toolbar.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}