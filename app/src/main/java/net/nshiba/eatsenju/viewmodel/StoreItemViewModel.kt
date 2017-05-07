package net.nshiba.eatsenju.viewmodel

import android.databinding.ObservableField

class StoreItemViewModel(isOpenData: Boolean, name: String, category: String, limit: String, openTime: String) {

    val isOpen: ObservableField<String>

    val name = ObservableField<String>(name)

    val category = ObservableField<String>(category)

    val limit = ObservableField<String>(limit)

    val openTime = ObservableField<String>(openTime)

    init {
        val isOpenText = if (isOpenData) "開店" else "閉店"
        isOpen = ObservableField(isOpenText)
    }
}