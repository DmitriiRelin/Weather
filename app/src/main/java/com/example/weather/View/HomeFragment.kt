package com.example.weatherapi.View

import android.os.Bundle
import android.text.TextUtils
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weather.AppStateHomeFragment
import com.example.weather.R
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weatherapi.Data.CityResponse
import com.example.weatherapi.Utils.showSnackBar
import com.example.weatherapi.ViewModel.HomeViewModel

class HomeFragment : Fragment() {

    private var weatherFlag: Boolean = false

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            renderData(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.requestFocus()

        binding.mbWeather.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.constrainLayout, Slide(Gravity.END))
            weatherFlag = !weatherFlag
            binding.groupWeather.visibility = if (weatherFlag) View.VISIBLE else View.GONE
        }


        binding.load.setOnClickListener {
            if (TextUtils.isEmpty(binding.editText.text)) {
                binding.textInputLayout.error = "EMPTY"
            } else {
                binding.textInputLayout.error = ""
                val text = binding.editText.text.toString()
                viewModel.loadData(text)
            }
        }
        binding.btnAdd.setOnClickListener {
            viewModel.addToDB()
        }
    }


    private fun renderData(state: AppStateHomeFragment) {
        when (state) {
            is AppStateHomeFragment.Success -> {
                binding.constrainLayout.visibility = View.VISIBLE
                binding.mbWeather.visibility = View.VISIBLE
                binding.btnAdd.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                loadInfo(state.cityResponse)
            }
            is AppStateHomeFragment.Loading -> {
                binding.constrainLayout.visibility = View.GONE
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
            }
            is AppStateHomeFragment.Error -> {
                binding.constrainLayout.visibility = View.VISIBLE
                binding.mbWeather.visibility = View.GONE
                binding.btnAdd.visibility = View.GONE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                binding.constrainLayout.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        val text = binding.editText.text.toString()
                        viewModel.loadData(text)
                    })
            }
        }
    }


    private fun loadInfo(city: CityResponse) {
        binding.tvWeatherTemp.text = "TEMP: ${city.main?.temp}"
        binding.tvWeatherFeelsLike.text = "FEELS LIKE: ${city.main?.feelsLike}"
        binding.tvWeatherTempMin.text = "TEMP MIN: ${city.main?.tempMin}"
        binding.tvWeatherTempMax.text = "TEMP MAX: ${city.main?.tempMax}"
        binding.tvWeatherPressure.text = "PRESSURE: ${city.main?.pressure}"
        binding.tvWeatherHumidity.text = "HUMIDITY: ${city.main?.pressure}"
        binding.tvName.text = city.name
        binding.tvLat.text = "LAT: ${city.coord?.lat.toString()}"
        binding.tvLon.text = "LON: ${city.coord?.lon.toString()}"
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}