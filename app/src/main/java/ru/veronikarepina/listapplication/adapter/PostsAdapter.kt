package ru.veronikarepina.listapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.veronikarepina.domain.model.Post
import ru.veronikarepina.listapplication.R
import ru.veronikarepina.listapplication.databinding.PostItemBinding


class PostsAdapter: PagingDataAdapter<Post, PostsAdapter.PostsHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsHolder {
        val post = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostsHolder(post)
    }

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }}

    inner class PostsHolder(view: View): ViewHolder(view) {
        private val binding = PostItemBinding.bind(view)

        fun bind(post: Post) = with(binding){
            titleText.text = post.title
            mainText.text = post.body
        }}
}