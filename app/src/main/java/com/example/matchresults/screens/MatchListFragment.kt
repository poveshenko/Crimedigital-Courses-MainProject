package com.example.matchresults.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matchresults.R
import com.example.matchresults.adapter.MatchAdapter
import com.example.matchresults.data.MatchViewModel
import com.example.matchresults.databinding.FragmentMatchListBinding
import com.example.matchresults.model.MatchModel

class MatchListFragment : Fragment() {

    private lateinit var binding: FragmentMatchListBinding
    private val viewModel: MatchViewModel by activityViewModels()
    private lateinit var adapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchButton()

        observeMatches()
        observeFilteredMatches()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MatchAdapter(emptyList()) { matchItem ->
            openMatchDetails(matchItem)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun setupSearchButton() {
        binding.buttonSearch.setOnClickListener {
            val searchTerm = binding.editTextSearch.text.toString()
            viewModel.searchMatchesByTeam(searchTerm)
        }
    }

    private fun observeMatches() {
        viewModel.matches.observe(viewLifecycleOwner, Observer { matches ->
            adapter.updateData(matches)
        })
    }

    private fun observeFilteredMatches() {
        viewModel.filteredMatches.observe(viewLifecycleOwner, Observer { filteredMatches ->
            adapter.updateData(filteredMatches)
        })
    }

    private fun openMatchDetails(matchItem: MatchModel) {
        val fragment = MatchDetailsFragment.newInstance(matchItem)
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
