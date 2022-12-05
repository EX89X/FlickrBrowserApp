package com.example.flickrbrowserapp
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrbrowserapp.databinding.ItemRowBinding



class RecyclerViewAdapter(var activity: MainActivity ,var photoList:ArrayList<PhotoItem>):
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){
    class ItemViewHolder(var binding:ItemRowBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = photoList[position]
        val serverID = photo.server
        val secret = photo.secret
        val id = photo.id


        holder.binding.apply {

            val photLink="https://live.staticflicker.com/${serverID}/${id}_${secret}.jpg"
            Glide.with(activity).load(photLink).into(ImageView1)

            ImageView1.setOnClickListener{
                 activity.hideRv(photLink)
            }
        }
    }

    override fun getItemCount() =photoList.size

  fun updatePhotosList(newList:ArrayList<PhotoItem>){
    photoList=newList
    notifyDataSetChanged()

    }
}
