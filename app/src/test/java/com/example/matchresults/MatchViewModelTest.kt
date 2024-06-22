package com.example.matchresults

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.matchresults.data.MatchViewModel
import com.example.matchresults.data.api.RetrofitInstance
import com.example.matchresults.data.api.Api
import com.example.matchresults.model.MatchItemsData
import com.example.matchresults.model.MatchList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class MatchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MatchViewModel
    private lateinit var api: Api

    @Before
    fun setup() {
        api = mock(Api::class.java)
        RetrofitInstance.api = api // Инжектируем mock Api в RetrofitInstance
        viewModel = MatchViewModel()
    }

    @Test
    fun testFetchMatches() = runBlocking {
        // Создание объекта MatchList для возвращения из запроса
        val matchList = MatchList().apply {
            add(
                MatchItemsData(
                    AwayTeam = "Team A",
                    AwayTeamScore = 1,
                    DateUtc = "2023-06-12T18:00:00Z",
                    Group = "",
                    HomeTeam = "Team B",
                    HomeTeamScore = 2,
                    Location = "Stadium",
                    MatchNumber = 1,
                    RoundNumber = 1
                )
            )
        }

        // Устанавливаем поведение метода getMatches при вызове
        `when`(api.getMatches()).thenReturn(matchList)

        // Создаем observer для LiveData
        @Suppress("UNCHECKED_CAST")
        val observer = mock(Observer::class.java) as Observer<List<MatchItemsData>>
        viewModel.matches.observeForever(observer)

        // Вызываем метод fetchMatches (который использует корутины)
        viewModel.fetchMatches()

        // Ждем выполнения асинхронной операции
        kotlinx.coroutines.delay(100) // Временное решение, лучше использовать более надежный подход

        // Проверяем, что LiveData обновилось корректно
        Mockito.verify(observer).onChanged(matchList)

        // Проверяем, что метод getMatches был вызван один раз
        Mockito.verify(api, Mockito.times(1)).getMatches()

        // Отменяем наблюдение, чтобы избежать утечки ресурсов
        viewModel.matches.removeObserver(observer)
    }
}
