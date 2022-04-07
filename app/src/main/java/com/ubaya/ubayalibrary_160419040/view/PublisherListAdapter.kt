package com.ubaya.ubayalibrary_160419040.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayalibrary_160419040.R
import com.ubaya.ubayalibrary_160419040.model.Book
import com.ubaya.ubayalibrary_160419040.model.Publisher
import com.ubaya.ubayalibrary_160419040.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*
import kotlinx.android.synthetic.main.publisher_list_item.view.*

class PublisherListAdapter(val publisherList:ArrayList<Publisher>) : RecyclerView.Adapter<PublisherListAdapter.PublisherViewHolder>() {
    class PublisherViewHolder(var view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublisherViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.publisher_list_item, parent, false)
        return PublisherViewHolder(view)
    }

    override fun onBindViewHolder(holder: PublisherViewHolder, position: Int) {
        val publisher = publisherList[position]
        with(holder.view){
            txtListPublisher.text = publisher.name


            buttonDetailPublisher.setOnClickListener {
                val action = PublisherListFragmentDirections.actionPublisherDetail(publisher.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = publisherList.size

    fun updateBookList(newPublisherList: ArrayList<Publisher>){
        publisherList.clear()
        publisherList.addAll(newPublisherList)
        notifyDataSetChanged()
    }
}