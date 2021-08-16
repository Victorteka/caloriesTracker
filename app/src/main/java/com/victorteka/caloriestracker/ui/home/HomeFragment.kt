package com.victorteka.caloriestracker.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.victorteka.caloriestracker.R
import com.victorteka.caloriestracker.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedPreferences = requireActivity().getSharedPreferences("PREF_FILE", Context.MODE_PRIVATE)

        binding = HomeFragmentBinding.inflate(inflater, container, false)

        binding.tabsContent.breakfast.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNewMealFragment)
        }

        binding.tabsContent.toLunch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNewMealFragment)
        }

        binding.tabsContent.dinner.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNewMealFragment)
        }

        val cal = sharedPreferences.getString("calories", "0")
        binding.tabsContent.myTargetText.text = "$cal cols"

        calculateCals(cal!!.toInt())
        return binding.root
    }

    private fun calculateCals(cals: Int) {
        val breakfastCals = 0.375 * cals
        val lunchCals = 0.5 * cals
        val dinnerCals = 0.125 * cals
        binding.tabsContent.breakfastCals.text = "Recommended $breakfastCals cals"
        binding.tabsContent.lanchCals.text = "Recommended $lunchCals cals"
        binding.tabsContent.dinnerCals.text = "Recommended $dinnerCals cals"
    }

}