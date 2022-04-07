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
import com.ubaya.ubayalibrary_160419040.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [BookListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookListFragment : Fragment(){
    private lateinit var viewModel: BookViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        viewModel.refresh()

        RecView.layoutManager = LinearLayoutManager(context)
        RecView.adapter = bookListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            RecView.visibility = View.GONE
            textViewError.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing=false
        }
    }

    private fun observeViewModel() {
        viewModel.bookLiveData.observe(viewLifecycleOwner){
            bookListAdapter.updateBookList(it)
        }
        viewModel.bookLoadError.observe(viewLifecycleOwner){
            textViewError.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){
                RecView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            } else {
                RecView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }
}