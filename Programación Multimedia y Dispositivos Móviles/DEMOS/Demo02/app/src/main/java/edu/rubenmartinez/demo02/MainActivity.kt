package edu.rubenmartinez.demo02

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import edu.rubenmartinez.demo02.databinding.ActivityMainBinding
import edu.rubenmartinez.demo02.model.Items
import edu.rubenmartinez.demo02.model.ItemsAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemsAdapter: ItemsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        itemsAdapter = ItemsAdapter(
            Items.items,
            itemClick = { item ->
                Toast.makeText(
                    this,
                    "Item clicked: ${item.title}",
                    Toast.LENGTH_SHORT
                ).show()
            }, imageClick = { item ->
                Snackbar.make(
                    binding.root,
                    "Image clicked: ${item.title}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        )
        binding.rv.adapter = itemsAdapter
    }
}