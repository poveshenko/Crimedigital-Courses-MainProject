package com.example.matchresults.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchresults.databinding.ListItemMatchBinding
import com.example.matchresults.model.MatchItemsData
import com.example.matchresults.utils.TimeUtils


// Класс MatchAdapter отвечает за отображение данных о матчах в RecyclerView.


class MatchAdapter(
    private var items: List<MatchItemsData>,  // items: Список матчей (MatchItemsData), который будет отображаться в RecyclerView.
    private val onItemClick: (MatchItemsData) -> Unit //onItemClick: Функция обратного вызова, которая будет вызываться при клике на элемент RecyclerView и передавать выбранный элемент MatchItemsData.
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {


    //Показывает макет listItemMutch
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemMatchBinding.inflate(inflater, parent, false)
        return MatchViewHolder(binding)
    }
//Возвращает количество элементов в списке items, которые будут отображены в RecyclerView.
    override fun getItemCount(): Int {
        return items.size
    }

    //Связывает данные для конкретной позиции position с MatchViewHolder.
    //Вызывает метод bind у MatchViewHolder для установки данных из items[position].
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(items[position])
    }

//    Обновляет список items новыми данными updatedItems.
//    Вызывает notifyDataSetChanged(), чтобы уведомить RecyclerView об изменениях в данных и запросить перерисовку.
    fun updateData(updatedItems: List<MatchItemsData>) {
        items = updatedItems
        notifyDataSetChanged()
    }


//    Метод bind(item: MatchItemsData): Устанавливает данные матча (item) в соответствующие представления (TextView, ImageView и т.д.) в binding.
//    setOnClickListener для binding.root (корневого элемента макета): Устанавливает обработчик клика, который вызывает onItemClick(item).
    inner class MatchViewHolder(private val binding: ListItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MatchItemsData) {
            binding.textTeam1.text = item.HomeTeam
            binding.textTeam2.text = item.AwayTeam
          //  binding.textLocation.text = item.Location
            binding.textTime.text = TimeUtils.convertUtcToLocal(item.DateUtc)
            binding.scoreHomeTeam.text = item.HomeTeamScore.toString()
            binding.scoreAwayTeam.text = item.AwayTeamScore.toString()
            binding.NumberMatch.text = item.MatchNumber.toString()
            binding.NumberRound.text = item.RoundNumber.toString()

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}
