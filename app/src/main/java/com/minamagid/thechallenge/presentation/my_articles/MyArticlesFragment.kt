package com.minamagid.thechallenge.presentation.my_articles

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
import com.minamagid.thechallenge.presentation.my_articles.adapter.MyArticlesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_articles.view.*
import kotlinx.android.synthetic.main.fragment_search.view.titlePage

@AndroidEntryPoint
class MyArticlesFragment : Fragment() {
    private val viewModel: MyArticlesViewModel by viewModels()

    val TAG="mina_articles"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_articles, container, false)
        viewModel.getLocalArticlesData()
        setAdapter(root)

        root.titlePage.setOnClickListener {
            it.findNavController().popBackStack()
        }

        return root
    }

    private fun setAdapter(root: View?) {
        viewModel.listDataRemote.observe(viewLifecycleOwner){
            Log.d("mina_local","offline ${it?.size}")

            val adapter = MyArticlesAdapter(object : MyArticlesAdapter.OnSaveClickListener {
                override fun onItemClick(v: View, model: Result, position: Int) {
                    viewModel.deleteArticleLocalDB(model)
                    Toast.makeText(requireActivity(), "deleted successfully", Toast.LENGTH_SHORT).show()
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
//                viewModel.clearLocalDB()
//                it?.forEach {
//                    viewModel.insertArticleLocal(it)
//                }
        }

    }




}
