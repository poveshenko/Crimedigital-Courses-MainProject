package com.example.matchresults.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.matchresults.R
import com.example.matchresults.databinding.FragmentMatchDetailsBinding
import com.example.matchresults.model.MatchModel
import com.example.matchresults.utils.TimeUtils


//MatchDetailsFragment получает данные о матче через аргументы и отображает их.

class MatchDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMatchDetailsBinding
    private val matchFragment = MatchListFragment()

    companion object {
        private const val ARG_MATCH_ITEM = "match_item"

        fun newInstance(matchItem: MatchModel): MatchDetailsFragment {
            val fragment = MatchDetailsFragment()
            val args = Bundle()
            args.putParcelable(ARG_MATCH_ITEM, matchItem)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchDetailsBinding.inflate(inflater, container, false)


        binding.buttonBack.setOnClickListener {
            val fragment = MatchListFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null) // Добавляем этот фрагмент в back stack
            transaction.commit()
        }




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val matchItem = arguments?.getParcelable<MatchModel>(ARG_MATCH_ITEM)
        matchItem?.let {
            binding.textTeam1.text = it.HomeTeam
            binding.textTeam2.text = it.AwayTeam
            binding.textLocation.text = it.Location
            binding.textTime.text = TimeUtils.convertUtcToLocal(it.DateUtc)
            binding.scoreHomeTeam.text = it.HomeTeamScore.toString()
            binding.scoreAwayTeam.text = it.AwayTeamScore.toString()
        }
    }
}
