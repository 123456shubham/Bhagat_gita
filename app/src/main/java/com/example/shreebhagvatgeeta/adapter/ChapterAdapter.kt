package com.example.shreebhagvatgeeta.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shreebhagvatgeeta.R
import com.example.shreebhagvatgeeta.model.ChapterResponseItem

class ChapterAdapter(val onChapterClicked: (ChapterResponseItem) -> Unit,val context: Context) : RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {
    val diffUtil=object : DiffUtil.ItemCallback<ChapterResponseItem>(){
        override fun areItemsTheSame(
            oldItem: ChapterResponseItem,
            newItem: ChapterResponseItem
        ): Boolean {
            return  oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ChapterResponseItem,
            newItem: ChapterResponseItem
        ): Boolean {
          return  oldItem==newItem;
        }

    }

    val differ= AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterAdapter.ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_view_chapter,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChapterAdapter.ViewHolder, position: Int) {
        val chapter=differ.currentList[position]

        print("Chapter Shubham ${differ}")

        holder.tvChapterName.setText("Chapter ${chapter.chapter_number}")
        holder.tvChapterTitle.text="${chapter.name_translated}"
        holder.tvChapterDes.text="${chapter.chapter_summary}"
        holder.tvChapterVerseCount.text="${chapter.verses_count}"

        holder.imgLike.setOnClickListener {
        }
        holder.rl.setOnClickListener {
            onChapterClicked(chapter)

        }
    }




    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(val view: View) :RecyclerView.ViewHolder(view){
        val tvChapterName:TextView=view.findViewById(R.id.item_view_chapter_count)
        val tvChapterTitle:TextView=view.findViewById(R.id.item_view_chapter_title)
        val tvChapterDes:TextView=view.findViewById(R.id.item_view_chapter_desc)
        val tvChapterVerseCount:TextView=view.findViewById(R.id.item_view_chapter_count_verses)
        val imgLike:ImageView=view.findViewById(R.id.item_view_like_slog)
        val rl:RelativeLayout=view.findViewById(R.id.rl)
    }
}