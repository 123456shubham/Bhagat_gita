package com.example.shreebhagvatgeeta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shreebhagvatgeeta.R

class VersesAdapter(val versesItem: (String, Int) -> Unit) : RecyclerView.Adapter<VersesAdapter.ViewHolder>() {

    val diffUtil=object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return  oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return  oldItem==newItem;
        }

    }

    val differ= AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_verse_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: VersesAdapter.ViewHolder, position: Int) {
        val verse:String=differ.currentList[position]
        holder.tvChapterTitle.text= verse
        holder.tvChapterCount.text="Verses ${position+1}"
        holder.ll.setOnClickListener {
            versesItem(verse,position)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(view :View) :RecyclerView.ViewHolder(view) {
        val tvChapterCount=view.findViewById<TextView>(R.id.chapter_count)
        val tvChapterTitle=view.findViewById<TextView>(R.id.item_view_chapter_title)
        val tvChapterDes=view.findViewById<TextView>(R.id.item_view_chapter_title)
        val tvChapterrVerseCount=view.findViewById<TextView>(R.id.item_view_chapter_count_verses)
        val ll=view.findViewById<RelativeLayout>(R.id.rl)
    }


}