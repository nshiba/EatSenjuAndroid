package net.nshiba.eatsenju.views.activities

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import net.nshiba.eatsenju.R
import net.nshiba.eatsenju.databinding.ActivityMainBinding
import net.nshiba.eatsenju.networks.Store
import net.nshiba.eatsenju.presenter.MainActivityPresenter
import net.nshiba.eatsenju.views.StoreRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    private val presenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupRecyclerView()
        presenter.fetchStoreList()
                .subscribe(this::success, this::error)
    }

    fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun success(data: List<Store>) {
        binding.recyclerView.adapter = StoreRecyclerViewAdapter(data = data, context = this,
                itemClickListener = {
                    // do nothing
                })
    }

    fun error(error: Throwable) {
        Log.e(TAG, "fetch store list error", error)
    }
}
