package net.nshiba.eatsenju.views.activities

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import net.nshiba.eatsenju.R
import net.nshiba.eatsenju.databinding.ActivityMainBinding
import net.nshiba.eatsenju.networks.Store
import net.nshiba.eatsenju.presenter.MainActivityPresenter
import net.nshiba.eatsenju.views.adapters.StoreRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    private val presenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupRecyclerView()
        presenter.fetchStoreList().subscribe(this::success, this::error)
    }

    fun setupRecyclerView() {
        val manager = GridLayoutManager(this, 2)
        val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) { 2 } else { 1 }
            }
        }
        manager.spanSizeLookup = spanSizeLookup
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = StoreRecyclerViewAdapter(context = this, itemClickListener = {})
    }

    fun success(data: List<Store>) {
        val adapter = binding.recyclerView.adapter as StoreRecyclerViewAdapter
        adapter.addAll(data)
    }

    fun error(error: Throwable) {
        Log.e(TAG, "fetch store list error", error)
    }
}
