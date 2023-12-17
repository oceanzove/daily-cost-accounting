package com.zove.money

import androidx.lifecycle.ViewModel
import com.zove.money.data.Entry

class EntryViewModel : ViewModel() {
    private val _entry = mutableListOf<Entry>()

    val entries: List<Entry>
        get() = _entry

    fun addEntry(entry: Entry){
        _entry.add(entry)
    }

    fun removedEntry(entry: Entry){
        _entry.remove(entry)
    }
}