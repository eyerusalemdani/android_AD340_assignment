package com.androiddemo.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androiddemo.R
import com.androiddemo.databinding.FragmentHomeBinding
import com.androiddemo.ui.movies.MoviesActivity
import com.androiddemo.ui.traffic.TrafficActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnCities.setOnClickListener {
            Toast.makeText(requireContext(), resources.getString(R.string.cities), Toast.LENGTH_SHORT).show()
        }

        binding.btnMovies.setOnClickListener {
            Toast.makeText(requireContext(), resources.getString(R.string.movies), Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), MoviesActivity::class.java)
            startActivity(intent)
        }

        binding.btnParks.setOnClickListener {
            Toast.makeText(requireContext(), resources.getString(R.string.parks), Toast.LENGTH_SHORT).show()
        }

        binding.btnTraffic.setOnClickListener {
            Toast.makeText(requireContext(), resources.getString(R.string.traffic), Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), TrafficActivity::class.java)
            startActivity(intent)
        }

        binding.btnMusic.setOnClickListener {
            Toast.makeText(requireContext(), resources.getString(R.string.music), Toast.LENGTH_SHORT).show()
        }

        binding.btnFood.setOnClickListener {
            Toast.makeText(requireContext(), resources.getString(R.string.food), Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}