package com.victorteka.caloriestracker.ui.newMeal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.victorteka.caloriestracker.databinding.AddNewMealFragmentBinding
import com.victorteka.caloriestracker.ui.newMeal.recently.RecentSearchedFoodAdapter
import com.victorteka.caloriestracker.ui.newMeal.recently.RecentlyViewModel
import com.victorteka.domain.Result
import com.victorteka.domain.models.Common
import com.victorteka.domain.repository.FoodStatsRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AddNewMealFragment : Fragment() {

    private lateinit var binding: AddNewMealFragmentBinding
    private val TAG = "AddNewMeal"

    @Inject
    lateinit var foodStatsRepository: FoodStatsRepository
    private val viewModel by viewModels<RecentlyViewModel>()

    private lateinit var adapter: RecentSearchedFoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddNewMealFragmentBinding.inflate(inflater, container, false)

        setupData()

        setupSearch()

        setupUI()

        return binding.root
    }

    private fun setupData() {
        viewModel.foodFound.observe(viewLifecycleOwner){ result ->
            when(result){
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d(TAG, "${result.exception.message.orEmpty()}")
                }
                Result.Loading -> {
                    Log.d(TAG, "Loading ...")
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    renderList(result.data)
                }
            }
        }
    }

    private fun setupSearch() {
        binding.searchInput.doAfterTextChanged { queryTerm ->
            if (!queryTerm.isNullOrEmpty()) {
                viewModel.searchFood(queryTerm.toString()).observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Log.d(TAG, "${result.exception.message.orEmpty()}")
                        }
                        Result.Loading -> {
                            Log.d(TAG, "Loading ...")
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(result.data)
                        }
                    }
                }
            }
        }
    }

    private fun setupUI() {
        binding.recentSearchFoodRV.layoutManager = LinearLayoutManager(context)
        adapter = RecentSearchedFoodAdapter(arrayListOf())
        binding.recentSearchFoodRV.addItemDecoration(
            DividerItemDecoration(
                binding.recentSearchFoodRV.context,
                (binding.recentSearchFoodRV.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recentSearchFoodRV.adapter = adapter

        adapter.onFoodClick = { food ->
            val action = AddNewMealFragmentDirections.actionAddNewMealFragmentToFoodDetailsFragment(food.food_name)
            findNavController().navigate(action)
        }
    }

    private fun renderList(foods: List<Common>) {

        adapter.addData(foods)
        adapter.notifyDataSetChanged()
    }

}