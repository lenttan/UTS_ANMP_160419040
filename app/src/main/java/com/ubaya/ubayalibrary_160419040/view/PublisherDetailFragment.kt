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
import com.ubaya.ubayalibrary_160419040.viewmodel.PublisherDetailModel
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.android.synthetic.main.fragment_publisher_detail.*

class PublisherDetailFragment : Fragment() {
    private lateinit var viewModel: PublisherDetailModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publisher_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(PublisherDetailModel::class.java)
        viewModel.fetch(PublisherDetailFragmentArgs.fromBundle(requireArguments()).idpublisher)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.publisherLiveData.observe(viewLifecycleOwner){
            val book = viewModel.publisherLiveData.value
            book?.let{
                txtDetailPublisherName.text = it.name
                txtDetailAddress.text=it.address
                txtDetailWebsite.text = it.website
            }
        }
    }

}