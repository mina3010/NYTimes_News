package com.minamagid.thechallenge.presentation.articlesScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.minamagid.thechallenge.R
import com.minamagid.thechallenge.common.Constants.TYPE_DATA
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.presentation.articlesScreen.adapter.ArticlesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_articles.view.*
import kotlinx.android.synthetic.main.fragment_search.view.titlePage

@AndroidEntryPoint
class ArticlesFragment : Fragment() {
    private val viewModel: ArticlesViewModel by viewModels()

    val TAG="mina_articles"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_articles, container, false)
        getArgs(root)
        setAdapter(root)

        root.titlePage.setOnClickListener {
            it.findNavController().popBackStack()
        }

        return root
    }

    private fun setAdapter(root: View?) {
        if (viewModel.networkObserver.value == true) {
            viewModel.listDataRemote.observe(viewLifecycleOwner){
                val adapter = ArticlesAdapter(object : ArticlesAdapter.OnSaveClickListener {
                    override fun onItemClick(v: View, model: Result, position: Int) {
                        viewModel.insertArticleLocal(model)
                        Toast.makeText(requireActivity(), "insert successfully", Toast.LENGTH_SHORT).show()
                    }

                })

                adapter.differ.submitList(it)
                root?.article_rv?.adapter = adapter

                adapter.setOnItemClickListener { article ->
                    val bundle = Bundle().apply {
                        putString("article", article)
                    }
                    root?.findNavController()?.navigate(
                        R.id.action_navigation_articles_to_articleDetailsFragment,
                        bundle
                    )
                }
            }

            viewModel.isLoad.observe(viewLifecycleOwner){
               if (!it){
                   root?.progressBar?.isVisible = false
               }
            }

        }else{
            root?.progressBar?.isVisible = false
            Toast.makeText(requireActivity(), "no internet connection", Toast.LENGTH_SHORT).show()
//            viewModel.getLocalArticlesData()
            viewModel.listDataRemote.observe(viewLifecycleOwner){
                val adapter = ArticlesAdapter(null)
                adapter.differ.submitList(it)
                root?.article_rv?.adapter = adapter
                Log.d(TAG,"offline ${it?.size}")

                adapter.setOnItemClickListener { article ->
                    val bundle = Bundle().apply {
                        putSerializable("article", article)
                    }
                    root?.findNavController()?.navigate(
                        R.id.action_navigation_articles_to_articleDetailsFragment,
                        bundle
                    )
                }
            }
        }


    }


    private fun getArgs(viewDataBinding: View) {
        viewModel.run {
            viewDataBinding.run {
                type.value = arguments?.getInt(TYPE_DATA) ?: 0
                Log.d(TAG,"${type.value}")
            }
            getData()
        }
    }

}
