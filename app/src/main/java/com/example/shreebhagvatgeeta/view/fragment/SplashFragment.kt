package com.example.shreebhagvatgeeta.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shreebhagvatgeeta.R
import com.example.shreebhagvatgeeta.utils.CommonFunction.changeStatusBarColour

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        changeStatusBarColour(requireActivity(), R.color.splash, isLightStatusBar = true)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }, 4000)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


}