package com.minamagid.thechallenge.presentation.my_articles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.minamagid.thechallenge.databinding.ItemPostArticleBinding
import com.minamagid.thechallenge.domain.model.homeResponses.Result

class MyArticlesAdapter (private val onSaveClickListener: OnSaveClickListener?,
): RecyclerView.Adapter<MyArticlesAdapter.NewsVH>() {

    private val differCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.source == newItem.source
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val binding =
            ItemPostArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsVH(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {

        val item = differ.currentList[position]
        holder.binding.apply {

            itemArticleTitle.text = item.title
            itemPostDescription.text = item.abstract
            itemPostAuthor.text = item.section.toString().ifBlank { "Unknown" }
            itemArticleImage.load(item.media.firstOrNull()?.mediaMetadata?.firstOrNull()?.url) {
                crossfade(true)
                crossfade(200)
                transformations(
                    RoundedCornersTransformation(
                        12f,
                        12f,
                        12f,
                        12f
                    )
                )
            }

            saveBtn.setOnClickListener {
                onSaveClickListener?.onItemClick(it,item,position)
            }

            // on item click
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(item.source) }
            }
        }
    }

    inner class NewsVH(val binding: ItemPostArticleBinding) : RecyclerView.ViewHolder(binding.root)

    // on item click listener
    private var onItemClickListener: ((String) -> Unit)? = null
    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
    interface OnSaveClickListener {
        fun onItemClick(
            v: View,
            model: Result,
            position: Int
        )
    }
}

