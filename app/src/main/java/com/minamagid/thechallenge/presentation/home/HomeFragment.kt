package com.minamagid.thechallenge.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.minamagid.thechallenge.R
import com.minamagid.thechallenge.common.Constants.MOST_EMAILED
import com.minamagid.thechallenge.common.Constants.MOST_SHARED
import com.minamagid.thechallenge.common.Constants.MOST_VIEWED
import com.minamagid.thechallenge.common.Constants.TYPE_DATA
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    val TAG="mina_home"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        myListener(root)
        return root
    }


    private fun myListener(viewDataBinding: View) {
        viewDataBinding.goToSearchBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_navigation_search)
        }

        viewDataBinding.mostViewBtn.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_navigation_home_to_navigation_articles,
                Bundle().apply { putInt(TYPE_DATA, MOST_VIEWED) }
            )

        }
        viewDataBinding.mostSharedBtn.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_navigation_home_to_navigation_articles,
                Bundle().apply { putInt(TYPE_DATA, MOST_SHARED) })

        }
        viewDataBinding.mostEmailsBtn.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_navigation_home_to_navigation_articles,
                Bundle().apply { putInt(TYPE_DATA, MOST_EMAILED) })
        }
    }


}
