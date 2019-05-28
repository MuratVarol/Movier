package com.varol.movier.util.listener

import android.widget.Spinner

interface ItemSelectListener<ModelType> {
    fun onItemSelect(view: Spinner, item: ModelType, position: Int)
}