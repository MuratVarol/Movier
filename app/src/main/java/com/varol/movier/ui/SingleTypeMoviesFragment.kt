package com.varol.movier.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.movier.R
import com.varol.movier.base.BaseFragment
import com.varol.movier.databinding.FragmentSingleTypeMoviesBinding
import com.varol.movier.model.MoviesModel
import com.varol.movier.viewmodel.MoviesVM
import observe

class SingleTypeMoviesFragment : BaseFragment<MoviesVM, FragmentSingleTypeMoviesBinding>(MoviesVM::class) {

    override val getLayoutId: Int = R.layout.fragment_single_type_movies


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        subscribeSelectedMovie()

        this.arguments?.let {
            val selectedMovie: List<MoviesModel>? = it.getParcelableArrayList(KEY_SELECTED_MOVIE)
            val selectedType: String? = it.getString(KEY_SELECTED_MOVIES_TYPE)
            selectedMovie?.let { movieModel ->
                if (selectedType?.isNotEmpty() == true)
                    viewModel.singleSelectedMovies.postValue(Pair(selectedType, movieModel as MutableList<MoviesModel>))
            }
        }




        return binding.root
    }

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

}
