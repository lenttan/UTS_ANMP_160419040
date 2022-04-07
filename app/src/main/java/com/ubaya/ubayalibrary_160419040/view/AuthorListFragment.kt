package com.ubaya.ubayalibrary_160419040.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayalibrary_160419040.R
import com.ubaya.ubayalibrary_160419040.viewmodel.AuthorViewModel
import kotlinx.android.synthetic.main.fragment_author_list.*
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_book_list.refreshLayout


class AuthorListFragment : Fragment() {
    private lateinit var viewModel: AuthorViewModel
    private val authorListAdapter = AuthorListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(AuthorViewModel::class.java)
        viewModel.refresh()

        recViewAuthor.layoutManager = LinearLayoutManager(context)
        recViewAuthor.adapter = authorListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewAuthor.visibility = View.GONE
            textViewErrorAuthor.visibility = View.GONE
            progressBarAuthor.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing=false
        }
    }

    private fun observeViewModel() {
        viewModel.authorLiveData.observe(viewLifecycleOwner){
            authorListAdapter.updateAuthorList(it)
        }
        viewModel.authorLoadError.observe(viewLifecycleOwner){
            textViewErrorAuthor.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){
                recViewAuthor.visibility = View.GONE
                progressBarAuthor.visibility = View.VISIBLE
            } else {
                recViewAuthor.visibility = View.VISIBLE
                progressBarAuthor.visibility = View.GONE
            }
        }
    }
}