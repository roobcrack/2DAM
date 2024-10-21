package edu.rubenmartinez.demo02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import edu.rubenmartinez.demo02.databinding.ItemsBinding
import edu.rubenmartinez.demo02.model.Items

class ItemsAdapter(
    val itemsList: MutableList<Items>,
    val itemClick: (Items) -> Unit,
    val imageClick: (Items) -> Unit
) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
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
            binding.tvTitle.text = item.title
            binding.tvYear.text = item.year.toString()
            binding.tvDuration.text = item.duration.toString() + " min"
            binding.tvGenre.text = item.genre
            binding.tvDirector.text = item.director
            Glide.with(itemView)
                .load(item.cover)
                .centerCrop()
                .into(binding.imageView)

            // Click listener normal para los ítems
            binding.imageView.setOnClickListener { imageClick(item) }
            itemView.setOnClickListener { itemClick(item) }

            // Long click listener para eliminar el ítem
            itemView.setOnLongClickListener { removeItem(adapterPosition, itemView); true }
        }
    }

    private fun removeItem(position: Int, view: View) {

        // Guardamos el ítem y su posición antes de eliminarlo
        val recentlyDeletedItem: Items = itemsList[position]
        val recentlyDeletedItemPosition: Int = position

        // Eliminamos el ítem de la lista
        itemsList.removeAt(position)
        notifyItemRemoved(position)

        // Mostramos el Snackbar con la opción de deshacer
        Snackbar.make(view, "\"${itemsList[position].title}\" deleted", Snackbar.LENGTH_LONG)
            .setAction("UNDO") {
                // Si se pulsa "UNDO", restauramos el ítem eliminado
                recentlyDeletedItem.let {
                    itemsList.add(recentlyDeletedItemPosition, it)
                    notifyItemInserted(recentlyDeletedItemPosition)
                }
            }.show()
    }

}
