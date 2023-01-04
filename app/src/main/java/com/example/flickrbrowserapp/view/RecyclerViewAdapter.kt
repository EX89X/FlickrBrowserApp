package com.example.flickrbrowserapp.view
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrbrowserapp.databinding.ItemRowBinding
import com.example.flickrbrowserapp.model.PhotoItem


class RecyclerViewAdapter(val photoClickInterface: PhotoClickInterface):
    ListAdapter<PhotoItem, RecyclerViewAdapter.ItemViewHolder>(PhotoDiffUtill()){
    class ItemViewHolder(var binding:ItemRowBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo= getItem(position)
        val serverID =photo.server
        val secret = photo.secret
        val id = photo.id


        holder.binding.apply {
            val photLink="https://live.staticflickr.com/${serverID}/${id}_${secret}.jpg"
            Glide.with(this.root.context).load(photLink).into(ImageView1)
            ImageView1.setOnClickListener{
               //  activity.hideRv(photLink)
                photoClickInterface.onPhotoClick(photo)
            }
        }
    }
    interface PhotoClickInterface{
         fun onPhotoClick(photoItem: PhotoItem)

    }
}
