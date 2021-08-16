package com.victorteka.caloriestracker.ui.newMeal.recently

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.victorteka.caloriestracker.databinding.FragmentResentlySelectedBinding
import com.victorteka.domain.repository.FoodStatsRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecentlySelectedFoodFragment : Fragment() {

    private lateinit var binding: FragmentResentlySelectedBinding
    @Inject
    lateinit var foodStatsRepository: FoodStatsRepository
    private val viewModel by viewModels<RecentlyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResentlySelectedBinding.inflate(inflater, container, false)

        return binding.root
    }

}