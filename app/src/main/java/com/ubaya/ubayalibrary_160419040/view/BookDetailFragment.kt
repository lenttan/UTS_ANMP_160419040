package com.ubaya.ubayalibrary_160419040.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ubayalibrary_160419040.R
import com.ubaya.ubayalibrary_160419040.util.loadImage
import com.ubaya.ubayalibrary_160419040.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.book_list_item.*
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.android.synthetic.main.fragment_book_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [BookDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookDetailFragment : Fragment() {
    private lateinit var viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(BookDetailFragmentArgs.fromBundle(requireArguments()).bookid)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.bookLiveData.observe(viewLifecycleOwner){
            val book = viewModel.bookLiveData.value
            book?.let{
                txtDetailTitle.text = it.title
                txtDetailDesc.text = it.description
                txtDetailPublisher.text = it.publisher
                txtAuthor.text = it.author
                txtRelease.text = it.releaseYear
                imageViewBookDetail.loadImage(it.photoUrl,progressBarDetailBook)
            }
        }
    }
}