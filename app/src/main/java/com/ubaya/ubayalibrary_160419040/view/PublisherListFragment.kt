package com.ubaya.ubayalibrary_160419040.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayalibrary_160419040.R
import com.ubaya.ubayalibrary_160419040.viewmodel.PublisherViewModel
import kotlinx.android.synthetic.main.fragment_book_list.refreshLayout
import kotlinx.android.synthetic.main.fragment_publisher_list.*

class PublisherListFragment : Fragment() {
    private lateinit var viewModel: PublisherViewModel
    private val bookListAdapter = PublisherListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publisher_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(PublisherViewModel::class.java)
        viewModel.refresh()

        recViewPublisher.layoutManager = LinearLayoutManager(context)
        recViewPublisher.adapter = bookListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewPublisher.visibility = View.GONE
            textViewErrorPublisher.visibility = View.GONE
            progressBarPublisher.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing=false
        }
    }

    private fun observeViewModel() {
        viewModel.publisherLiveData.observe(viewLifecycleOwner){
            bookListAdapter.updateBookList(it)
        }
        viewModel.publisherLoadError.observe(viewLifecycleOwner){
            textViewErrorPublisher.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){
                recViewPublisher.visibility = View.GONE
                progressBarPublisher.visibility = View.VISIBLE
            } else {
                recViewPublisher.visibility = View.VISIBLE
                progressBarPublisher.visibility = View.GONE
            }
        }
    }
}