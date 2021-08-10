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
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.viewmodel.HomeViewModel
import com.example.weatherapi.Utils.makeSnackBar
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.requestFocus()
        setObservers()
        setClickListeners()
    }

    private fun setObservers() {
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}