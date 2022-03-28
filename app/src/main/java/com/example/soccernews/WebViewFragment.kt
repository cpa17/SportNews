package com.example.soccernews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.soccernews.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment(R.layout.fragment_web_view) {
    private lateinit var binding: FragmentWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWebViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("source")?.let {
            binding.webViewText.text = (it)
        }

        arguments?.getString("url")?.let {
            binding.webView.loadUrl(it)
            binding.backButtonSource.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}
