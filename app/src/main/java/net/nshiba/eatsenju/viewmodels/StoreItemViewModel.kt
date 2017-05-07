package net.nshiba.eatsenju.viewmodels

import android.content.Context
import android.databinding.ObservableField
import android.graphics.Color
import net.nshiba.eatsenju.R
import net.nshiba.eatsenju.networks.Store
import java.text.SimpleDateFormat
import java.util.*

class StoreItemViewModel(val data: Store) {

    private val dateFormat = SimpleDateFormat("HH:mm", Locale.JAPAN)

    val openText = ObservableField<String>()

    val name = ObservableField<String>()

    val category = ObservableField<String>()

    val limit = ObservableField<String>()

    val openTime = ObservableField<String>()

    val backgroundColor = ObservableField<Int>()

    init {
        name.set(data.name)
        category.set(data.category)
        setupTime()
    }

    private fun setupTime() {
        val week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        openTime.set(when (week) {
            Calendar.MONDAY -> setupStoreHoliday(data.monday)
            Calendar.TUESDAY -> setupStoreHoliday(data.tuesday)
            Calendar.WEDNESDAY -> setupStoreHoliday(data.wednesday)
            Calendar.THURSDAY -> setupStoreHoliday(data.thursday)
            Calendar.FRIDAY -> setupStoreHoliday(data.friday)
            Calendar.SATURDAY -> setupStoreHoliday(data.saturday)
            Calendar.SUNDAY -> setupStoreHoliday(data.sunday)
            else -> { "" }
        })
    }

    private fun setupStoreHoliday(weekTime: String): String {
        return if (weekTime.isEmpty()) {
            setupOpen(data.base)
            data.base
        } else if(weekTime == "00:00-00:00") {
            setupOpen(weekTime)
            "定休日"
        } else {
            setupOpen(weekTime)
            weekTime
        }
    }

    private fun setupOpen(text: String) {
        val text = if (isOpen(text)) {
            backgroundColor.set(Color.WHITE)
            "開店"
        } else {
            backgroundColor.set(Color.LTGRAY)
            "閉店"
        }
        openText.set(text)
    }

    private fun isOpen(time: String) : Boolean {
        val times = time.split(",").map {
            val range = it.split("-")
            Pair(Integer.parseInt(range[0].replace(":", "")), Integer.parseInt(range[1].replace(":", "")))
        }.filter {
            val today = Integer.parseInt(dateFormat.format(Date()).replace(":", ""))
            it.first <= today && today <= it.second
        }

        return times.isNotEmpty()
    }
}
