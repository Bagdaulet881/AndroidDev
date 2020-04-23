package com.example.moviedbapi.main.popularMovie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviedbapi.R
import com.example.moviedbapi.base.ParentFragment
import com.example.moviedbapi.data.models.MovieData
import com.example.moviedbapi.data.models.Story
import com.example.moviedbapi.main.stories.Stories
import com.example.moviedbapi.main.stories.StoriesAdapter
import com.example.moviedbapi.utilities.AppConstants
import com.example.moviedbapi.utilities.PaginationListener
import org.koin.android.ext.android.inject


class MoviePopularFragment : ParentFragment() {

    private lateinit var navController: NavController
    private val viewModel: MoviePopularViewModel by inject()
    private lateinit var rvMovies: RecyclerView
    private lateinit var srlMovies: SwipeRefreshLayout
    private var storiesAdapter: StoriesAdapter? = null
    private var currentPage = PaginationListener.PAGE_START
    private var isLastPage = false
    private var isLoading = false
    private var itemCount = 0
    lateinit var storiesRecyclerView: RecyclerView

    private val onClickListener = object :
        MovieAdapter.ItemClickListener {
        override fun onItemClick(item: MovieData) {
            val bundle = Bundle()
            bundle.putInt(AppConstants.MOVIE_ID, item.id!!)
            navController.navigate(
                R.id.action_moviesListFragment_to_movieDetailsFragment,
                bundle
            )
        }
    }

    private val moviesAdapter by lazy {
        MovieAdapter(
            itemClickListener = onClickListener
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.movies_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setAdapter()
        setData()
    }

    override fun bindViews(view: View) = with(view) {
        navController = Navigation.findNavController(this)
        srlMovies = findViewById(R.id.srlMovies)
        rvMovies = findViewById(R.id.rvMovies)
        //val layoutManager = LinearLayoutManager(context)
//        LinearLayoutManager layoutMan = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
//        -------------------------------------------------------------------------------------------
        storiesRecyclerView = view.findViewById(R.id.recyclerViewStories)
        storiesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        storiesAdapter =
            StoriesAdapter(
                storyGenerator()
            )
        storiesRecyclerView.adapter = storiesAdapter


//        -------------------------------------------------------------------------------------------

        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvMovies.layoutManager = linearLayoutManager
        rvMovies.setHasFixedSize(true)
        rvMovies.addOnScrollListener(object : PaginationListener(linearLayoutManager) {


            override fun loadMoreItems() {
                Log.d("MovieApp: loadMore", "true")
                isLoading = true
                currentPage++
                viewModel.loadMovies(page = currentPage)
            }

            override fun isLastPage(): Boolean = isLastPage

            override fun isLoading(): Boolean = isLoading
        })

        srlMovies.setOnRefreshListener {
            moviesAdapter.clearAll()
            itemCount = 0
            currentPage = PaginationListener.PAGE_START
            isLastPage = false
            viewModel.loadMovies(page = currentPage)
        }
    }

    override fun setData() {
        moviesAdapter.clearAll()
        viewModel.liveData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is MoviePopularViewModel.State.ShowLoading -> {
                    srlMovies.isRefreshing = true
                }
                is MoviePopularViewModel.State.HideLoading -> {
                    srlMovies.isRefreshing = false
                }
                is MoviePopularViewModel.State.Result -> {
                    itemCount = result.list.size
                    if (currentPage != PaginationListener.PAGE_START) {
                        moviesAdapter.removeLoading()
                    }
                    moviesAdapter.addItems(result.list)
                    if (currentPage < result.totalPage) {
                        moviesAdapter.addLoading()
                    } else {
                        isLastPage = true
                    }
                    isLoading = false
                }
                is MoviePopularViewModel.State.Error -> {
                    Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
                }
                is MoviePopularViewModel.State.IntError -> {
                    Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setAdapter() {
        rvMovies.adapter = moviesAdapter
    }

    private fun storyGenerator(): ArrayList<Story> {

        var listStories = Stories.stories

        listStories.add(
            Story(
                1,
                "Премьеры",
                R.drawable.s1
            )
        )
        listStories.add(
            Story(
                2,
                "Теперь бесп...",
                R.drawable.st3
            )
        )
        listStories.add(
            Story(
                3,
                "Лучшее",
                R.drawable.s2
            )
        )
        listStories.add(
            Story(
                4,
                "Цена снижена",
                R.drawable.st4
            )
        )
        listStories.add(
            Story(
                5,
                "Боевики",
                R.drawable.st5
            )
        )
        listStories.add(
            Story(
                6,
                "Всех Времен",
                R.drawable.st6
            )
        )



        listStories.add(
            Story(
                7,
                "Недавние",
                R.drawable.a5
            )
        )

        return listStories
    }

}
