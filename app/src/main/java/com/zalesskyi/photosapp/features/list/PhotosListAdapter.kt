package com.zalesskyi.photosapp.features.list

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zalesskyi.domain.models.Photo
import com.zalesskyi.photosapp.R
import com.zalesskyi.photosapp.databinding.ItemPhotoBinding
import com.zalesskyi.photosapp.databinding.ItemSkeletonPhotoBinding
import com.zalesskyi.photosapp.extensions.AppSkeletonItem
import com.zalesskyi.photosapp.extensions.image
import com.zalesskyi.photosapp.extensions.toggleSelected

const val SKELETON_VIEW_TYPE = 0
const val ITEM_VIEW_TYPE = 1

class PhotosAdapter(list: List<Parcelable>,
                    private val onItemClick: (Photo, ImageView) -> Unit,
                    private val onFavoriteClick: (Photo) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = list.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                SKELETON_VIEW_TYPE -> SkeletonHolder.newInstance(parent)
                else -> PhotoHolder.newInstance(parent, onItemClick, onFavoriteClick)
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (data[position] as? Photo)?.let {
            (holder as? PhotoHolder)?.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int =
            when (data[position]) {
                is AppSkeletonItem -> SKELETON_VIEW_TYPE
                else -> ITEM_VIEW_TYPE
            }

    fun updateAllNotify(list: List<Parcelable>) {
        this.data.clear()
        this.data.addAll(list)
        notifyDataSetChanged()
    }

    fun add(list: List<Parcelable>) {
        val prevSize = data.size
        data.addAll(list)
        notifyItemRangeChanged(prevSize, list.size)
    }

    class PhotoHolder(private val binding: ItemPhotoBinding,
                      private val onItemClick: (Photo, ImageView) -> Unit,
                      private val onFavoriteClick: (Photo) -> Unit) :
            RecyclerView.ViewHolder(binding.root) {

        companion object {

            fun newInstance(parent: ViewGroup,
                            onItemClick: (Photo, ImageView) -> Unit,
                            onFavoriteClick: (Photo) -> Unit) =
                    PhotoHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context)), onItemClick, onFavoriteClick)
        }

        fun bind(photo: Photo) {
            binding.run {
                ivPreview.transitionName = photo.url
                ivPreview.image(photo.url, R.drawable.ic_image)
                root.setOnClickListener { onItemClick(photo, binding.ivPreview) }
                ivFavorite.isSelected = photo.isFavorite
                ivFavorite.setOnClickListener {
                    onFavoriteClick(photo)
                    ivFavorite.toggleSelected()
                }
            }
        }
    }

    class SkeletonHolder(binding: ItemSkeletonPhotoBinding) :
            RecyclerView.ViewHolder(binding.root) {

        companion object {

            fun newInstance(parent: ViewGroup) =
                    SkeletonHolder(ItemSkeletonPhotoBinding
                        .inflate(LayoutInflater.from(parent.context)))
        }
    }
}