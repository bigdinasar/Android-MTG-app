package com.example.magicgallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.magicgallery.databinding.FragmentHowtoPageBinding

class HowtoPageFragment : Fragment() {
    private val args: HowtoPageFragmentArgs by navArgs()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstance: Bundle?
    ): View {
        val binding = FragmentHowtoPageBinding.inflate(
            inflater,
            container,
            false
        )

        binding.apply {
            webView.apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(args.howtoPageUri.toString())
            }
        }

        return binding.root
    }
}