package com.varol.movier.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.varol.movier.R
import com.varol.movier.base.BaseFragment
import com.varol.movier.databinding.FragmentSingleTypeMoviesBinding
import com.varol.movier.model.MoviesModel
import com.varol.movier.remote.MovieTypes
import com.varol.movier.util.listener.EndlessRecyclerViewScrollListener
import com.varol.movier.viewmodel.MoviesVM
import observe

class SingleTypeMoviesFragment : BaseFragment<MoviesVM, FragmentSingleTypeMoviesBinding>(MoviesVM::class) {

    override val getLayoutId: Int = R.layout.fragment_single_type_movies

    lateinit var selectedType: String

    private val visibleThreshold = 3

    lateinit var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    var pageCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        subscribeSelectedMovie()
        subscribeResetScrollState()



        this.arguments?.let {
            val selectedMovie: List<MoviesModel>? = it.getParcelableArrayList(KEY_SELECTED_MOVIE)
            selectedType = it.getString(KEY_SELECTED_MOVIES_TYPE) ?: ""
            selectedMovie?.let { movieModel ->
                if (selectedType.isNotEmpty())
                    viewModel.singleSelectedMovies.postValue(Pair(selectedType, movieModel as MutableList<MoviesModel>))
            }
        }




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewListeners()
    }

    /**
     * Reset states of endlessRecyclerViewScrollListener for prevent unnecessary onLoadMore method calling
     */
    private fun subscribeResetScrollState() {
        viewModel.isNeedToResetScrollState.observe(this) {
            if (it == true)
                endlessRecyclerViewScrollListener.resetState()
        }
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
     * RecyclerView endless scrolling listener
     * TODO: check for moving endlessRecyclerViewScrollListener to inside of viewModel
     * TODO: this could be trigger service on screen rotation.
     *
     */
    private fun setRecyclerViewListeners() {
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.rvMovies.layoutManager = gridLayoutManager
        endlessRecyclerViewScrollListener = object :
            EndlessRecyclerViewScrollListener(gridLayoutManager, visibleThreshold) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {

                /**
                 * increasing pageCount is workaround.
                 * Expected EndlessRecyclerViewScrollListener to increase but not working now
                 * TODO: check page increament later
                 */
                pageCount++

                when (selectedType) {
                    MovieTypes.TopRated.name ->
                        viewModel.getTopRatedMovies(pageCount)
                    MovieTypes.Revenue.name ->
                        viewModel.getTopSelledMovies(pageCount)
                    MovieTypes.Popular.name ->
                        viewModel.getPopularMovies(pageCount)
                }


            }
        }

        binding.rvMovies.addOnScrollListener(endlessRecyclerViewScrollListener)

    }


}
