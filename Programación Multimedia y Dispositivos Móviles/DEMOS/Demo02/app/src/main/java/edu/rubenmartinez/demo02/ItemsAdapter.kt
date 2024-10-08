package edu.rubenmartinez.demo02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.rubenmartinez.demo02.databinding.ItemsBinding
import edu.rubenmartinez.demo02.model.Items

class ItemsAdapter(
    val itemsList: MutableList<Items>,
    val itemClick: (Items) -> Unit,
    val imageClick: (Items) -> Unit
) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ItemsViewHolder {
        return ItemsViewHolder(
            ItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    inner class ItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemsBinding.bind(view)
        fun bind(item: Items) {
            binding.tvId.text = item.id.toString()
            binding.tvTitle.text = item.title
            Glide.with(itemView)
                .load(item.image)
                .centerCrop()
                .into(binding.imageView)

            binding.imageView.setOnClickListener { imageClick(item) }
            itemView.setOnClickListener { itemClick(item) }
        }
    }
}