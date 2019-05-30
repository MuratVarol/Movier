package com.varol.movier.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.movier.base.BaseVM
import com.varol.movier.model.MoviesModel
import com.varol.movier.remote.DataHolder
import com.varol.movier.remote.MovieTypes
import com.varol.movier.usecase.GetMoviesUseCase
import com.varol.movier.util.binding_adapters.SingleLiveEvent
import com.varol.movier.util.listener.ItemClickListener
import com.varol.movier.viewentity.MoviesWithType
import io.reactivex.Observable

class MoviesVM(
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseVM() {
    val popularMovieViewEntityList = MutableLiveData<MutableList<MoviesModel>>()
    val upcomingMovieViewEntityList = MutableLiveData<MutableList<MoviesModel>>()
    val topRatedMovieViewEntityList = MutableLiveData<MutableList<MoviesModel>>()

    val moviesWithTypeList = mutableListOf<MoviesWithType>()


    val startScreenMovieList = MutableLiveData<MutableList<MoviesWithType>>()

    val isLoading = SingleLiveEvent<Boolean>()
    val isRefreshing = SingleLiveEvent<Boolean>()

    private val movieTypeList: List<MovieTypes> by lazy {
        listOf(
            MovieTypes.Popular,
            MovieTypes.TopRated
        )
    }

    val itemClickListener = object : ItemClickListener<MoviesWithType> {
        override fun onItemClick(view: View, item: MoviesWithType, position: Int) {
            Log.v("onItemClick", item.type.name)
        }
    }

    val subItemClickListener = object : ItemClickListener<MoviesModel> {
        override fun onItemClick(view: View, item: MoviesModel, position: Int) {
            Log.v("onItemClick", item.title)
        }

    }

    init {
        getAllMovies()
    }

    fun getAllMovies() {
        val disposable = Observable.just(getPopularMovies(), getTopRatedMovies())
            .subscribe()
        disposables.add(disposable)


        val movieTypes = listOf(MovieTypes.Popular, MovieTypes.TopRated)

    }

    fun getPopularMovies(page: Int = 1) {
        getMoviesByType(MovieTypes.Popular, page)
    }


    fun getTopRatedMovies(page: Int = 1) {
        getMoviesByType(MovieTypes.TopRated, page)
    }

    private fun getMoviesByType(movieType: MovieTypes, page: Int) {

        isLoading.postValue(true)

        val disposable = getMoviesUseCase
            .getMoviesByType(movieType, page)
            .subscribeOn(getBackgroundScheduler())
            .observeOn(getMainThreadScheduler())
            .subscribe { data ->
                isLoading.postValue(false)
                isRefreshing.postValue(false)
                when (data) {

                    is DataHolder.Success -> {

                        val moviesWithType = MoviesWithType(
                            movieType,
                            data.data.results,
                            subItemClickListener
                        )
                        moviesWithTypeList.add(moviesWithType)
                        startScreenMovieList.postValue(moviesWithTypeList)

//                            startScreenMovieList.value?.filter {
//                                it.typeName == MovieTypes.TopRated.name
//                            }

                    }
                }
            }

        disposables.add(disposable)
    }


}