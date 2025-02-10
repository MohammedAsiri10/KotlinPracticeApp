package com.example.springtrainingproject2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections


class ImageListAdapter(private val items: MutableList<Int>) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>(), ItemMoveCallback.ItemTouchHelperContract {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_items_draggable_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(items, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRowSelected(viewHolder: RecyclerView.ViewHolder?) {}

    override fun onRowClear(viewHolder: RecyclerView.ViewHolder?) {}
}