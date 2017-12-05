package com.github.reyurnible.news.component.adapter

import android.support.v7.util.DiffUtil

/**
 * DiffUtils for RecyclerView List
 */
class RecyclerDiffCallback<T>(
        val oldList: List<T>,
        val newList: List<T>,
        // Called by the DiffUtil to decide whether two object represent the same Item.
        val isSameItem: ((oldItem: T?, newItem: T?) -> Boolean)? = null,
        // Called by the DiffUtil when it wants to check whether two items have the same data.
        val isSameContents: ((oldItem: T?, newItem: T?) -> Boolean)? = null
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            isSameItem?.let { it(oldList[oldItemPosition], newList[newItemPosition]) }
                    ?: oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            isSameContents?.let { it(oldList[oldItemPosition], newList[newItemPosition]) }
                    ?: oldList[oldItemPosition]?.equals(newList[newItemPosition])
                    ?: false
}