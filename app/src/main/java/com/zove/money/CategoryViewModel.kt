package com.zove.money

import androidx.lifecycle.ViewModel
import com.zove.money.data.Category

class CategoryViewModel : ViewModel() {
    private val _category = mutableListOf<Category>(Category("Питание"),Category("Транспорт"))

    val categories: List<Category>
        get() = _category

    fun addCategory(category: Category){
        _category.add(category)
    }

    fun removeCategory(category: Category){
        _category.remove(category)
    }
}