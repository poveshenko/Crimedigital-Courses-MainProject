package com.example.matchresults

import com.example.matchresults.data.api.Api
import com.example.matchresults.model.MatchItemsData
import com.example.matchresults.model.MatchList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class ApiTest {

    @Test
    fun testGetMatches() = runTest {
        // Создание заглушки для класса Api
        val api = Mockito.mock(Api::class.java)

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
        Mockito.`when`(api.getMatches()).thenReturn(matchList)

        // Вызываем метод getMatches и сохраняем результат
        val result = api.getMatches()

        // Проверяем, что результат не равен null
        assertNotNull(result)

        // Проверяем, что метод getMatches был вызван один раз
        Mockito.verify(api, Mockito.times(1)).getMatches()
    }
}
