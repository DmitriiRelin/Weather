package com.example.weatherapi.View

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weather.R
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.ui.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var snackbar: Snackbar? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

//    private val viewModel: HomeViewModel by lazy {
//        ViewModelProvider(this).get(HomeViewModel::class.java)
//    }

    private val viewModel: HomeViewModel by viewModels()

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
    }

    private fun setObservers() {
        viewModel.errorLiveData.observe(viewLifecycleOwner){error->
            if (error != null){
                snackbar = binding.constrainLayout.makeSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.loadData()
                    })
                snackbar?.show()
            } else
                snackbar?.dismiss()
        }

        viewModel.detailInfoVisibilityLiveData.observe(viewLifecycleOwner){
            TransitionManager.beginDelayedTransition(binding.constrainLayout, Slide(Gravity.END))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}