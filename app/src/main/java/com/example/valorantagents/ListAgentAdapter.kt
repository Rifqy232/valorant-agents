package com.example.valorantagents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valorantagents.databinding.ItemRowAgentBinding

class ListAgentAdapter(private val listAgent: ArrayList<Agent>) : RecyclerView.Adapter<ListAgentAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowAgentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowAgentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listAgent.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listAgent[position] // set nama, deskripsi, dan photo darj setiap isi listAgent
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgAgentPhoto)
        holder.binding.tvAgentName.text = name
        holder.binding.tvAgentDescription.text = description

        // Menambahkan aksi ketika item di-klik
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listAgent[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Agent)
    }
}