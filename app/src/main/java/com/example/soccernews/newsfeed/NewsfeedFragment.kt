package com.example.soccernews.newsfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccernews.databinding.FragmentNewsfeedBinding

class NewsfeedFragment : Fragment() {

    private val newsfeedViewModel:NewsfeedViewModel by activityViewModels()
    private var _binding: FragmentNewsfeedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewsfeedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PostListAdapter().also { adapter ->
                newsfeedViewModel.items.observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}