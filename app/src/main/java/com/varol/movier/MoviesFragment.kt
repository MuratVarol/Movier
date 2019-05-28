package com.varol.movier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.movier.base.BaseFragment
import com.varol.movier.databinding.FragmentMoviesBinding
import com.varol.movier.viewmodel.MoviesVM

class MoviesFragment : BaseFragment<MoviesVM, FragmentMoviesBinding>(MoviesVM::class) {

    override val getLayoutId: Int = R.layout.fragment_movies


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }


}
