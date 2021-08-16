package com.victorteka.caloriestracker.ui.fooddetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.victorteka.caloriestracker.R
import com.victorteka.caloriestracker.databinding.FragmentFoodDetailsBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.victorteka.domain.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFoodDetailsBinding
    private lateinit var args: FoodDetailsFragmentArgs

    private val viewModel by viewModels<FoodDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailsBinding.inflate(layoutInflater, container, false)
        args = FoodDetailsFragmentArgs.fromBundle(requireArguments())
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        binding.toolbar.title = args.foodName

        setupUI(args.foodName)

        binding.foodContent.addFoodFab.setOnClickListener {
            findNavController().navigate(R.id.action_foodDetailsFragment_to_homeFragment)
        }

        return binding.root
    }

    private fun setupUI(foodName: String) {
        val queryTerm = HashMap<String, String>()
        queryTerm["query"] = foodName
        viewModel.foodDetail(queryTerm).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Error -> {
                    binding.foodContent.fDProgresbar.visibility = View.GONE
                }
                Result.Loading -> {
                    binding.foodContent.fDProgresbar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.foodContent.fDProgresbar.visibility = View.GONE
                    binding.foodContent.apply {
                        caloriesValue.text = result.data.foods[0].nf_calories.toString()
                        fatsValue.text = result.data.foods[0].nf_total_fat.toString()
                        saturatedFatValue.text = result.data.foods[0].nf_saturated_fat.toString()
                        cholesterolValue.text = result.data.foods[0].nf_cholesterol.toString()
                        sodiumValue.text = result.data.foods[0].nf_sodium.toString()
                        carboValue.text = result.data.foods[0].nf_total_carbohydrate.toString()
                        proteinValue.text = result.data.foods[0].nf_protein.toString()
                        fiberValue.text = result.data.foods[0].nf_dietary_fiber.toString()
                        sugarValue.text = result.data.foods[0].nf_sugars.toString()
                        potassiumValue.text = result.data.foods[0].nf_potassium.toString()
                    }
                    /*if (result.data.photo.thumb != null || result.data.photo.thumb != ""){
                        Glide.with(this).load(result.data.photo.thumb)
                            .into(binding.foodImageAppbar)
                    }*/
                }
            }
        }
    }


}