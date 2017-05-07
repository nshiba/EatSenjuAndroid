package net.nshiba.eatsenju.presenter

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.nshiba.eatsenju.networks.EatSenjuService
import net.nshiba.eatsenju.networks.RetrofitClient
import net.nshiba.eatsenju.networks.Store

class MainActivityPresenter {

    val eatSenjuService = RetrofitClient.default().build().create(EatSenjuService::class.java)

    fun fetchStoreList(): Single<List<Store>> {
        return eatSenjuService.stores()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
