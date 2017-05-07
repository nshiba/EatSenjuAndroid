package net.nshiba.eatsenju

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.nshiba.eatsenju.networks.EatSenjuService
import net.nshiba.eatsenju.networks.RetrofitClient

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tmp()
    }

    fun tmp() {
        RetrofitClient.default().build().create(EatSenjuService::class.java)
                .stores()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { it -> Log.d(TAG, it.toString())},
                        {error -> Log.e(TAG, "fetch store error", error)})
    }
}
