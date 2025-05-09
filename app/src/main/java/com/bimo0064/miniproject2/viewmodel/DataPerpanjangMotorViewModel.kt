package com.bimo0064.miniproject2.viewmodel

import androidx.lifecycle.ViewModel
import com.bimo0064.miniproject2.model.DataPerpanjangMotor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataPerpanjangMotorViewModel : ViewModel() {
    private val _dataList = MutableStateFlow<List<DataPerpanjangMotor>>(emptyList())
    val dataList: StateFlow<List<DataPerpanjangMotor>> = _dataList

    fun addData(data: DataPerpanjangMotor) {
        _dataList.value = _dataList.value + data
    }

    fun removeData(index: Int) {
        _dataList.value = _dataList.value.toMutableList().apply { removeAt(index) }
    }
}