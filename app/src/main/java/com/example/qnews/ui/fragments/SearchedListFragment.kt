package com.example.qnews.ui.fragments

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qnews.R
import com.example.qnews.core.NewsApi
import com.example.qnews.core.db.NewsDatabase
import com.example.qnews.core.models.key.Key
import com.example.qnews.core.repo.MainRepository
import com.example.qnews.databinding.FragmentSearchedListBinding
import com.example.qnews.ui.base.NewsDelegates
import com.example.qnews.ui.base.NewsListScreenAdapter
import com.example.qnews.ui.base.viewBinding
import com.example.qnews.ui.recycler.listeners.OnRecyclerClickListener
import com.example.qnews.ui.viewModel.factories.TopicViewModelFactory
import com.example.qnews.ui.viewModel.other.MainViewModel
import com.example.qnews.ui.viewModel.other.TopicViewModel


class SearchedListFragment : Fragment(R.layout.fragment_searched_list) {

    private val binding by viewBinding { FragmentSearchedListBinding.bind(it) }

    private val adapterNews = NewsListScreenAdapter()

    private val viewModel by lazy {
        val dao = NewsDatabase.getInstance(requireContext().applicationContext).newsDao
        val repo = MainRepository(NewsApi.NewsApiService, dao)
        val factory = TopicViewModelFactory(repo)
        ViewModelProvider(this, factory).get(TopicViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: Bundle? = this.arguments

        binding.recyclerViewNews.adapter = adapterNews
        bundle?.let {
            val topic = it.getString(Key.TOPIC)

            binding.toolbar3.textViewSearchedTitle.text = topic

            if (topic != null) {
                if (viewModel.listOFNews.value == null) {
                    viewModel.getNewsByTopic(topic)
                }
            } else {
                Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
        }

        binding.toolbar3.imageVIewGetBack.setOnClickListener {
            it.findNavController().navigateUp()
        }

        viewModel.listOFNews.observe(viewLifecycleOwner) {
            adapterNews.items = it
        }

        NewsDelegates.setOnRecyclerClickListener(object : OnRecyclerClickListener {
            override fun onClick(position: Int) {
                val news = viewModel.listOFNews.value!![position]
                val bundle1 = Bundle()
                bundle1.putParcelable("news", news)
                findNavController().navigate(R.id.action_searchedListFragment_to_detailFragment, bundle1)
            }
        })

    }


}