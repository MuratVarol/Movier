package com.varol.movier.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.movier.R
import com.varol.movier.base.BaseFragment
import com.varol.movier.databinding.FragmentMovieDetailBinding
import com.varol.movier.model.MoviesModel
import com.varol.movier.viewmodel.MovieDetailVM

class MovieDetailFragment : BaseFragment<MovieDetailVM, FragmentMovieDetailBinding>(MovieDetailVM::class) {

    override val getLayoutId: Int = R.layout.fragment_movie_detail


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        this.arguments?.let {

            val selectedMovie: MoviesModel? = it.getParcelable(KEY_SELECTED_MOVIE)
            selectedMovie?.let { movieModel ->
                viewModel.setSelectedMovie(movieModel)
            }
        }



        return binding.root
    }


}
