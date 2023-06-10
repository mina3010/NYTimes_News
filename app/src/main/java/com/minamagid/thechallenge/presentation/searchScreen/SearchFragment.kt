package com.minamagid.thechallenge.presentation.searchScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.minamagid.thechallenge.R
import com.minamagid.thechallenge.presentation.articlesScreen.adapter.ArticlesAdapter
import com.minamagid.thechallenge.presentation.searchScreen.adapter.SearchResultAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_articles.view.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.fragment_search.view.titlePage

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchResultAdapter
    lateinit var viewDataBinding: View

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        setupList()
        setupView(root)


        return root
    }

    private fun setupView(binding: View) {

        binding.searchBtn.setOnClickListener {
            viewModel.search(binding.searchEd.text.toString())
        }

        if (viewModel.networkObserver.value == true) {

            adapter = SearchResultAdapter()

            binding.searchRv.adapter = adapter
            binding.searchRv.layoutManager = LinearLayoutManager(requireContext())
            binding.searchRv.setHasFixedSize(true)
            viewModel.searchResults.observe(viewLifecycleOwner) { pagingData ->
                adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            }

            adapter.setOnItemClickListener { article ->
                val bundle = Bundle().apply {
                    putString("article", article)
                }
                binding.findNavController().navigate(
                    R.id.action_navigation_search_to_articleDetailsFragment,
                    bundle
                )
            }

        }else{
            Toast.makeText(requireActivity(), "no internet connection", Toast.LENGTH_SHORT).show()
        }






        binding.titlePage.setOnClickListener {
            it.findNavController().popBackStack()
        }

    }

    private fun setupList() {

    }



}
