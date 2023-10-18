package ru.veronikarepina.listapplication.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.veronikarepina.domain.model.Post


object DiffUtilCallback: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}