package com.varol.movier.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.movier.R
import com.varol.movier.base.BaseFragment
import com.varol.movier.databinding.FragmentMoviesBinding
import com.varol.movier.model.MoviesModel
import com.varol.movier.viewmodel.MoviesVM
import observe
import java.util.*

class MoviesFragment : BaseFragment<MoviesVM, FragmentMoviesBinding>(MoviesVM::class) {

    override val getLayoutId: Int = R.layout.fragment_movies


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)


        subscribeSelectedMovie()
        subscribeSelectedSingleTypeMovies()


        return binding.root
    }


    /**
     * pushing value to selectedMovie will trigger below method
     * MovieDetailFragment will be called with selected movie.
     */
    private fun subscribeSelectedMovie() {
        viewModel.selectedMovie.observe(this) {
            it?.let { movieModel ->
                val bundle = Bundle()
                bundle.putParcelable(KEY_SELECTED_MOVIE, movieModel)
                val movieDetailFragment = MovieDetailFragment().apply {
                    arguments = bundle
                }
                loadFragment(R.id.frmMainContainer, movieDetailFragment, fragmentManager, true)
            }
        }
    }


    /**
     * pushing value to singleSelectedMovies (List of Movies by type) will trigger below method
     * SingleTypeMoviesFragment will be called with movie list of selected type.
     */
    private fun subscribeSelectedSingleTypeMovies() {
        viewModel.singleSelectedMovies.observe(this) {
            it?.let { movieModelWithType ->
                val bundle = Bundle()
                bundle.putString(KEY_SELECTED_MOVIES_TYPE, it.first)
                bundle.putParcelableArrayList(KEY_SELECTED_MOVIE, movieModelWithType.second as ArrayList<MoviesModel>)
                val singleTypeMoviesFragment = SingleTypeMoviesFragment().apply {
                    arguments = bundle
                }
                loadFragment(R.id.frmMainContainer, singleTypeMoviesFragment, fragmentManager, true)
            }
        }
    }

}
