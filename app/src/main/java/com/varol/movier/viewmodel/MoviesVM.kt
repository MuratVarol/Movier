package com.varol.movier.viewmodel

import com.varol.movier.base.BaseVM
import com.varol.movier.util.binding_adapters.SingleLiveEvent

class MoviesVM : BaseVM() {

    val isLoading = SingleLiveEvent<Boolean>()

}