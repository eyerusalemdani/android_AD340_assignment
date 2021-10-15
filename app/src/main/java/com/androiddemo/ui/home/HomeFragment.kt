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
import android.util.Log
import com.androiddemo.ui.firebase.FirebaseActivity
import com.androiddemo.utils.Constants
import com.androiddemo.utils.SharedPreferenceUtil
import com.androiddemo.utils.Utils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var username = ""
    private var email = ""
    private var password = ""

    private lateinit var sharedPreferenceUtil : SharedPreferenceUtil

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sharedPreferenceUtil = SharedPreferenceUtil(requireContext())
        if(sharedPreferenceUtil.getString(Constants.USERNAME, "") != "") {
            startActivity(Intent(requireContext(), FirebaseActivity::class.java))
            activity?.finish()
        }

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

        binding.btnLogin.setOnClickListener {
            signIn()
        }

        return root
    }


    private fun signIn() {

        username = etUsername.text.trim().toString()
        email = etEmail.text.trim().toString()
        password = etPassword.text.trim().toString()

        Log.d("FIREBASE", "signIn")

        // 1 - validate display name, email, and password entries
        if(!validation()) {
            return
        }

        // 2 - sign into Firebase
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                Log.d("FIREBASE", "signIn:onComplete:" + task.isSuccessful)
                if (task.isSuccessful) {
                    // 2 - save valid entries to shared preferences
                    sharedPreferenceUtil.setString(Constants.USERNAME, username)
                    sharedPreferenceUtil.save()

                    // update profile. display name is the value entered in UI
                    val user: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
                    val profileUpdates: UserProfileChangeRequest = UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .build()
                    user.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("FIREBASE", "User profile updated.")
                                // Go to FirebaseActivity
                                startActivity(Intent(requireContext(), FirebaseActivity::class.java))
                                activity?.finish()
                            }
                        }
                } else {
                    Log.d("FIREBASE", "sign-in failed")
                    Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validation() : Boolean {
        return when {
            username.isEmpty() -> {
                Toast.makeText(requireContext(), "Please enter username", Toast.LENGTH_SHORT).show()
                false
            }
            email.isEmpty() -> {
                Toast.makeText(requireContext(), "Please enter email", Toast.LENGTH_SHORT).show()
                false
            }
            password.isEmpty() -> {
                Toast.makeText(requireContext(), "Please enter password", Toast.LENGTH_SHORT).show()
                false
            }
            !Utils.isEmailValid(email) -> {
                Toast.makeText(requireContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show()
                false
            }
            password.length < 6 -> {
                Toast.makeText(requireContext(), "Password must be atleast 6 digits long", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}