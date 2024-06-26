package com.example.matchresults.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matchresults.data.api.RetrofitInstance
import com.example.matchresults.model.MatchModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel : ViewModel() {

    // LiveData для отфильтрованного, списка матчей
    private val _filteredMatches = MutableLiveData<List<MatchModel>>()
    val filteredMatches: LiveData<List<MatchModel>>
        get() = _filteredMatches

    // Метод для выполнения поиска
    fun searchMatchesByTeam(teamName: String) {
        val matchesToFilter = _matches.value ?: emptyList()
        val filteredList = matchesToFilter.filter { match ->
            match.HomeTeam.contains(teamName, ignoreCase = true) ||
                    match.AwayTeam.contains(teamName, ignoreCase = true)
        }
        _filteredMatches.value = filteredList
    }

    private val _matches = MutableLiveData<List<MatchModel>>()
    val matches: LiveData<List<MatchModel>>
        get() = _matches

    init {
        fetchMatches()
    }

    internal fun fetchMatches() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val matchesData = RetrofitInstance.api?.getMatches()
                _matches.postValue(matchesData)
            } catch (e: Exception) {
                // Handle error
                Log.e("MatchViewModel", "Error fetching matches: ${e.message}")
            }
        }
    }
}
