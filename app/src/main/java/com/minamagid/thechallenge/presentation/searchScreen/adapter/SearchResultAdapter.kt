package  com.minamagid.thechallenge.presentation.searchScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.minamagid.thechallenge.R
import com.minamagid.thechallenge.databinding.DropDownItemBinding
import com.minamagid.thechallenge.domain.model.search.Doc


class SearchResultAdapter : PagingDataAdapter<Doc, RecyclerView.ViewHolder>(DiffCallback()) {

    // ViewTypes for different item types
    private val ITEM_VIEW_TYPE = 0
    private val LOADING_VIEW_TYPE = 1
    private val ERROR_VIEW_TYPE = 2
    private var loadState: LoadState = LoadState.Loading // Initialize with initial loading state

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_VIEW_TYPE -> {
                val binding: DropDownItemBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.drop_down_item,
                    parent,
                    false
                )
                SearchResultViewHolder(binding)
            }
            LOADING_VIEW_TYPE -> {
                val loadingView = inflater.inflate(R.layout.item_loading, parent, false)
                LoadingViewHolder(loadingView)
            }
            else -> {
                val errorView = inflater.inflate(R.layout.item_error, parent, false)
                ErrorViewHolder(errorView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_VIEW_TYPE -> {
                val searchResult = getItem(position) as Doc
                (holder as SearchResultViewHolder).bind(searchResult)
            }
            LOADING_VIEW_TYPE -> {
                setLoadState(loadState)
            }
            ERROR_VIEW_TYPE -> {
                // Handle error state if needed
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position < itemCount -> ITEM_VIEW_TYPE
            loadState is LoadState.Loading -> LOADING_VIEW_TYPE
            loadState is LoadState.Error -> ERROR_VIEW_TYPE
            else -> throw IllegalArgumentException("Invalid view type at position $position")
        }
    }

    fun setLoadState(newLoadState: LoadState) {
        val previousLoadState = loadState
        loadState = newLoadState
        if (previousLoadState is LoadState.Error && newLoadState is LoadState.Loading) {
        }
        if (previousLoadState != newLoadState) {
            notifyItemChanged(itemCount)
        }
    }
    inner class SearchResultViewHolder(private val binding: DropDownItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(searchResult: Doc) {
            binding.txtData.text = searchResult.headline?.main
            binding.itemPostDescription.text = searchResult.abstract
            binding.itemPostAuthor.text = searchResult.sectionName

            // on item click
            binding.txtData.setOnClickListener {
                onItemClickListener?.let { it(searchResult.webUrl?:"") }
            }
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }


    // on item click listener
    private var onItemClickListener: ((String) -> Unit)? = null
    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
    inner class ErrorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class DiffCallback : DiffUtil.ItemCallback<Doc>() {
        override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean {
            return oldItem == newItem
        }
    }
}
