package com.example.matchresults.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchresults.databinding.ListItemMatchBinding
import com.example.matchresults.model.MatchItemsData
import com.example.matchresults.utils.TimeUtils


// Класс MatchAdapter отвечает за отображение данных о матчах в RecyclerView.


class MatchAdapter(
    private var items: List<MatchItemsData>,
    private val onItemClick: (MatchItemsData) -> Unit
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemMatchBinding.inflate(inflater, parent, false)
        return MatchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateData(updatedItems: List<MatchItemsData>) {
        items = updatedItems
        notifyDataSetChanged()
    }

    inner class MatchViewHolder(private val binding: ListItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MatchItemsData) {
            binding.textTeam1.text = item.HomeTeam
            binding.textTeam2.text = item.AwayTeam
            binding.textLocation.text = item.Location
            binding.textTime.text = TimeUtils.convertUtcToLocal(item.DateUtc)
            binding.scoreHomeTeam.text = item.HomeTeamScore.toString()
            binding.scoreAwayTeam.text = item.AwayTeamScore.toString()

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}
