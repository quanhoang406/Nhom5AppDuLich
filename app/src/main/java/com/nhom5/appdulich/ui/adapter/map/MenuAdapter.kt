package com.nhom5.appdulich.ui.adapter.map

import com.nhom5.appdulich.R
import com.nhom5.appdulich.base.BaseRecyclerViewAdapter
import com.nhom5.appdulich.data.model.Menu
import com.nhom5.appdulich.databinding.ItemMenuMapBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuAdapter @Inject constructor() : BaseRecyclerViewAdapter<Menu, ItemMenuMapBinding>() {
    override fun getLayout() = R.layout.item_menu_map

    override fun onBindViewHolder(holder: BaseViewHolder<ItemMenuMapBinding>, position: Int) {
        holder.binding.menu = items[position]
        holder.binding.root.setOnClickListener {
            listener?.invoke(
                holder.itemView,
                items[position],
                position
            )
        }
    }
}