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
import com.example.weather.R
import com.example.weather.ResponseResult
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weatherapi.Data.CityWeather
import com.example.weatherapi.Utils.makeSnackBar
import com.example.weatherapi.Utils.showSnackBar
import com.example.weatherapi.ViewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var snackbar: Snackbar? = null

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.requestFocus()
        setObservers()
        setClickListeners()
    }

    private fun setObservers() {
        viewModel.cityWeatherLiveData.observe(viewLifecycleOwner){cityWeater->
            if (cityWeater != null){
                binding.constrainLayout.visibility = View.VISIBLE
                binding.mbWeather.visibility = View.VISIBLE
                binding.btnAdd.visibility = View.VISIBLE
                loadInfo(cityWeater)
            } else{
                binding.constrainLayout.visibility = View.GONE
                binding.mbWeather.visibility = View.GONE
                binding.btnAdd.visibility = View.GONE
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){error->
            if (error != null){
                snackbar = binding.constrainLayout.makeSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        val text = binding.editText.text.toString()
                        viewModel.loadData(text)
                    })
                snackbar?.show()
            } else
                snackbar?.dismiss()
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){isLoading->
            if (isLoading)
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
            else
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
        }

        viewModel.detailInfoVisibilityLiveData.observe(viewLifecycleOwner){isVisible->
            binding.groupWeather.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }

    private fun setClickListeners() {
        binding.mbWeather.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.constrainLayout, Slide(Gravity.END))
            viewModel.changeDetailInfoVisibility()
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

    private fun loadInfo(city: CityWeather) {
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