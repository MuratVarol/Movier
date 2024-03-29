package com.varol.movier.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.varol.movier.BR
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.reflect.KClass


abstract class BaseFragment<out VM : ViewModel, DB : ViewDataBinding>(viewModelClass: KClass<VM>) : Fragment() {


    val KEY_SELECTED_MOVIE = "selected_movie"
    val KEY_SELECTED_MOVIES_TYPE = "selected_type_movie"
    val KEY_SELECTED_MOVIE_LIST = "selected_movie_LİST"

    //no need for ViewModelProviders
    val viewModel: VM by viewModelByClass(viewModelClass)

    protected lateinit var binding: DB

    abstract val getLayoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    fun loadFragment(
        containerId: Int,
        fragment: Fragment,
        fm: FragmentManager?,
        addToBackStack: Boolean
    ) {
        val ft = fm?.beginTransaction()
        if (addToBackStack) {
            ft?.addToBackStack("")
        }
        ft?.add(containerId, fragment)?.commit()
    }

    fun clearBackStack(manager: FragmentManager?) {
        manager?.apply {
            if (backStackEntryCount > 1) {
                val first = getBackStackEntryAt(1)
                popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }

    }


}