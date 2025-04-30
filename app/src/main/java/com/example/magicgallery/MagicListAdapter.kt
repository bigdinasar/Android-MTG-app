package com.example.magicgallery

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.magicgallery.api.GalleryItem
import com.example.magicgallery.databinding.ListItemGalleryBinding
import com.example.magicgallery.databinding.ListItemMultiBinding

const private val TAG = "MagicListAdapter"

class MagicViewHolder(
    private val binding: ListItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: GalleryItem) {
        Log.d(TAG, "gallery url before: $galleryItem.url")
        val newUrl = galleryItem.url.replace("http", "https")
        Log.d(TAG, "gallery url after: $newUrl")
        binding.itemImageView.load(newUrl) {
            placeholder(R.drawable.alien)
        }

        binding.itemNameView.text = galleryItem.name
        binding.itemCmcView.text = "CMC: " + galleryItem.cmc
        binding.itemTypeView.text = galleryItem.type
        binding.itemRarityView.text = "Rarity: " + galleryItem.rarity

        binding.itemImageView.setOnClickListener {
            binding.itemImageView.animate().apply {
                duration = 1000
                rotationYBy( 360f)
            }.start()
        }
    }
}

class MagicListViewHolder(
    private val binding: ListItemMultiBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: GalleryItem) {
        binding.itemListImageView.load(galleryItem.url) {
            placeholder(R.drawable.alien)
        }
    }
}

class MagicListAdapter(
    private val galleryItems: List<GalleryItem>
) : RecyclerView.Adapter<MagicViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MagicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return MagicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MagicViewHolder, position: Int) {
        val item = galleryItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = galleryItems.size
}