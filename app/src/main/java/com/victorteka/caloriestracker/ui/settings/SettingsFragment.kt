package com.victorteka.caloriestracker.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.victorteka.caloriestracker.R
import com.victorteka.caloriestracker.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var binding: SettingsFragmentBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)

        binding.myGoal.setOnClickListener {
            showMyGoalDialog()
        }

        sharedPreferences = requireActivity().getSharedPreferences("PREF_FILE",Context.MODE_PRIVATE)
        return binding.root
    }

    private fun showMyGoalDialog() {
        //Inflate the dialog as custom view
        val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.goal_dialog_layout, null)

        //AlertDialogBuilder
        val messageBoxBuilder = activity?.let { AlertDialog.Builder(it).setView(messageBoxView) }

        //show dialog
        val  messageBoxInstance = messageBoxBuilder?.show()

        val submitBtn = messageBoxView.findViewById<Button>(R.id.submit)
        val calET = messageBoxView.findViewById<EditText>(R.id.etCalories)
        val currentTarget = sharedPreferences.getString("calories","0")
        calET.setText(currentTarget)
        submitBtn.setOnClickListener {
            if (messageBoxInstance != null) {
                messageBoxInstance.dismiss()
                val target = calET.text.toString()
                val editor = sharedPreferences.edit()
                editor.putString("calories", target)
                editor.apply()
                editor.commit()
                Log.d("TAG", "showMyGoalDialog: $target")
            }
        }

        val cancelBtn = messageBoxView.findViewById<Button>(R.id.cancel)
        cancelBtn.setOnClickListener {
            if (messageBoxInstance != null) {
                messageBoxInstance.dismiss()
            }
        }
      /*  //set Listener
        messageBoxView.setOnClickListener(){
            //close dialog

        }*/
    }

}