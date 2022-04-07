package com.ubaya.ubayalibrary_160419040.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayalibrary_160419040.R
import com.ubaya.ubayalibrary_160419040.model.Author
import com.ubaya.ubayalibrary_160419040.util.loadImage
import kotlinx.android.synthetic.main.author_list_item.view.*

class AuthorListAdapter(val authorList: ArrayList<Author>) : RecyclerView.Adapter<AuthorListAdapter.AuthorViewHolder>() {
    class AuthorViewHolder(var view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.author_list_item, parent, false)
        return AuthorListAdapter.AuthorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val author = authorList[position]
        with(holder.view){
            textViewAuthorName1.text = author.name
            imageViewAuthor.loadImage(author.photoUrl,progressBarAuthorPhoto)
        }
    }

    override fun getItemCount() = authorList.size

    fun updateAuthorList(newBookList: ArrayList<Author>){
        authorList.clear()
        authorList.addAll(newBookList)
        notifyDataSetChanged()
    }
}