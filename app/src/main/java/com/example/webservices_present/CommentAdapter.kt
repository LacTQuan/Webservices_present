package com.example.webservices_present

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter(private val context: Context, private val comments: ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val id: TextView = itemView.findViewById(R.id.commentId)
        val email: TextView = itemView.findViewById(R.id.email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, postition: Int) {
        val comment = comments[postition]
        holder.name.text = comment.name
        holder.id.text = comment.id.toString()
        holder.email.text = comment.email
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newComments: ArrayList<Comment>) {
        comments.clear()
        comments.addAll(newComments)
        notifyDataSetChanged()
    }

}