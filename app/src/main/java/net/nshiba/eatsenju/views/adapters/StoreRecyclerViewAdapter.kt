package net.nshiba.eatsenju.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.nshiba.eatsenju.R
import net.nshiba.eatsenju.databinding.ItemStoreListBinding
import net.nshiba.eatsenju.networks.Store
import net.nshiba.eatsenju.viewmodels.StoreItemViewModel

class StoreRecyclerViewAdapter(data: List<Store>, val itemClickListener: (Store) -> Unit, val context: Context):
        RecyclerView.Adapter<StoreRecyclerViewAdapter.ViewHolder>() {

    private val VIEWTYPE_HEADER = 0

    private val VIEWTYPE_ITEM = 1

    val dataList = data.toMutableList()

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEWTYPE_HEADER
        } else {
            VIEWTYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return dataList.size + 1
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (position != 0) {
            holder?.setup(dataList[position - 1])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEWTYPE_HEADER -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_list_first, parent, false)
                HeaderViewHolder(view, itemClickListener)
            }
            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_store_list, parent, false)
                ItemViewHolder(view, itemClickListener)
            }
        }
    }

    abstract class ViewHolder(itemView: View, itemClickListener: (Store) -> Unit) : RecyclerView.ViewHolder(itemView) {
        abstract fun setup(store: Store)
    }

    class ItemViewHolder(itemView: View, val itemClickListener: (Store) -> Unit) : ViewHolder(itemView, itemClickListener) {

        val binding: ItemStoreListBinding = ItemStoreListBinding.bind(itemView)

        override fun setup(store: Store) {
            this.itemView.setOnClickListener { itemClickListener(store) }
            binding.store = StoreItemViewModel(store)
        }
    }

    class HeaderViewHolder(itemView: View, val itemClickListener: (Store) -> Unit) : ViewHolder(itemView, itemClickListener) {

        override fun setup(store: Store) {
            this.itemView.setOnClickListener { itemClickListener(store) }
        }
    }
}
