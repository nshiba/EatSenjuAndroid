package net.nshiba.eatsenju.views

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

    val dataList = data.toMutableList()

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.setup(dataList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_store_list, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    class ViewHolder(itemView: View, val itemClickListener: (Store) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val binding: ItemStoreListBinding = ItemStoreListBinding.bind(itemView)

        fun setup(store: Store) {
            this.itemView.setOnClickListener { itemClickListener(store) }
            binding.store = StoreItemViewModel(store)
        }
    }
}
