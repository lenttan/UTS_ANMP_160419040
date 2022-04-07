package com.ubaya.ubayalibrary_160419040.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.ubayalibrary_160419040.R
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBook.setOnClickListener {
            val action = MainFragmentDirections.actionMainBook()
            Navigation.findNavController(it).navigate(action)
        }
        btnPublisher.setOnClickListener {
            val action = MainFragmentDirections.actionMainPublisher()
            Navigation.findNavController(it).navigate(action)
        }

        buttonAuthor.setOnClickListener {
            val action = MainFragmentDirections.actionItemMenuToAuthorListFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}