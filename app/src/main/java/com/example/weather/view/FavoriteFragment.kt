package com.example.weatherapi.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.AppStateFavoritesFragment
import com.example.weather.R
import com.example.weather.databinding.FragmentFavoriteBinding
import com.example.weatherapi.Utils.makeSnackBar
import com.example.weatherapi.Utils.showSnackBar
import com.example.weatherapi.View.RecyclerView.FavoritesAdapter
import com.example.weatherapi.ViewModel.FavoritesViewModel
import com.google.android.material.snackbar.Snackbar


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private var favoritesAdapter = FavoritesAdapter()
    private var snackbar: Snackbar? = null

    private val viewModel: FavoritesViewModel by lazy {
        ViewModelProvider(this).get(FavoritesViewModel::class.java)
    }

    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            viewModel.deleteFavorite(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = favoritesAdapter

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.favoriteWeatherListLiveData.observe(viewLifecycleOwner){favoriteList->
            if (favoriteList != null){
                binding.recyclerView.visibility = View.VISIBLE
                favoritesAdapter.favoritesList = favoriteList
            } else
                binding.recyclerView.visibility = View.GONE
        }
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){isLoading->
            binding.includedLoadingLayout.loadingLayout.visibility = if (isLoading)
                View.VISIBLE
            else
                View.GONE
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){error->
            if (error != null){
                snackbar = binding.recyclerView.makeSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.getList()
                    })
                snackbar?.show()
            } else
                snackbar?.dismiss()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}